package de.L118.game.entitys;

import de.L118.game.World;
import graphics.renderer.world.WorldRenderer;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;

/**
 * Superklasse für alle Entities
 *
 * @author Stefan
 * @author Luca
 * @version 1.0
 */
public abstract class Entity {
	
	/**
	 * Die möglichen Statien, in denen sich ein Entity befinden kann
	 */
	protected enum JumpState
	{
		FALLING,
		JUMPING,
		ONGROUND,
		STOMPING
	}
	
	/**
	 * Status des Entities
	 */
	protected JumpState jumpState;
	
	/**
	 * x und y position des Entities
	 */
	protected float
			x,
			y;
	
	/**
	 * breite und höhe des Entities
	 */
	protected float
			width,
			height;
	
	/**
	 * ob das Entity tot ist oder nicht
	 */
	private boolean isDead;
	
	/**
	 * Welt in der das Entity lebt
	 */
	protected World world;
	/**
	 * JBox2D collisionbox für das Entity
	 */
	protected Body body;
	
	/**
	 * Erstellt ein neues Entity
	 * @param x
	 * 		X Position des Entitiy
	 * @param y
	 * 		Y Position des Entity
	 * @param world
	 * 		Welt in der das Entitiy sich befindet
	 * @param width
	 * 		Breite des Entity (1 = 1 Gitterfeld)
	 * @param height
	 * 		Höhe des ENtity (1 = 1 Gitterfeld)
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
	
	/**
	 * Erstellt die JBox2D collisionbox
	 */
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
	
	/**
	 * @return
	 * 		X Position des Entitymittelpunktes
	 */
	public float getX() {
		
		return body.getPosition().x;
	}
	
	/**
	 * @return
	 * 		Y Position des Entitymittelpunktes
	 */
	public float getY() {
		
		return body.getPosition().y;
	}
	
	/**
	 * @return
	 * 		Welt in dem sich das Objekt befindet
	 */
	public World getWorld() {
		
		return world;
	}
	
	/**
	 * @return
	 * 		Höhe des Objektes
	 */
	public float getHeight() {
		
		return height;
	}
	
	/**
	 * @return
	 * 		Breite des Objektes
	 */
	public float getWidth() {
		
		return width;
	}
	
	/**
	 * Update Methode für die Superklasse um zu prüfen, ob das entity noch lebt oder ob es unterhalb der Welt ist (wenn dann töten des Entities)
	 */
	public void updateEntity() {
		
		if(isDead)
			return;
		
		if(getY()+getHeight() < 0) {
			isDead = true;
			kill();
		}
		
		update();
	}
	
	/**
	 * Mehtode die aufgerufen wird, wenn das Objekt getötet wird
	 */
	abstract public void kill();
	
	/**
	 * Methode die zum Updaten des Objektes aufgerufen wird
	 */
	abstract public void update();
	
	/**
	 * Zeichen Methode für die Superklasse um zu prüfen, ob das Objekt noch lebt (sonst ignorieren)
	 * @param renderer
	 * 		Renderer der Welt, in dem das Entity lebt
	 */
	public void drawEntity(WorldRenderer renderer) {
		if (isDead)
			return;
		
		draw(renderer);
	}
	
	/**
	 * Mehtode die auffgerufen wird, wenn das Objekt gezeichnet werden soll
	 * @param renderer
	 * 		Renderer der Welt, in dem das Entity lebt
	 */
	abstract public void draw(WorldRenderer renderer);
	
}
