package de.L118.game;

import de.L118.game.entitys.Entity;
import graphics.Texture;
import graphics.renderer.SpriteRenderer;
import graphics.renderer.world.WorldRenderer;
import utils.input.Input;

import java.awt.*;

import org.jbox2d.common.Vec2;
import org.lwjgl.input.Keyboard;


public class Player extends Entity{
	
	private static final float
		VELOCITY = 1f,
		FLOATING_DIST = 0.0001f,
		VERTICAL_ACCELERATION = 0.02f;
	
	private World w;
	private Texture texture;
	
	private float fallSpeed;
	public static final int DISPLAY_POS = 2;
	
	public Player(World w) {
		super(1f,1,w,1,1);
		this.w = w;
		texture = new Texture("res/textures/Player.png");
		fallSpeed = 0;
	}
	
	public void update() {
		
		move(0.0f);
		
		if (Input.isKeyPressed(Keyboard.KEY_SPACE) || Input.isKeyPressed(Keyboard.KEY_W))
			jump(6.5f);
		if(Input.isKeyDown(Keyboard.KEY_A))
			move(-3f);
		if(Input.isKeyDown(Keyboard.KEY_D))
			move(3f);
		
		//w.setxPos((getX()-DISPLAY_POS)*100);
	}
	
	/*
		4.6f ~ 2 Blöcke
		6.5f ~ 3 Blöcke
	
	 */
	
	public void jump(float vel)
	{
		if (body.getLinearVelocity().y == 0)
			body.setLinearVelocity(new Vec2(body.getLinearVelocity().x,vel));
		//body.applyForceToCenter(new Vec2(0.0f, vel));
	}
	
	public void move(float vel) {
		body.applyLinearImpulse(new Vec2(vel - body.getLinearVelocity().x, 0.0f), body.getWorldCenter());
		//body.setLinearVelocity(new Vec2(vel,body.getLinearVelocity().y));
	}
	
	public void draw(WorldRenderer renderer) {
		
		SpriteRenderer.drawSprite(getX() * World.TILESIZE, getY() * World.TILESIZE, getWidth() * World.TILESIZE, getHeight() * World.TILESIZE, texture, null);
	}
	
}
