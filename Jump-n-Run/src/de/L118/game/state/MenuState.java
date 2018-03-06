package de.L118.game.state;

import org.lwjgl.opengl.Display;

import de.L118.game.Game;
import gui.Button;
import gui.GUICallback;

public class MenuState extends State
{
	private Button startButton;
	private Button exitButton;
	
	/**
	 * Erstellt ein neues Menu-State, welches die Buttons rendert und updatet.
	 * 
	 * @param game - Das momentan laufende Spiel
	 */
	public MenuState(Game game)
	{
		startButton = new Button(Display.getWidth() / 2.0f - 200.0f, 500, 400, 100, "Start Game");
		exitButton = new Button(Display.getWidth() / 2.0f - 200.0f, 100, 400, 100, "Exit Game");
		
		exitButton.addGUICallback(new GUICallback()
		{
			@Override
			public void onPressed()
			{
				game.stop();
			}
		});
		
		startButton.addGUICallback(new GUICallback()
		{
			@Override
			public void onPressed()
			{
				State.currentState = new GameState();
			}
		});
	}
	
	/**
	 *  Rendern der beiden Buttons
	 */
	public void render()
	{
		startButton.render();
		exitButton.render();
	}
	
	/**
	 * Updaten der beiden Buttons
	 */
	public void update()
	{
		startButton.update();
		exitButton.update();
	}
	
}
