package graphics.renderer;

import java.awt.Color;
import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import graphics.Texture;

public class Renderer {
	
	//Default shader
	private static SpriteShader defaultSpriteShader;
	private static ColorShader defaultColorShader;
	
	//						   				  x	    y	  u	    v
	private static final float[] quadData = {0.0f, 0.0f, 0.0f, 1.0f, 
									   		 1.0f, 0.0f, 1.0f, 1.0f,
									   		 0.0f, 1.0f, 0.0f, 0.0f,
									   		 
									   		 1.0f, 0.0f, 1.0f, 1.0f,
									   		 1.0f, 1.0f, 1.0f, 0.0f,
									   		 0.0f, 1.0f, 0.0f, 0.0f };
	
	private static int vao = -1;
	private static int vbo = -1;
	
	/**
	 * Initializes the renderer
	 */
	public static void init()
	{
		defaultSpriteShader = new SpriteShader();
		defaultColorShader = new ColorShader();
		
		defaultSpriteShader.sampler.loadTexUnit(0);
		
		createVertexArray();
	}

	private static void createVertexArray()
	{
		vao = GL30.glGenVertexArrays();
		GL30.glBindVertexArray(vao);
		
		vbo = GL15.glGenBuffers();
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vbo);
		FloatBuffer buffer = storeDataInFloatBuffer(quadData);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
		GL20.glEnableVertexAttribArray(0);
		GL20.glVertexAttribPointer(0, 2, GL11.GL_FLOAT, false, 16, 0);
		GL20.glEnableVertexAttribArray(1);
		GL20.glVertexAttribPointer(1, 2, GL11.GL_FLOAT, false, 16, 8);
		
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
		GL30.glBindVertexArray(0);
	}
	
	private static FloatBuffer storeDataInFloatBuffer(float[] data)
	{
		FloatBuffer result = BufferUtils.createFloatBuffer(data.length);
		result.put(data);
		result.flip();
		return result;
	}
	
	/**
	 * Cleans up the renderer
	 */
	public static void cleanUp()
	{
		GL15.glDeleteBuffers(vbo);
		GL30.glDeleteVertexArrays(vao);
		
		defaultColorShader.cleanUp();
		defaultSpriteShader.cleanUp();
	}
	
	/**
	 * 
	 * @param x - x position
	 * @param y - y position
	 * @param width - width of the sprite
	 * @param height - height of the sprite
	 * @param texture - texture of the sprite
	 * @param spriteShader - the shader to use for the Sprite.
	 * 				If null it uses the default shader
	 * 
	 * <p>
	 * draws a single image with the default shader
	 */
	public static void drawSprite(float x, float y, float width, float height, Texture texture, SpriteShader spriteShader)
	{
		SpriteShader shader = defaultSpriteShader;
		if (spriteShader != null) shader = spriteShader;
		
		shader.start();
		shader.screenSize.loadVec2(Display.getWidth(), Display.getHeight());
		Matrix4f modelMatrix = new Matrix4f();
		modelMatrix.setIdentity();
		Matrix4f.scale(new Vector3f(width, height, 0.0f), modelMatrix, modelMatrix);
		Matrix4f.translate(new Vector2f(x, y), modelMatrix, modelMatrix);
		
		shader.modelMatrix.loadMatrix(modelMatrix);
		
		GL30.glBindVertexArray(vao);
		
		texture.bind(0);
		GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, 6);
		texture.unbind(0);

		GL30.glBindVertexArray(0);

		shader.stop();
	}

	/**
	 * 
	 * @param x - x position
	 * @param y - y position
	 * @param width - width of the sprite
	 * @param height - height of the sprite
	 * @param color - color of the sprite
	 * @param colorShader - the shader to use for the Sprite.
	 * 				If null it uses the default shader
	 * 
	 * <p>
	 * draws a single image with the default shader
	 */
	public static void drawSprite(float x, float y, float width, float height, Color color, ColorShader colorShader)
	{
		ColorShader shader = defaultColorShader;
		if (colorShader != null) shader = colorShader;
		
		shader.start();
		shader.color.loadVec3(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f);
		shader.screenSize.loadVec2(Display.getWidth(), Display.getHeight());
		Matrix4f modelMatrix = new Matrix4f();
		modelMatrix.setIdentity();
		Matrix4f.scale(new Vector3f(width, height, 0.0f), modelMatrix, modelMatrix);
		Matrix4f.translate(new Vector2f(x, y), modelMatrix, modelMatrix);
		
		shader.modelMatrix.loadMatrix(modelMatrix);
		
		GL30.glBindVertexArray(vao);
		
		GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, 6);

		GL30.glBindVertexArray(0);

		shader.stop();
	}
	
}
