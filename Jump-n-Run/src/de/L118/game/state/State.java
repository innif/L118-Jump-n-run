package de.L118.game.state;

/**
 * 
 * @author james_000
 * 
 * Ein bestimmter State des Spiels. Das currentState wird gerendert und geupdatet.
 *
 */
public abstract class State
{
	//Das Momentan Aktive State
	public static State currentState;

	public State()
	{
		
	}
	
	public abstract void render();
	public abstract void update();
	
}
