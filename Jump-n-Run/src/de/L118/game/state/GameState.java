package de.L118.game.state;

import java.io.File;

import de.L118.game.Player;
import de.L118.game.World;
import utils.sound.Sound;
import utils.storage.Config;

public class GameState extends State
{
	private static Sound s = new Sound(new File("res/sounds/","menu.wav"));
	Player p;
	World w;
	
	/**
	 * Erzeug eine neues State zum rendern und updaten des Spiels.
	 * Eine Standartmap wird eingeladen
	 */
	public GameState()
	{
		w = new World(Config.getMap(new File("res/maps/level1.json")));
		p = new Player(w);
		w.addEntity(p);
		s.stop();
		s.start();
	}
	
	/**
	 * Rendern des Spielers und der Welt
	 */
	public void render()
	{
		w.render();
		p.draw(null);
	}
	
	/**
	 * Updaten der welt. 
	 * Der Spieler wird automatisch in World.java geupdatet
	 */
	public void update()
	{
		w.update();
	}
	

}
 