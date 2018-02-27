package graphics;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.IntBuffer;

import javax.imageio.ImageIO;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;

public class Texture {

	private int width, height;
	private int id;
	
	private int filterMode;
	private int[] data;
	
	public Texture(String path)
	{
		int[] pixels = null;
		try {
			BufferedImage image = ImageIO.read(new FileInputStream(path));
			width = image.getWidth();
			height = image.getHeight();
			pixels = new int[width * height];
			image.getRGB(0, 0, width, height, pixels, 0, width);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		data = new int[width * height];
		for (int i = 0; i < width * height; i++)
		{
			int a = (pixels[i] & 0xff000000) >> 24;
			int r = (pixels[i] & 0xff0000) >> 16;
			int g = (pixels[i] & 0xff00) >> 8;
			int b = (pixels[i] & 0xff);
			
			data[i] = a << 24 | b << 16 | g << 8 | r;
		}
		
		id = -1;
		filterMode = GL11.GL_NEAREST;
	}
	
	public Texture(String path, int filterMode)
	{
		int[] pixels = null;
		try {
			BufferedImage image = ImageIO.read(new FileInputStream(path));
			width = image.getWidth();
			height = image.getHeight();
			pixels = new int[width * height];
			image.getRGB(0, 0, width, height, pixels, 0, width);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		data = new int[width * height];
		for (int i = 0; i < width * height; i++)
		{
			int a = (pixels[i] & 0xff000000) >> 24;
			int r = (pixels[i] & 0xff0000) >> 16;
			int g = (pixels[i] & 0xff00) >> 8;
			int b = (pixels[i] & 0xff);
			
			data[i] = a << 24 | b << 16 | g << 8 | r;
		}
		
		id = -1;
		this.filterMode = filterMode;
	}
	
	private void create(int filterMode)
	{
		id = GL11.glGenTextures();
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, id);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, filterMode);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, filterMode);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL11.GL_REPEAT);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL11.GL_REPEAT);
		GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, width, height, 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, storeDataInIntBuffer(data));
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
	}
	
	
	private static IntBuffer storeDataInIntBuffer(int[] data)
	{
		IntBuffer result = BufferUtils.createIntBuffer(data.length);
		result.put(data);
		result.flip();
		return result;
	}
	
	/**
	 * @return the ID of the texture
	 */
	public int getID() {
		return id;
	}
	
	
	/**
	 * 
	 * @return width of the texture in pixel
	 */
	public int getWidth()
	{
		return width;
	}
	
	/**
	 * 
	 * @return height of the texture in pixel
	 */
	public int getHeight()
	{
		return height;
	}
	
	/**
	 * @param slot - The opengl-slot the texture is binding to
	 * 
	 * <p>Bind the texture for rendering
	 */
	public void bind(int slot)
	{
		if (id == -1)
			create(this.filterMode);
		GL13.glActiveTexture(GL13.GL_TEXTURE0 + slot);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, id);
	}
	
	public void unbind(int slot)
	{
		GL13.glActiveTexture(GL13.GL_TEXTURE0 + slot);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
	}
	
}
