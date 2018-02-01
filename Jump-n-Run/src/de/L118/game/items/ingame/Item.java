package de.L118.game.items.ingame;

import de.L118.game.World;
import de.L118.game.items.base.IItem;
import graphics.Graphics;
import graphics.Texture;
import graphics.renderer.world.WorldRenderer;
import utils.storage.map.Tileset;

import javax.swing.*;
import java.util.Random;

public class Item {
	
	public final static Random rnd      = new Random();
	public static final float
		    VELOCITY 					= 0.03f,
			ACCELERATION                = 0.005f,
			DEFAULT_FALLSPEED           = -0.04f;
	
	private float   x;
	private float   y;
	private int     sizeX;
	private int     sizeY;
	private Tileset tileset;
	private short   id;
	private short   tilesetID;
	private boolean moves;
	private boolean direction;
	private float   fallspeed;
	private World   world;
	
	public Item(int x, int y, short id, World world) {
		
		this.world = world;
		fallspeed = DEFAULT_FALLSPEED;
		this.id = id;
		IItem item = IItem.getItemByID(id);
		this.tileset = item.getTileset();
		this.tilesetID = item.getTilesetID();
		this.sizeX = item.getSizeX();
		this.sizeY = item.getSizeY();
		this.x = x;
		this.y = y;
		
	}
	
	public void setMovingInRandomDir() {
		
		setMoving(rnd.nextBoolean());
		
	}
	
	public void setMoving(boolean direction) {
		
		this.direction = direction;
		moves = true;
		
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
		
		renderer.drawBlock((int) (x * sizeX), (int) (y * sizeY), sizeX, sizeY, tileset, tilesetID);
	}
	
	public void update() {
		
		fall();
		if (moves) {
			moveLR();
		}
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
	}
}
