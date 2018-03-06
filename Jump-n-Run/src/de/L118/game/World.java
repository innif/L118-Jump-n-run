package de.L118.game;

import java.util.ArrayList;

import org.jbox2d.common.Vec2;

import de.L118.game.entitys.Entity;
import graphics.renderer.world.WorldRenderer;
import utils.storage.map.MapStruct;

/**
 * Welt Klasse zum Handling von allen Welt betreffenden dingen
 * @author Stefan
 * @author Finn
 * @author Luca
 * @version 1.0
 */
public class World {
	
	/**
	 * Größe von Blöcken/Koordinatengitter
	 */
	public static final int TILESIZE = 50;
	/**
	 * Blöcke
	 */
	private Blocks[][][] blocks; // [ebene][x][y]
	/**
	 * x Position der Welt (für die Verschiebung hinsichtlich des Fensters)
	 */
	float xPos;
	/**
	 * y Position der Welt (für die Verschiebung hinsichtlich des Fensters)
	 */
	float yPos;
	
	/**
	 * Derzeit benutzte Welt
	 */
	static World currentWorld;
	/**
	 * JBox2D Welt
	 */
	private org.jbox2d.dynamics.World world;
	
	/**
	 * Liste aller Entities.
	 * Derzeit nur der Spieler
	 */
	private ArrayList<Entity> entities;
	/**
	 * Renderer für die Welt
	 */
	private WorldRenderer     renderer;
	/**
	 * Layer, der für die Collisions benutzt wird.
	 */
	private int               collisionLayer;
	
	/**
	 * Generiert die Welt
	 * @param mapInfo
	 * 		{@link MapStruct} mit allen nötigen Informationen
	 */
	public World(MapStruct mapInfo)
	{
		currentWorld = this;
		this.world = new org.jbox2d.dynamics.World(new Vec2(0.0f,-20f));
		
		blocks = mapInfo.blocks;
		entities = new ArrayList<>();
		this.collisionLayer = mapInfo.collisionLayer;
		
		for(int i = 0 ; i < blocks[collisionLayer].length; i++) {
			Blocks[] b = blocks[collisionLayer][i];
			for(int j = 0; j < b.length;j++) {
				if (b[j].getType() != 0)
					b[j].createPhysics(this);
			}
		}
		
		xPos = 0.0f;
		yPos = 0.0f;
		
		renderer = new WorldRenderer();
	}
	
	/**
	 * Updatet alle Entities
	 * Wird optimalerweise 60 mal pro Sekunde aufgerufen
	 */
	public void update() {
		
		for (Entity entity : entities) {
			entity.updateEntity();
		}
		world.step(1.0f / 60.0f, 6, 2);
	}
	
	/**
	 * Rendert die Welt und ruft die Render funktion aller Entites auf
	 */
	public void render() {
		renderer.beginWorld((int) (xPos), (int) (yPos));
		
		for(int z = 0; z < blocks.length; z++) {
			for (int x = 0 ; x < blocks[z].length ; x++) {
				for (int y = 0 ; y < blocks[z][x].length ; y++) {
					if (blocks[z][x][y].isBlock())
						blocks[z][x][y].draw(renderer);
					
				}
			}
		}
		
		entities.forEach(entity -> entity.drawEntity(renderer));
		
		renderer.endWorld();
		renderer.showWorld();
		
	}
	
	/**
	 * Gibt den Block an gegebener Position zurück
	 *
	 * @param x
	 * 		X Position des Blockes
	 * @param y
	 * 		Y Position des Blockes
	 * @return
	 * 		Block an der Position
	 */
	public Blocks blockAt(int x, int y) {
		if(x>= blocks[0].length || y>= blocks[0][0].length || x<0 || y<0 || blocks[0][x][y] == null) {
			return new Blocks();
		}else {
			return blocks[collisionLayer][x][y];
		}
	}
	
	/**
	 * Fügt ein Entity der Liste hinzu.
	 *
	 * @param entity
	 * 		Entitiy, dass der Welt hinzugefügt werden soll
	 */
	public void addEntity(Entity entity) {
		entities.add(entity);
	}
	
	/**
	 * Gibt die JBox2D Welt zurück
	 *
	 * @return
	 * 		Die JBox2D Welt
	 */
	public org.jbox2d.dynamics.World getWorld() {
		return world;
	}
}
