package graphics.renderer.font;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Vector3f;

/**
 * 
 * @author james_000
 *
 *Ein GUIText erstellt ein zeichenbaren Mesh und Aktualisiert ihn, falls der Text geändert wird.
 *
 */
public class GUIText {

	private String text;
	private boolean needUpdate;
	private Vector3f color;
	
	private int vao = -1;
	private int vbo = -1;

	
	public GUIText(String text)
	{
		this.text = text;
		needUpdate = true;
		this.color = new Vector3f(1.0f, 1.0f, 1.0f);
	}
	
	public GUIText(String text, Vector3f color)
	{
		this.color = color;
		this.text = text;
		needUpdate = true;
	}
	
	/**
	 * 
	 * @param font - Die Font mit welcher der Text gezeichnet wird
	 * @return Den Mesh des Textes zum Zeichnen
	 */
	public int getVAO(Font font)
	{
		if (needUpdate)
		{
			rebuildMesh(font);
			needUpdate = false;
		}
		return vao;
	}
	
	/**
	 * 
	 * @return Die Anzahl an Punkten des Meshes
	 */
	public int getVertexCount()
	{
		return text.length() * 6;
	}
	
	/**
	 * Aktualisiert den Text-Mesh
	 * 
	 * @param font - Die Font mit welcher der Text gezeichnet wird
	 */
	private void rebuildMesh(Font font)
	{
		if (vao == -1)
			vao = GL30.glGenVertexArrays();
		GL30.glBindVertexArray(vao);
		
		if (vbo == -1)
			vbo = GL15.glGenBuffers();
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vbo);
		
		GL20.glEnableVertexAttribArray(0);
		GL20.glVertexAttribPointer(0, 2, GL11.GL_FLOAT, false, 4 * 4, 0);
		GL20.glEnableVertexAttribArray(1);
		GL20.glVertexAttribPointer(1, 2, GL11.GL_FLOAT, false, 4 * 4, 2 * 4);
		
		float[] vertices = font.getVertices(text);
		FloatBuffer buffer = storeDataInFloatBuffer(vertices);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
	}
	
	private FloatBuffer storeDataInFloatBuffer(float[] data)
	{
		FloatBuffer result = BufferUtils.createFloatBuffer(data.length);
		result.put(data).flip();
		return result;
	}
	
	/**
	 * 
	 * @param text - Setzt den Text und Aktualisiert falls sich der Text unterscheidet 
	 */
	public void setText(String text) 
	{
		needUpdate = !this.text.equalsIgnoreCase(text);
		this.text = text;
	}
	
	/**
	 * 
	 * @return Der Inhalt des Textes
	 */
	public String getText()
	{
		return text;
	}
	
	public String toString()
	{
		return text;
	}
	
	/**
	 * 
	 * @return Die Farbe des Textes
	 */
	public Vector3f getColor()
	{
		return color;
	}
	
}
