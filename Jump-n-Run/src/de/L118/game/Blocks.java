package de.L118.game;

import graphics.renderer.world.WorldRenderer;
import utils.storage.map.Tileset;

public class Blocks {
	private int     x;
	private int     y;
	private int     sizeX;
	private int     sizeY;
	private Tileset tileset;
	private short   type;
	
	public Blocks(int x, int y, short type, Tileset tileset) {
		this.tileset = tileset;
		this.x = x;
		this.y = y;
		this.sizeX = World.TILESIZE;
		this.sizeY = World.TILESIZE;
		this.type = type;
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
			renderer.drawBlock(x * sizeX, y * sizeY, sizeX, sizeY, tileset, type);
		}
	}
	
	public boolean isBlock() {
		return type != 0;
	}
	
	public short getType() {
		return type;
	}
}
