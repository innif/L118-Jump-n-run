package de.L118.game;

import graphics.Texture;
import graphics.renderer.SpriteRenderer;


public class Player {
	private World w;
	private Texture texture;
	private float posX;
	private float posY;
	private int sizeX;
	private int sizeY;
	private float fallSpeed;
	public static final int DISPLAY_POS = 2;
	
	public Player(World w) {
		this.w = w;
		texture = new Texture("res/textures/Player.png");
		posX = 0;
		posY = 6;
		sizeX = World.TILESIZE;
		sizeY = World.TILESIZE;
		fallSpeed = 0;
	}
	
	public void jump() {
		if(overBlock()) {
			fallSpeed = (float) -0.27;
		}
	}
	
	public void update() {
		if(overBlock() && fallSpeed >= 0) {
			fallSpeed = 0;
		}else {
			fallSpeed += 0.01;
		}
		moveUp(-fallSpeed);
	}
	
	public void moveRight(double d) {
		if(!blockRight() && d > 0) {
			posX += d;
			w.setxPos(posX);
		}
		if(!blockLeft() && d < 0) {
			posX += d;
			w.setxPos(posX);
		}
	}
	
	public void moveUp(double d) {
		if(!underBlock() || d < 0) {
			posY += d;
		}else {
			if(fallSpeed <0) {
				fallSpeed = 0;
			}
		}
		
	}
	
	public void draw() {
		SpriteRenderer.drawSprite(DISPLAY_POS*sizeX, posY*sizeY, sizeX, sizeY, texture, null);
	}
	
	private boolean overBlock() {
		return 	w.blockAt((int)Math.round(posX-0.5), (int)(posY)).isBlock() || 
				w.blockAt((int)Math.round(posX+0.5), (int)(posY)).isBlock();
	}
	
	private boolean underBlock() {
		return 	w.blockAt((int)Math.round(posX-0.5), (int)(posY +1)).isBlock() || 
				w.blockAt((int)Math.round(posX+0.5), (int)(posY +1)).isBlock();
	}
	
	private boolean blockRight() {
		return 	w.blockAt((int)Math.round(posX), (int)(posY-0.5)).isBlock() || 
				w.blockAt((int)Math.round(posX), (int)(posY+0.5)).isBlock();
	}
	
	private boolean blockLeft() {
		return 	w.blockAt((int)Math.round(posX-1), (int)(posY-0.5)).isBlock() || 
				w.blockAt((int)Math.round(posX-1), (int)(posY+0.5)).isBlock();
	}
	
}
