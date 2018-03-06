package graphics.renderer.world;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import graphics.Texture;
import utils.storage.map.Tileset;

public class WorldRenderer
{
	private static final int VERTEX_SIZE = 5;
	private static final int SPRITE_COUNT = 100000;
	private static final int INDEX_COUNT = SPRITE_COUNT * 6;
	private static final int VERTEX_COUNT = SPRITE_COUNT * 4;
	
	private WorldShader shader;
	
	private int worldOffsetX = 0;
	private int worldOffsetY = 0;
	
	private int vao;
	private int vbo;
	private int ibo;
	
	private int vertexCount = 0;
	private int indexCount = 0;
	private float[] vertices = new float[VERTEX_COUNT];
	private List<Texture> textures = new ArrayList<>();
	
	/**
	 * Erstellt einen neuten Welten-Renderer
	 */
	public WorldRenderer()
	{
		init();
		shader = new WorldShader();
		shader.start();
		shader.samplers.loadAsIndex();
		shader.stop();
	}
	
	
	/**
	 * Initialisiert den Welt-Renderer
	 */
	private void init()
	{
		vao = GL30.glGenVertexArrays();
		GL30.glBindVertexArray(vao);
		
		vbo = GL15.glGenBuffers();
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vbo);
		
		GL20.glEnableVertexAttribArray(0);
		GL20.glVertexAttribPointer(0, 2, GL11.GL_FLOAT, false, 5 * 4, 0);
		GL20.glEnableVertexAttribArray(1);
		GL20.glVertexAttribPointer(1, 2, GL11.GL_FLOAT, false, 5 * 4, 2 * 4);
		GL20.glEnableVertexAttribArray(2);
		GL20.glVertexAttribPointer(2, 1, GL11.GL_FLOAT, false, 5 * 4, 4 * 4);

		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
		
