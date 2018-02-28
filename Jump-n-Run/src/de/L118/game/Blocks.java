package de.L118.game;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;

import graphics.renderer.world.WorldRenderer;
import utils.storage.map.Tileset;

public class Blocks {
	private int     x;
	private int     y;
	private int     sizeX;
	private int     sizeY;
	private Tileset tileset;
	private short   type;
	
	private Body body;
	
	public Blocks(int x, int y, short type, Tileset tileset) {
		this.tileset = tileset;
		this.x = x;
		this.y = y;
		this.sizeX = 1;
		this.sizeY = 1;
		this.type = type;
	}
	
	public void createPhysics(World world)
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
	
	public Blocks() {
		type = 0;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getSizeX() {
		return sizeX;
	}
	
	public int getSizeY() {
		return sizeY;
	}
	
	public void draw(WorldRenderer renderer) {
		if(type != 0) {
			renderer.drawBlock(x * World.TILESIZE, y * World.TILESIZE, sizeX * World.TILESIZE, sizeY * World.TILESIZE, tileset, type);
		}
	}
	
	public boolean isBlock() {
		return type != 0;
	}
	
	public short getType() {
		return type;
	}
}
