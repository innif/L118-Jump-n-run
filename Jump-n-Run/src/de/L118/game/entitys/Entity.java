package de.L118.game.entitys;

import de.L118.game.Blocks;
import de.L118.game.World;
import graphics.renderer.world.WorldRenderer;

public abstract class Entity {
	
	private final static float FLOATING_DIST = 0.00001f;
	
	private float
			x,
			y;
	
	private float
			width,
			height;
	
	private boolean isDead;
	
	private float[][] corners = new float[4][2];
	
	private World world;
	
	public Entity(float x, float y, World world, float height, float width) {
		
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.world = world;
		isDead = false;
	}
	
	public float getX() {
		
		return x;
	}
	
	public float getY() {
		
		return y;
	}
	
	public World getWorld() {
		
		return world;
	}
	
	public float getHeight() {
		
		return height;
	}
	
	public float getWidth() {
		
		return width;
	}
	
	public void updateEntity() {
		
		if(isDead)
			return;
		
		if(y+height < 0)
			isDead = true;
		
		update();
	}
	
	abstract public void update();
	
	public void drawEntity(WorldRenderer renderer) {
		if (isDead)
			return;
		
		draw(renderer);
	}
	
	abstract public void draw(WorldRenderer renderer);
	
	/*
		2 := Moved Right
		1 := Block Right
		0 := No Motion
		-1 := Block Left
		-2 := Moved Left
	
	 */
	public int moveRightLeft(float velocity) {
		
		if(velocity == 0)
			return 0;
		
		if(velocity > 0) {
			
			if(!isBlockRight()) {
				Blocks block = getClosestBlockRightLeft(velocity);
				if (block != null) {
					moveRightToBlock(block.getX());
					return 1;
				} else {
					x += velocity;
					return 2;
				}
			} else {
				return 1;
			}
			
		} else {
			
			if(!isBlockLeft()) {
				Blocks block = getClosestBlockRightLeft(velocity);
				if (block != null) {
					moveLeftToBlock(block.getX());
					return -2;
				} else {
					x += velocity;
					return -1;
				}
			} else {
				return -2;
			}
		}
	}
	
	/*
		2 := Travelled up
		1 := Above block
		0 := Velocity = 0
		-1 := Below block
		-2 := Travelled down
	 */
	
	public int moveUpDown(float velocity) {
		
		
		if(velocity == 0)
			return 0;
		
		if(velocity > 0) {
			
			if(!isUnderBlock()) {
				Blocks block = getClosestBlockAboveBelow(velocity);
				if (block != null) {
					moveUnderBlock(block.getY());
					return 1;
				} else {
					y += velocity;
					return 2;
				}
			} else {
				return 1;
			}
			
		} else {
			
			if(!isOnBlock()) {
				Blocks block = getClosestBlockAboveBelow(velocity);
				if (block != null) {
					moveOnBlock(block.getY());
					return -1;
				} else {
					y += velocity;
					return -2;
				}
			} else {
				return -1;
			}
		}
		
	}
	
	private void moveOnBlock(int y) {
		
		this.y = y+1+FLOATING_DIST;
	}
	
	private void moveUnderBlock(int y) {
		this.y = y-height-FLOATING_DIST;
	}
	
	private void moveRightToBlock(int x) {
		this.x = x-width-FLOATING_DIST;
	}
	
	private void moveLeftToBlock(int x) {
		this.x = x+1+FLOATING_DIST;
	}
	
	private Blocks getClosestBlockAboveBelow(float toCheck) {
		
		int multi = (int)(toCheck/Math.abs(toCheck));
		toCheck = Math.abs(toCheck);
		int checks = (int)toCheck;
		
		for (int i = 0 ; i < checks ; i++) {
			for (int m = 0 ; m <= 1 ; m ++) {
				float[] corner = corners[(multi == -1 ? (m == 0 ? 0 : 3) : (m == 0 ? 1 : 2))];
				Blocks thisBlock = getBlock(corner[0] + width * m, corner[1] + (i+FLOATING_DIST) * multi);
				if (thisBlock.isBlock()) {
					return thisBlock;
				}
				
			}
		}
		
		for (int m = 0 ; m <= 1 ; m ++) {
			float[] corner = corners[(multi == -1 ? (m == 0 ? 0 : 3) : (m == 0 ? 1 : 2))];
			Blocks thisBlock = getBlock(corner[0] + width * m, corner[1] + toCheck * multi);
			if (thisBlock.isBlock()) {
				return thisBlock;
			}
		}
		
		return null;
		
	}
	
	private Blocks getClosestBlockRightLeft(float toCheck) {
		
		int multi = (int)(toCheck/Math.abs(toCheck));
		toCheck = Math.abs(toCheck);
		
		for (int i = 0 ; i < toCheck ; i++) {
			for (int m = 0 ; m <= 1 ; m ++) {
				float[] corner = corners[(multi == -1 ? (m == 0 ? 0 : 1) : (m == 0 ? 2 : 3))];
				Blocks thisBlock = getBlock(corner[0] + (i+FLOATING_DIST) * multi, corner[1] + height * m);
				if (thisBlock.isBlock()) {
					return thisBlock;
				}
				
			}
		}
		return null;
	}
	
	public boolean isOnBlock() {
		
		updateCorners();
		
		return isBlock(corners[0][0], corners[0][1]-FLOATING_DIST*1.1f) ||
				isBlock(corners[3][0], corners[3][1]-FLOATING_DIST*1.1f);
	}
	
	private boolean isUnderBlock() {
		
		updateCorners();
		
		return isBlock(corners[0][0], corners[0][1]+FLOATING_DIST) ||
				isBlock(corners[3][0], corners[3][1]+FLOATING_DIST);
	}
	
	private boolean isBlockRight() {
		
		updateCorners();
		
		return isBlock(corners[2][0]+FLOATING_DIST, corners[0][1]) ||
				isBlock(corners[3][0]+FLOATING_DIST, corners[3][1]);
	}
	
	private boolean isBlockLeft() {
		
		updateCorners();
		
		return isBlock(corners[0][0]-FLOATING_DIST, corners[0][1]) ||
				isBlock(corners[1][0]-FLOATING_DIST, corners[3][1]);
	}
	
	private boolean isBlock(float x,float y) {
		return getBlock(x,y).isBlock();
	}
	
	private Blocks getBlock(float x, float y) {
		
		return world.blockAt((int)x,(int)y);
	}
	
	private void updateCorners(){
		corners[0][0] = x;
		corners[1][0] = x + height;
		corners[2][0] = x+height;
		corners[3][0] = x;
		
		corners[0][1] = y;
		corners[1][1] = y;
		corners[2][1] = y+height;
		corners[3][1] = y+height;
	}
}
