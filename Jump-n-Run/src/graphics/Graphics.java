package graphics;

import java.awt.Color;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

import graphics.renderer.Renderer;

public class Graphics {

	/**
	 * 
	 * @param width of the window
	 * @param height of the window
	 * @param title of the window
	 * @param fullscreen - wether the window is in fullscreen mode or not
	 */
	public static void createWindow(int width, int height, String title, boolean fullscreen)
	{
		try {
			Display.setDisplayMode(new DisplayMode(width, height));
			Display.setFullscreen(fullscreen);
			Display.setTitle(title);
			Display.setResizable(false);
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 	Clears all of the window
	 */
	public static void clearWindow()
	{
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
	}
	
	/**
	 * Updates the window buffer and the input
	 */
	public static void updateWindow()
	{
		Display.update();
	}
	
	/**
	 * 
	 * @return wether the window is still alive or not 
	 */
	public static boolean windowOpen()
	{
		return !Display.isCloseRequested();
	}
	
	/**
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param color
	 * 
	 * Fills a colored rect
	 */
	public static void fillRect(float x, float y, float width, float height, Color color)
	{
		Renderer.drawSprite(x, y, width, height, color, null);
	}
	
	
	/**
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param texture
	 * 
	 * Draws a texture
	 */
	public static void drawTexture(float x, float y, float width, float height, Texture texture)
	{
		Renderer.drawSprite(x, y, width, height, texture, null);
	}
	
}
