package de.L118.game;

import de.L118.game.entitys.Entity;
import de.L118.game.state.GameState;
import graphics.Texture;
import graphics.renderer.SpriteRenderer;
import graphics.renderer.world.WorldRenderer;
import utils.input.Input;

import org.jbox2d.callbacks.ContactImpulse;
import org.jbox2d.callbacks.ContactListener;
import org.jbox2d.collision.Manifold;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.contacts.Contact;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;


/**
 * Objekt des Spielers
 *
 * @author Finn
 * @author Luca
 * @author Stefan
 * @version 1.0
 */
public class Player extends Entity{
	
	/**
	 * Welt in der sich der Spieler befindet
	 */
	private World w;
	/**
	 * Textur des Spielers
	 */
	private Texture texture;
	
	/**
	 * Erzeugt den Spieler
	 *
	 * @param w
	 * 		Welt des Spielers
	 */
	public Player(World w) {
		super(10f,7,w,1,1);
		this.w = w;
		texture = new Texture("res/textures/Player.png");
	}
	
	@Override
	/**
	 * Erzeugt die JBox2D hitbox des Spielers
	 */
	protected void createBody()
	{
		BodyDef def = new BodyDef();
		def.type = BodyType.DYNAMIC;
		def.fixedRotation = true;
		def.position.set(x,y);
		
		body = world.getWorld().createBody(def);
		
		PolygonShape shape = new PolygonShape();
		float width = 0.3f;
		Vec2[] points = {
				new Vec2(width/2,-0.5f),
				new Vec2(-width/2,-0.5f),
				new Vec2(-width,-0.2f),
				new Vec2(-width,0.2f),
				new Vec2(width/2,0.45f),
				new Vec2(-width/2,0.45f),
				new Vec2(width,0.2f),
				new Vec2(width,-0.2f)};
		scale(points);
		shape.set(points, points.length);
		
		
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.density = 0.0f;
		fixtureDef.friction = 0.0f;
		fixtureDef.restitution = 0.0f;
		fixtureDef.userData = this;
		body.createFixture(fixtureDef);
		
	}
	
	private void scale(Vec2[] points) {
		for(int i = 0; i < points.length; i++) {
			points[i].x *= getWidth();
			points[i].y *= getHeight();
		}
	}
	
	public void update() {
		
		move(0.0f);
		
		if (Input.isKeyDown(Keyboard.KEY_SPACE) || Input.isKeyDown(Keyboard.KEY_W))
			jump(10.0f);
		if (Input.isKeyPressed(Keyboard.KEY_S))
			stomp(20.0f);
		if(Input.isKeyDown(Keyboard.KEY_A))
			move(-5f);
		if(Input.isKeyDown(Keyboard.KEY_D))
			move(5f);
		
		float xScreen = ((getX() + (Display.getWidth() / 2.0f / World.TILESIZE)) * World.TILESIZE) - w.xPos;
		float yScreen = ((getY() + (Display.getHeight() / 2.0f / World.TILESIZE)) * World.TILESIZE) - w.yPos;
		
		float mx = getWorldAdvanceMultiplier(xScreen);
		float my = getWorldAdvanceMultiplier(yScreen);
		
		w.xPos += (map(0.0f, Display.getWidth(), -6.0f, 6.0f, xScreen - Display.getWidth() / 2.0f)) * Math.min(mx, 1.0f);
		w.yPos += (map(0.0f, Display.getHeight(), -6.0f, 6.0f, yScreen - Display.getHeight() / 2.0f)) * Math.min(my, 1.0f);
		
		if (jumpState == JumpState.JUMPING && body.getLinearVelocity().y < 0.0f)
			jumpState = JumpState.FALLING;
		else if ((jumpState == JumpState.FALLING || jumpState == JumpState.STOMPING) && body.getLinearVelocity().y == 0.0f) {
			if(jumpState == JumpState.STOMPING) {
			
			}
			jumpState = JumpState.ONGROUND;
		}
		
		//w.setxPos((getX()- 12f)* World.TILESIZE);
	}
	
	@Override
	/**
	 * Wirf aufgerufen, wenn der Spieler stirbt
	 */
	public void kill() {
		GameState.currentState = new GameState();
	}
	
	/**
	 *
	 * @param a
	 * @param b
	 * @param c
	 * @param d
	 * @param value
	 * @return
	 */
	public float map(float a, float b, float c, float d, float value)
	{
		//Y = (X-A)/(B-A) * (D-C) + C
		return (value - a) / (b - a) * (d - c) + c;
	}
	
	/**
	 * Magic value, sollte die Geschwindigkeit der Kamerabewegung ändern
	 *
	 * @param x
	 * 		Position des Spielers relativ zum Fenster
	 * @return
	 * 		Magic value
	 */
	private float getWorldAdvanceMultiplier(float x)
	{
		return (200.0f / 1.0f) * x * x;
	}
	
	/**
	 * Lässt den Spieler einen Stomp ausführen
	 *
	 * @param vel
	 * 		Geschwindigkeit des Stomps
	 */
	private void stomp(float vel) {
		
		if (jumpState == JumpState.JUMPING || jumpState == JumpState.FALLING)
		{
			body.applyLinearImpulse(new Vec2(body.getLinearVelocity().x,-vel), body.getWorldCenter());
			jumpState = JumpState.STOMPING;
		}
	}
	
	/**
	 * Lässt den Spieler springen
	 *
	 * @param vel
	 * 		Geschwindigkeit des Sprungs
	 */
	private void jump(float vel)
	{
		if (jumpState == JumpState.ONGROUND)
		{
			body.setLinearVelocity(new Vec2(body.getLinearVelocity().x,vel));
			jumpState = JumpState.JUMPING;
		}
		//body.applyForceToCenter(new Vec2(0.0f, vel));
	}
	
	/**
	 * Bewegt den Spieler nach rechts bzw links
	 *
	 * @param vel
	 * 		Geschwindigkeit des Spielers
	 */
	private void move(float vel) {
		body.applyLinearImpulse(new Vec2(vel - body.getLinearVelocity().x, 0.0f), body.getWorldCenter());
		//body.setLinearVelocity(new Vec2(vel,body.getLinearVelocity().y));
	}
	
	/**
	 * Wird aufgerufen um den Spieler zu zeichnen
	 *
	 * @param renderer
	 * 		Hier egal
	 */
	public void draw(WorldRenderer renderer) {
		
		SpriteRenderer.drawSprite((getX() * World.TILESIZE) - w.xPos, (getY() * World.TILESIZE) - w.yPos, getWidth() * World.TILESIZE, getHeight() * World.TILESIZE, texture, null);
	}
	
}
