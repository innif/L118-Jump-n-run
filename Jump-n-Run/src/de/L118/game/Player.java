package de.L118.game;

import graphics.Texture;
import graphics.renderer.Renderer;

public class Player {
	World w;
	Texture texture;
	float posX;
	float posY;
	int sizeX;
	int sizeY;
	float fallSpeed;
	
	public Player(World w) {
		this.w = w;
		texture = new Texture("res/textures/test.png");
		posX = 0;
		posY = 6;
		sizeX = 100;
		sizeY = 100;
		fallSpeed = 0;
	}
	
	public void jump() {
		if(w.blockAt(Math.round(posX), (int)(posY)).isBlock()) {
			fallSpeed = (float) -0.1;
		}
	}
	
	public void update() {
		if(w.blockAt((int)posX, (int)(posY )).isBlock() && fallSpeed >= 0) {
			fallSpeed = 0;
		}else {
			fallSpeed += 0.001;
		}
		posY -= fallSpeed;
	}
	
	void moveLeft(double distance) {
		posX -= distance;
		w.setxPos(posX);
	}
	
	public void moveRight(double d) {
		posX += d;
		w.setxPos(posX);
	}
	
	public void draw() {
		Renderer.drawSprite(2*sizeX, posY*sizeY, sizeX, sizeY, texture, null);
	}
	
}
