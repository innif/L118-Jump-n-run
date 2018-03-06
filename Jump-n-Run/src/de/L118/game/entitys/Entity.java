package de.L118.game.entitys;

import de.L118.game.World;
import graphics.renderer.world.WorldRenderer;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;

public abstract class Entity {
	
	protected enum JumpState
	{
		FALLING,
		JUMPING,
		ONGROUND,
		STOMPING
	}
	
	protected JumpState jumpState;
	
	protected float
			x,
			y;
	
	protected float
			width,
			height;
	
	protected boolean isDead;
	
	protected World world;
	protected Body body;
	
	/*
	
		Width: 1 = 1 Block
		Height: 1 = 1 Block
		X: 1 = 1 Block
		Y: 1 = 1 Block
	
	*/
	public Entity(float x, float y, World world, float width, float height) {
		
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.world = world;
		isDead = false;
		createBody();
		
		jumpState = JumpState.FALLING;
	}
	
	protected void createBody()
	{
		BodyDef def = new BodyDef();
		def.type = BodyType.DYNAMIC;
		def.fixedRotation = true;
		def.position.set(x,y);
		
		body = world.getWorld().createBody(def);
		
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(width / 2.0f,height / 2.0f);
		
		
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.density = 0.0f;
		fixtureDef.friction = 1.0f;
		fixtureDef.restitution = 0.0f;
		body.createFixture(fixtureDef);
		
	}
	
	
	public float getX() {
		
		return body.getPosition().x;
	}
	
	public float getY() {
		
		return body.getPosition().y;
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
		
		if(getY()+getHeight() < 0) {
			isDead = true;
			kill();
		}
		
		update();
	}
	
	abstract public void kill();
	abstract public void update();
	
	public void drawEntity(WorldRenderer renderer) {
		if (isDead)
			return;
		
		draw(renderer);
	}
	
	abstract public void draw(WorldRenderer renderer);
	
}