		ibo = GL15.glGenBuffers();
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, ibo);
		
		int[] indices = new int[INDEX_COUNT];
		int offset = 0;
		for (int i = 0; i < INDEX_COUNT; i += 6)
		{
			indices[  i  ] = offset + 0;
			indices[i + 1] = offset + 1;
			indices[i + 2] = offset + 2;
			
			indices[i + 3] = offset + 2;
			indices[i + 4] = offset + 3;
			indices[i + 5] = offset + 0;
			
			offset += 4;
		}
		
		IntBuffer indexBuffer = storeDataInIntBuffer(indices);
		GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER,indexBuffer, GL15.GL_STATIC_DRAW);
		
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
		GL30.glBindVertexArray(0);
	}
	
	private static FloatBuffer storeDataInFloatBuffer(float[] data)
	{
		FloatBuffer result = BufferUtils.createFloatBuffer(data.length);
		result.put(data).flip();
		return result;
	}
	
	private static IntBuffer storeDataInIntBuffer(int[] data)
	{
		IntBuffer result = BufferUtils.createIntBuffer(data.length);
		result.put(data);
		result.flip();
		return result;
	}
	
	/**
	 * Bereitet die Welt auf das Zeichnen vor
	 * 
	 * @param worldOffsetX - Die Kamera X-Position
	 * @param worldOffsetY - Die Kamera Y-Position
	 */
	public void beginWorld(int worldOffsetX, int worldOffsetY)
	{
		vertexCount = 0;
		indexCount = 0;
		this.worldOffsetX = worldOffsetX;
		this.worldOffsetY = worldOffsetY;
	}
	
	/**
	 * Beendet das Zeichnen der Welt
	 */
	public void endWorld()
	{
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vbo);
		
		FloatBuffer buffer = storeDataInFloatBuffer(vertices);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);

		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
	}
	
	/**
	 * Zeigt die Welt auf dem Bildschirm
	 */
	public void showWorld()
	{
		shader.start();
		shader.screenSize.loadVec2(Display.getWidth(), Display.getHeight());
		shader.worldOffset.loadVec2(worldOffsetX, worldOffsetY);
		
		GL30.glBindVertexArray(vao);
		
		for (int i = 0; i < textures.size(); i++)
		{
			textures.get(i).bind(i);
		}
		
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, ibo);

		GL11.glDrawElements(GL11.GL_TRIANGLES, indexCount, GL11.GL_UNSIGNED_INT, 0);
		
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
		
		GL30.glBindVertexArray(0);
		shader.stop();
	}
	
	/**
	 * Zeichnet einen Block
	 * 
	 * @param x - X-Position des Blockes
	 * @param y - Y-Position des Blockes
	 * @param width - Breite des Blockes
	 * @param height - Höhe des Blockes
	 * @param tileSet - Das TileSet mit welchem der block gezeichnet weird
	 * @param id - Die ID des Blockes
	 */
	public void drawBlock(float x, float y, float width, float height, Tileset tileSet, int id)
	{
		int textureWidth = tileSet.getImage().getWidth();
		int textureHeight = tileSet.getImage().getHeight();
		int tileWidth = textureWidth / tileSet.getColumns();
		int tileHeight = textureHeight / tileSet.getRows();
		
		int tileX = (id - 1) % tileSet.getColumns();
		int tileY = (id - 1) / tileSet.getColumns();
		
		float t = ((float)tileWidth * (float)tileX) / ((float)textureWidth);
		float c = ((float)tileHeight * (float)tileY) / ((float)textureHeight);
		
		
		float relSizeX = (float) tileWidth / (float)textureWidth;
		float relSizeY = (float) tileHeight / (float)textureHeight;
		
		float tid = addTileSet(tileSet);
		
		SpriteData spriteData = new SpriteData(x, y, t, c + relSizeY, tid);
		vertices[vertexCount * VERTEX_SIZE] = spriteData.x;
		vertices[vertexCount * VERTEX_SIZE + 1] = spriteData.y;
		vertices[vertexCount * VERTEX_SIZE + 2] = spriteData.t;
		vertices[vertexCount * VERTEX_SIZE + 3] = spriteData.c;
		vertices[vertexCount * VERTEX_SIZE + 4] = spriteData.tid;
		vertexCount++;
		
		spriteData = new SpriteData(x + width, y, t + relSizeX, c + relSizeY, tid);
		vertices[vertexCount * VERTEX_SIZE] = spriteData.x;
		vertices[vertexCount * VERTEX_SIZE + 1] = spriteData.y;
		vertices[vertexCount * VERTEX_SIZE + 2] = spriteData.t;
		vertices[vertexCount * VERTEX_SIZE + 3] = spriteData.c;
		vertices[vertexCount * VERTEX_SIZE + 4] = spriteData.tid;
		vertexCount++;
		
		spriteData = new SpriteData(x + width, y + height, t + relSizeX, c, tid);
		vertices[vertexCount * VERTEX_SIZE] = spriteData.x;
		vertices[vertexCount * VERTEX_SIZE + 1] = spriteData.y;
		vertices[vertexCount * VERTEX_SIZE + 2] = spriteData.t;
		vertices[vertexCount * VERTEX_SIZE + 3] = spriteData.c;
		vertices[vertexCount * VERTEX_SIZE + 4] = spriteData.tid;
		vertexCount++;
		
		spriteData = new SpriteData(x, y + height, t, c, tid);
		vertices[vertexCount * VERTEX_SIZE] = spriteData.x;
		vertices[vertexCount * VERTEX_SIZE + 1] = spriteData.y;
		vertices[vertexCount * VERTEX_SIZE + 2] = spriteData.t;
		vertices[vertexCount * VERTEX_SIZE + 3] = spriteData.c;
		vertices[vertexCount * VERTEX_SIZE + 4] = spriteData.tid;
		vertexCount++;
		
		indexCount += 6;
	}
	
	/**
	 * Fügt ein TileSet zum Rendern hinzu
	 * @param tileset - Das TileSet
	 * @return Die float-id des TileSet
	 */
	private float addTileSet(Tileset tileset)
	{
		for (int i = 0; i < textures.size(); i++)
		{
			Texture texture = textures.get(i);
			if (texture.getID() == tileset.getImage().getID())
			{
				return (float)i;
			}
		}
		textures.add(tileset.getImage());
		return (float)textures.size() - 1.0f;
	}
	
}
