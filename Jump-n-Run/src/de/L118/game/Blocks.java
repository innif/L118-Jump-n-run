package de.L118.game;

import graphics.Texture;
import graphics.renderer.Renderer;

public class Blocks {
	private int x;
	private int y;
	private int sizeX;
	private int sizeY;
	private Texture texture;
	private short type;
	
	public Blocks(int x,int y,int sizeX,int sizeY,short type) {
		texture = new Texture("res/textures/middle_block.png");
		this.x = x;
		this.y = y;
		this.sizeX = sizeX;
		this.sizeY = sizeY;
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
	
	public void draw(float xPos) {
		if(type != 0) {
			Renderer.drawSprite((x-xPos)*sizeX, y*sizeY, sizeX, sizeY, texture, null);
		}
	}
	
	public boolean isBlock() {
		return type != 0;
	}
}
