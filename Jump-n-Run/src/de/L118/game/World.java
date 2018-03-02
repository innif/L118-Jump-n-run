package de.L118.game;

import java.util.ArrayList;

import org.jbox2d.common.Vec2;

import de.L118.game.entitys.Entity;
import de.L118.game.entitys.items.base.IItem;
import de.L118.game.entitys.items.base.TestItem;
import de.L118.game.entitys.items.ingame.Item;
import graphics.renderer.world.WorldRenderer;
import utils.storage.map.MapStruct;

public class World {
	
	public static final int TILESIZE = 50;
	private Blocks[][][] blocks; // [ebene][x][y]
	public float xPos;
	public float yPos;
	
	public static World currentWorld;
	private org.jbox2d.dynamics.World world;
	
	private ArrayList<Entity> entities;
	private WorldRenderer     renderer;
	private int               collisionLayer;
	
	public World(MapStruct mapInfo)
	{
		currentWorld = this;
		this.world = new org.jbox2d.dynamics.World(new Vec2(0.0f,-20f));
		this.world.setContactListener(new IContactListener());
		
		blocks = mapInfo.blocks;
		entities = new ArrayList<>();
		this.collisionLayer = mapInfo.collisionLayer;
		
		for(int i = 0 ; i < blocks[collisionLayer].length; i++) {
			Blocks[] b = blocks[collisionLayer][i];
			for(int j = 0; j < b.length;j++) {
				if (b[j].getType() != 0)
					b[j].createPhysics(this);
			}
		}
		
		xPos = 0.0f;
		yPos = 0.0f;
		
		//IItem testItem = new TestItem();
		//Item  item     = new Item(this, 2, 1, 1, 1, testItem.getID());
		//item.setMoving(true);
		//entities.add(item);
		
		renderer = new WorldRenderer();
	}
	
	public void onPickup(Item item, Player p) {
		item.onPickup(p);
		entities.remove(item);
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
		entities.forEach(Entity::updateEntity);
		world.step(1.0f / 60.0f, 6, 2);
	}
	
	public void render() {
		renderer.beginWorld((int) (xPos), (int) (yPos));
		
		for(int z = 0; z < blocks.length; z++) {
			for (int x = 0 ; x < blocks[z].length ; x++) {
				for (int y = 0 ; y < blocks[z][x].length ; y++) {
					if (blocks[z][x][y].isBlock())
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
	
	public org.jbox2d.dynamics.World getWorld() {
		return world;
	}
}
