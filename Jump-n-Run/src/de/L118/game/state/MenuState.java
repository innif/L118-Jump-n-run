package de.L118.game.state;

import org.lwjgl.opengl.Display;

import de.L118.game.Game;
import gui.Button;
import gui.GUICallback;

public class MenuState extends State
{
	private Button startButton;
	private Button exitButton;
	
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
	
	public void render()
	{
		startButton.render();
		exitButton.render();
	}
	
	public void update()
	{
		startButton.update();
		exitButton.update();
	}
	
}
