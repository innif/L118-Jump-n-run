package de.L118.game;

public class Blocks {
	int x;
	int y;
	int sizeX;
	int sizeY;
	
	public Blocks(int x,int y,int sizeX,int sizeY) {
		this.x = x;
		this.y = y;
		this.sizeX = sizeX;
		this.sizeY = sizeY;
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
}
