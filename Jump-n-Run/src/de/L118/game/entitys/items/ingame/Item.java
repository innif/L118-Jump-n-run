package de.L118.game.entitys.items.ingame;

import de.L118.game.World;
import de.L118.game.entitys.NPE;
import de.L118.game.entitys.items.base.IItem;
import graphics.renderer.world.WorldRenderer;
import utils.storage.map.Tileset;

import java.util.Random;

public class Item extends NPE {
	
	public final static Random rnd      = new Random();
	public static final float
			VELOCITY 					= 0.03f,
			ACCELERATION                = 0.005f,
			DEFAULT_FALLSPEED           = 0.054f;
	
	private Tileset tileset;
	private short   id;
	private short   tilesetID;
	private boolean moves;
	private boolean direction;
	private float   fallspeed;
	
	public Item(World world, float x, float y, float height, float width, short id) {
		
		super(x, y, world, height, width);
		this.id = id;
		IItem item = IItem.getItemByID(id);
		this.tileset = item.getTileset();
		this.tilesetID = item.getTilesetID();
		fallspeed = DEFAULT_FALLSPEED;
	}
	
	public void setMovingInRandomDir() {
		
		setMoving(rnd.nextBoolean());
		
	}
	
	public void setMoving(boolean direction) {
		
		this.direction = direction;
		moves = true;
		
	}
	
	@Override
	public void update() {
		
		System.out.println(getY());
		if(!(fallspeed == DEFAULT_FALLSPEED && isOnBlock())) {
			int response = moveUpDown(fallspeed);
			switch (response) {
				case -2:
				case 0:
				case 2:
					fallspeed -= ACCELERATION;
					break;
				case -1:
					System.out.println(response);
					fallspeed = DEFAULT_FALLSPEED;
					break;
				case 1:
					fallspeed = 0;
					break;
			}
		}
		if (moves) {
			int response = moveRightLeft(VELOCITY);
			switch (response) {
				case 1:
				case -1:
					direction = !direction;
					break;
				default:
					break;
			}
		}
	}
	
	@Override
	public void draw(WorldRenderer renderer) {
		
		renderer.drawBlock((int) (getX() * getWidth()*World.TILESIZE), (int) (getY() * World.TILESIZE), World.TILESIZE, World.TILESIZE, tileset, tilesetID);
	}
	
	/*
	
	public Item(float x, float y, short id, World world) {
		
		this.world = world;
		fallspeed = DEFAULT_FALLSPEED;
		this.sizeX = item.getSizeX();
		this.sizeY = item.getSizeY();
		this.x = x;
		this.y = y;
		
	}
	
	
	public float getX() {
		
		return x;
	}
	
	public float getY() {
		
		return y;
	}
	
	public int getSizeX() {
		
		return sizeX;
	}
	
	public int getSizeY() {
		
		return sizeY;
	}
	
	public void draw(WorldRenderer renderer) {
	
	}
	
	public void update() {
	
	}
	
	private void fall() {
		
		if (!isOnBlock()) {
			if(fallspeed < 0) {
				if(isUnderBlock()) {
					fallspeed = 0;
				} else {
					y -= fallspeed;
					fallspeed += ACCELERATION;
				}
			} else {
				y -= fallspeed;
				fallspeed += ACCELERATION;
			}
		} else {
			fallspeed = DEFAULT_FALLSPEED;
		}
	}
	
	private void moveLR() {
		
		if (direction) {
			if(!isBlockRight())
				x += VELOCITY;
			else
				direction = !direction;
		} else {
			if(!isBlockLeft())
				x -= VELOCITY;
			else
				direction = !direction;
		}
		
	}
	
	public boolean isOnBlock() {
		
		return world.blockAt((int) Math.round(x - 0.5), (int) (y)).isBlock() ||
				world.blockAt((int) Math.round(x + 0.5), (int) (y)).isBlock();
	}
	
	private boolean isUnderBlock() {
		return 	world.blockAt((int)Math.round(x-0.5), (int)(y +1)).isBlock() ||
				world.blockAt((int)Math.round(x+0.5), (int)(y +1)).isBlock();
	}
	
	private boolean isBlockRight() {
		return 	world.blockAt((int)Math.round(x+1), (int)Math.round(y)).isBlock();
	}
	
	private boolean isBlockLeft() {
		return 	world.blockAt((int)Math.round(x-1), (int)Math.round(y)).isBlock();
	}
	
	public short getID() {
		
		return id;
	}*/
}
