package de.L118.game;

import de.L118.game.state.MenuState;
import de.L118.game.state.State;
import graphics.Graphics;
import graphics.renderer.SpriteRenderer;
import graphics.renderer.font.TextRenderer;
import utils.input.Input;

/**
 * 
 * @author james_000
 *
 *	Die Game-Klasse
 *	Hauptklasse des Spiels.
 *	Game-loop, update- and render-methode in dieser klasse
 */
public class Game {

	private boolean running;
	long timer;
	
	public Game()
	{
		
	}
	
	/**
	 * Initialisierung des Spiels
	 */
	private void init()
	{
		SpriteRenderer.init();
		TextRenderer.init();
		
		State.currentState = new MenuState(this);
	}
	
	/**
	 * Cleans up the game
	 */
	private void cleanUp()
	{
		TextRenderer.cleanUp();
		SpriteRenderer.cleanUp();
	}
	
	/**
	 * 
	 * Updating des Spiels
	 */
	private void update()
	{
		if (State.currentState != null)
			State.currentState.update();
	}
	
	/**
	 * 
	 * Rendering des Spiels
	 * 
	 */
	private void render()
	{
		if (State.currentState != null)
			State.currentState.render();
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
	 * Schlie�t das Spiel
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
		
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double ns = 1000000000.0 / 60.0;
		double delta = 0;
		int fps = 0;
		int ups = 0;
		
		while (running)
		{
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if (delta >= 1) 
			{
				update();
				Input.update();
				ups++;
				delta--;
			}
			Graphics.clearWindow();
			
			render();
			fps++;
			
			Graphics.updateWindow();
			if (System.currentTimeMillis() - timer > 1000)
			{
				timer += 1000;
				System.out.println("FPS: " + fps + " UPS: " + ups);
				ups = fps = 0;
			}
			running = Graphics.windowOpen() && running;
		}
		cleanUp();
	}
	
}
