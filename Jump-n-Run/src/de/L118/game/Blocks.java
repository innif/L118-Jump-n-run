package de.L118.game;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;

import graphics.renderer.world.WorldRenderer;
import utils.storage.map.Tileset;

/**
 * Block Objekte der Map
 *
 * @author Luca
 * @author Stefan
 * @author Finn
 *
 * @version 1.0
 */
public class Blocks {
	
	/**
	 * x position der Blöcke
	 */
	private int     x;
	/**
	 * y position der Blöcke
	 */
	private int     y;
	/**
	 * Breite der Blöcke
	 * 1 = 1 Gitterfeld
	 */
	private float   sizeX;
	/**
	 * Höhe der Blöcke
	 * 1 = 1 Gitterfeld
	 */
	private float   sizeY;
	/**
	 * Tileset in welchem sich die Textur des Blockes befindet
	 */
	private Tileset tileset;
	/**
	 * ID der Textur innerhalb des Tilesets
	 */
	private short   type;
	
	/**
	 * JBox2D hitbox des Blockes
	 */
	private Body body;
	
	/**
	 * Erzeugt einen neuen Block
	 *
	 * @param x
	 * 		x Position des Blockes
	 * @param y
	 * 		y Position des Blockes
	 * @param type
	 * 		ID der Texture des Blockes im Tileset
	 * @param tileset
	 * 		Tileset in dem sich die Textur des Blockes befindet
	 */
	public Blocks(int x, int y, short type, Tileset tileset) {
		this.tileset = tileset;
		this.x = x;
		this.y = y;
		this.sizeX = 1;
		this.sizeY = 1;
		this.type = type;
	}
	
	/**
	 * Erstellt die Physik für den Block (JBox2D)
	 *
	 * @param world
	 * 		Welt in der der Block steht
	 */
	private void createPhysics(World world)
	{
		BodyDef def = new BodyDef();
		def.type = BodyType.STATIC;
		def.position.set(x,y);
		def.fixedRotation = true;
		
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(sizeX / 2.0f,sizeY / 2.0f);
		
		body = world.getWorld().createBody(def);
		body.createFixture(shape, 1.0f);
	}
	
	/**
	 * Constructor für Luftblöcke
	 */
	public Blocks() {
		type = 0;
	}
	
	/**
	 * @return
	 * 		X Position des Blockes
	 */
	public int getX() {
		return x;
	}
	
	/**
	 *
	 * @return
	 * 		Y Position des Blockes
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * Gibt die Breite des Blockes zurück
	 * @return
	 * 		Breite des Blockes (1 = 1 Gitterfeld)
	 */
	public float getSizeX() {
		return sizeX;
	}
	
	/**
	 * Gibt die Höhe des Blockes zurück
	 *
	 * @return
	 * 		Höhe des Blockes (1 = 1 Gitterfeld)
	 */
	public float getSizeY() {
		return sizeY;
	}
	
	/**
	 * Methode zum Zeichnen des Blockes
	 *
	 * @param renderer
	 * 		WorldRenderer in dem der Block gezeichnet werden soll
	 */
	public void draw(WorldRenderer renderer) {
		if(type != 0) {
			renderer.drawBlock(x * World.TILESIZE, y * World.TILESIZE, sizeX * World.TILESIZE, sizeY * World.TILESIZE, tileset, type);
		}
	}
	
	/**
	 * Gibt zurück ob der Block ein fester Block oder ein Luftblock ist
	 *
	 * @return
	 * 		true falls es ein Block ist
	 */
	public boolean isBlock() {
		return type != 0;
	}
	
	/**
	 * @return
	 * 		ID der Texture im Tileset zurück
	 */
	public short getType() {
		return type;
	}
}
