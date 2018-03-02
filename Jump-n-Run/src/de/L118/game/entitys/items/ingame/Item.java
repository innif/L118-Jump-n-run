package de.L118.game.entitys.items.ingame;

import de.L118.game.Player;
import de.L118.game.World;
import de.L118.game.entitys.Entity;
import de.L118.game.entitys.items.base.IItem;
import graphics.renderer.world.WorldRenderer;
import utils.storage.map.Tileset;

import java.util.Random;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.*;

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
		
	}
	
	@Override
	protected void createBody() {
		
		PolygonShape polygonShape = new PolygonShape();
		polygonShape.setAsBox(width, height);
		
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.DYNAMIC;
		bodyDef.position.set(x, y);
		
		body = world.getWorld().createBody(bodyDef);
		FixtureDef fixture = new FixtureDef();
		fixture.shape = polygonShape;
		fixture.density = 5.0f;
		fixture.userData = this;
		//fixture.filter.maskBits = 0x0000;
		
		body.createFixture(fixture);
		
		
	}
	
	public void setMovingInRandomDir() {
		
		setMoving(rnd.nextBoolean());
		
	}
	
	public void setMoving(boolean direction) {
		
		this.direction = direction;
		moves = true;
		
	}
	
	public void onPickup(Player p) {
		IItem.getItemByID(id).onPickup(p);
		body.getWorld().destroyBody(body);
		this.isDead = true;
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
	
}
