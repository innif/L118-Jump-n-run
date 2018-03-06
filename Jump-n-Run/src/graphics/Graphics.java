package graphics;

import java.awt.Color;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.PixelFormat;

import graphics.renderer.SpriteRenderer;

public class Graphics {

	/**
	 * 
	 * @param breite des Fensters
	 * @param höhe des Fensters
	 * @param Titel des Fensters
	 * @param fullscreen - ob das Fenster in Vollbild gespielt wird oder nicht
	 */
	public static void createWindow(int width, int height, String title, boolean fullscreen)
	{
		try {
			Display.setDisplayMode(new DisplayMode(width, height));
			Display.setFullscreen(fullscreen);
			Display.setTitle(title);
			Display.setResizable(false);
			Display.create(new PixelFormat().withSamples(8));
			
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 	Löscht den Inhalt des Fensters
	 */
	public static void clearWindow()
	{
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
	}
	
	/**
	 * Updatet den Input und den Renderbuffer des Fensters
	 */
	public static void updateWindow()
	{
		Display.update();
	}
	
	/**
	 * 
	 * @return Ob das Fenster noch offen ist
	 */
	public static boolean windowOpen()
	{
		return !Display.isCloseRequested();
	}
	
	/**
	 * 
	 * @param x - X Position des Vierecks
	 * @param y - Y Position des Vierecks
	 * @param width - Breite des Vierecks
	 * @param height - Höhe des Vierecks
	 * @param color - Farbe des Vierecks
	 * 
	 * Zeichnet ein einfarbiges Viereck
	 */
	public static void fillRect(float x, float y, float width, float height, Color color)
	{
		SpriteRenderer.drawSprite(x, y, width, height, color, null);
	}
	
	
	/**
	 * 
	 *  @param x - X Position des Vierecks
	 * @param y - Y Position des Vierecks
	 * @param width - Breite des Vierecks
	 * @param height - Höhe des Vierecks
	 * @param color - Textur des Vierecks
	 * 
	 * zeichnet ein texturiertes Viereck
	 */
	public static void drawTexture(float x, float y, float width, float height, Texture texture)
	{
		SpriteRenderer.drawSprite(x, y, width, height, texture, null);
	}
	
}
