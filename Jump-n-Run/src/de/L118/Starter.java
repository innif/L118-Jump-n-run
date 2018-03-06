package de.L118;

import de.L118.game.Game;

/**
 * Startet das Spiel
 */
public class Starter {
	
	/**
	 * Startet das Spiel
	 *
	 * @param args
	 * 		Cmd übergebene Argumente
	 */
	public static void main(String[] args)
	{
		
		Game game = new Game();
		game.start();
		
		
	}
	
}
