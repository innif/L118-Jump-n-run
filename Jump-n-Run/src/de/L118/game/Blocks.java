package de.L118.game;

import graphics.Texture;
import graphics.renderer.Renderer;

public class Blocks {
	int x;
	int y;
	int sizeX;
	int sizeY;
	private Texture texture;
	byte type;
	
	public Blocks(int x,int y,int sizeX,int sizeY) {
		texture = new Texture("res/textures/middle_block.png");
		this.x = x;
		this.y = y;
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		type = 1;
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
	
	public void draw(float xPos) {
		Renderer.drawSprite((x-xPos)*sizeX, y*sizeY, sizeX, sizeY, texture, null);
	}
	
	public boolean isBlock() {
		return type != 0;
	}
}
