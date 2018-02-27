package de.L118.game.state;

public abstract class State
{
	public static State currentState;

	public State()
	{
		
	}
	
	public abstract void render();
	public abstract void update();
	
}
