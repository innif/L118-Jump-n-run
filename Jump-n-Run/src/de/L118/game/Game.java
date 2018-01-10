package de.L118.game;

import java.awt.Color;

import graphics.Graphics;
import graphics.Texture;
import graphics.renderer.Renderer;

/**
 * 
 * @author james_000
 *
 *	Die Game-Klasse
 *	Hauptklasse des Spiels.
 *	Game-loop, update- und render-methode in dieser klasse
 */
public class Game {

	private boolean running;
	private Texture texture;
	
	Player p;
	World w;
	
	public Game()
	{
		w = new World();
		p = new Player(w);
	}
	
	/**
	 * Initialisierung des Spiels
	 */
	private void init()
	{
		Renderer.init();
		texture = new Texture("res/textures/test.png");
	}
	
	/**
	 * Cleans up the game
	 */
	private void cleanUp()
	{
		Renderer.cleanUp();
	}
	
	/**
	 * 
	 * Updating des Spiels
	 */
	private void update()
	{
		
	}
	
	/**
	 * 
	 * Rendering des Spiels
	 * 
	 */
	private void render()
	{
		Renderer.drawSprite(0, 0, 100, 100, texture, null);
	}
	
	/**
	 *
	 * 	Start und Initialisierung des Spiels
	 *  
	 */
	public void start()
	{
		running = true;
		run();
	}
	
	/**
	 * 
	 * Schlieﬂt das Spiel
	 * 
	 */
	public void stop()
	{
		running = false;
	}
	
	/**
	 * 
	 * Die game-loop des spiels
	 * TODO: Timer
	 */
	private void run()
	{
		Graphics.createWindow(1270, 720, "Jump n Run", false);
		init();
		while (running)
		{
			update();
			
			Graphics.clearWindow();
			render();
			
			Graphics.updateWindow();
			running = Graphics.windowOpen();
		}
		cleanUp();
	}
	
}
