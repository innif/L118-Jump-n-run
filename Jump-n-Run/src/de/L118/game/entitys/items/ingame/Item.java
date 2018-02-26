package de.L118.game.entitys.items.ingame;

import de.L118.game.World;
import de.L118.game.entitys.Entity;
import de.L118.game.entitys.items.base.IItem;
import graphics.renderer.world.WorldRenderer;
import utils.storage.map.Tileset;

import java.util.Random;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;

public class Item extends Entity {
	
	public final static Random rnd = new Random();
	
	public static final float
			DEFAULT_FALLSPEED       = 0.054f,
			VELOCITY                = 0.05f;
	
	private Tileset tileset;
	private short   id;
	private short   tilesetID;
	private boolean moves;
	private boolean direction;
	private float   fallspeed;
	
	public Item(World world, float x, float y, float width, float height, short id) {
		
		super(x, y, world, width, height);
		this.id = id;
		IItem item = IItem.getItemByID(id);
		this.tileset = item.getTileset();
		this.tilesetID = item.getTilesetID();
		fallspeed = DEFAULT_FALLSPEED;
		
		PolygonShape polygonShape = new PolygonShape();
		polygonShape.setAsBox(1, 1);
		
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.DYNAMIC;
		bodyDef.position.set(5 * 1, 0);
		bodyDef.angle = (float) (Math.PI / 4 * 1);
		bodyDef.allowSleep = false;
		System.out.println(world.getWorld());
		Body body = world.getWorld().createBody(bodyDef);
		body.createFixture(polygonShape, 5.0f);
		
		body.applyForce(new Vec2(-10000 * (1 - 1), 0), new Vec2());
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
		move();
	}
	
	@Override
	public void draw(WorldRenderer renderer) {
		
		renderer.drawBlock(
				(int) (getX() * World.TILESIZE),
				(int) (getY() * World.TILESIZE),
				(int) (getWidth() * World.TILESIZE),
				(int) (getHeight() * World.TILESIZE),
				tileset,
				tilesetID
						  );
	}
	
	private void move() {
		body.setLinearVelocity(new Vec2(VELOCITY, body.getLinearVelocity().y));
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
