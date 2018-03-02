package de.L118.game.state;

import java.io.File;

import de.L118.game.Player;
import de.L118.game.World;
import utils.storage.Config;

public class GameState extends State
{
	
	Player p;
	World w;
	
	public GameState()
	{
		w = new World(Config.getMap(new File("res/maps/level1.json")));
		p = new Player(w);
		w.addEntity(p);
	}
	
	public void render()
	{
		w.render();
		p.draw(null);
	}
	
	public void update()
	{
		//p.jump();
		
		w.update();
		//p.moveRight(0.1);
	}
	

}
 