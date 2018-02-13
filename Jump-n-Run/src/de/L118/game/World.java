package de.L118.game;

import org.lwjgl.input.Keyboard;

import de.L118.game.entitys.Entity;
import de.L118.game.entitys.items.base.IItem;
import de.L118.game.entitys.items.base.TestItem;
import de.L118.game.entitys.items.ingame.Item;
import graphics.renderer.world.WorldRenderer;
import utils.input.Input;
import utils.storage.map.MapStruct;

import java.util.ArrayList;

public class World {
	
	public static final int TILESIZE = 100;
	private Blocks[][][] blocks; // [ebene][x][y]
	private float xPos;
	private short[][] types = {
			{0,0,0,0,2,1,1,1,1,1,1,3,0,0,0,0,0,0,0,0,0,0,0,0},	
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},	
			{0,0,0,0,0,0,0,0,2,1,1,3,0,0,0,0,0,0,0,0,0,0,0,0},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,3,0,0,0,2,1,3,0,2}
	};
	
	private ArrayList<Entity> entities;
	private WorldRenderer     renderer;
	private int               collisionLayer;
	
	public World(MapStruct mapInfo)
	{
		blocks = mapInfo.blocks;
		entities = new ArrayList<>();
		this.collisionLayer = mapInfo.collisionLayer;
		
		xPos = 0.0f;
		
		IItem testItem = new TestItem();
		Item  item     = new Item(this, 2, 2, 1, 1, testItem.getID());
		item.setMoving(true);
		entities.add(item);
		
		
		renderer = new WorldRenderer();
	}
	
	public boolean isObject(int x, int y) {
		//TODO
		return false;
	}
	
	void moveLeft(double distance) {
		xPos -= distance;
	}
	
	public void moveRight(double d) {
		xPos += d;
	}
	
	public void setxPos(float xPos) {
		this.xPos = xPos;
	}
	
	public float getxPos() {
		return xPos;
	}
	
	public void loadWorld() {
	}
	
	public void update() {
		if (Input.isKeyDown(Keyboard.KEY_D))
			xPos++;
		entities.forEach(Entity::updateEntity);
	}
	
	public void render() {
		renderer.beginWorld((int) (xPos - Player.DISPLAY_POS), 0);
		
		for(int z = 0; z < blocks.length; z++) {
			for (int x = 0 ; x < blocks[z].length ; x++) {
				for (int y = 0 ; y < blocks[z][x].length ; y++) {
					if (blocks[z][x][y] != null)
						blocks[z][x][y].draw(renderer);
					
				}
			}
		}
		
		entities.forEach(entity -> entity.drawEntity(renderer));
		
		renderer.endWorld();
		renderer.showWorld();
		
	}
	
	public Blocks blockAt(int x, int y) {
		if(x>= blocks[0].length || y>= blocks[0][0].length || x<0 || y<0 || blocks[0][x][y] == null) {
			return new Blocks();
		}else {
			return blocks[collisionLayer][x][y];
		}
	}
}
