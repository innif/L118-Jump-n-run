package de.L118.game;

import graphics.Texture;
import graphics.renderer.Renderer;

public class World {
	
	Blocks[][] blocks;
	float xPos;
	
	public World() {
		blocks = new Blocks[10][10];
		xPos = 0;

		for(int i = 0; i < blocks.length; i++) {
			for(int j = 0; j < blocks[0].length; j++) {
				blocks[i][j] = new Blocks(i,j,100,100);
			}
		}
	}
	
	public boolean isObject(int x, int y) {
		//TODO
		return false;
	}
	
	void moveLeft(int distance) {
		//TODO
	}
	
	public void moveRight(int distance) {
		//TODO
	}
	
	public void loadWorld() {
		//TODO
	}
	
	public void update() {
		
	}
	
	public void render() {
		for(int i = 0; i < blocks.length; i++) {
			for(int j = 0; j < blocks[0].length; j++) {
				blocks[i][j].draw(xPos);
			}
		}
	}
}
