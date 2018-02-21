package de.L118.game.entitys;

import de.L118.game.World;
import graphics.renderer.world.WorldRenderer;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;

public abstract class Entity {
	
	private float
			x,
			y;
	
	private float
			width,
			height;
	
	private boolean isDead;
	
	private World world;
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
		BodyDef def = new BodyDef();
		def.type = BodyType.DYNAMIC;
		def.fixedRotation = true;
		def.position.set(x,y);
		
		body = world.getWorld().createBody(def);
		
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(width / 2.0f,height / 2.0f);
		
		
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.density = 1.0f;
		fixtureDef.friction = 0.0f;
		
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
	public void moveRightLeft(float velocity) {
	
	}
	
	/*
		2 := Travelled up
		1 := Above block
		0 := Velocity = 0
		-1 := Below block
		-2 := Travelled down
	 */
	
	public void moveUpDown(float velocity) {
	
	}
	
}
