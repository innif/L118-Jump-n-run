package de.L118.game;

import graphics.Texture;
import graphics.renderer.Renderer;

public class Blocks {
	int x;
	int y;
	int sizeX;
	int sizeY;
	private Texture texture;
	
	public Blocks(int x,int y,int sizeX,int sizeY) {
		texture = new Texture("res/textures/test.png");
		this.x = x;
		this.y = y;
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.texture = texture;
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
	
	public void draw(float pos) {
		Renderer.drawSprite(x-pos, y-pos, sizeX, sizeY, texture, null);
	}
}
