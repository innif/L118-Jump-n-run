package gui;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.util.vector.Vector2f;

import graphics.renderer.SpriteRenderer;
import utils.input.Input;


/**
 * 
 * @author james_000
 *
 *Die Basis eines GUI-Element
 */
public class GUIElement {
	
	private Color color;
	
	protected float x;
	protected float y;
	protected float width;
	protected float height;

	private boolean isMouseOver;
	private boolean lastMouseOver;
	protected boolean isHold;
	
	private List<GUICallback> callbacks = new ArrayList<>();
	
	public GUIElement(float x, float y, float width, float height)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.color = Color.GRAY;
		isHold = false;
	}
	
	public void render()
	{
		SpriteRenderer.drawSprite(x, y, width, height, color, null);
	}
	
	/**
	 * Updatet das GUI-Element und schaut noch Mauseingaben
	 */
	public void update()
	{
		Vector2f mouse = Input.getMousePosition();
		isMouseOver = mouse.x > x && mouse.x < x + width && mouse.y > y && mouse.y < y + height;
		
		if (isMouseOver && !lastMouseOver)
			for (GUICallback guiCallback : callbacks)
				guiCallback.onMouseEnter();
		else if (!isMouseOver && lastMouseOver)
			for (GUICallback guiCallback : callbacks)
				guiCallback.onMouseLeave();
		
		if (isMouseOver)
		{
			if (Input.isButtonReleased(0))
			{
				for (GUICallback guiCallback : callbacks)
				{
					guiCallback.onPressed();
				}
			}
		}
		
		isHold = isMouseOver && Input.isButtonDown(0);
		
		lastMouseOver = isMouseOver;
		onUpdate();
	}
	
	protected void onUpdate() {}

	/**
	 * 
	 * Fügt einen Callback zu dem GUIElement hinzu
	 * 
	 * @param callback - Der Callback
	 */
	public void addGUICallback(GUICallback callback)
	{
		callbacks.add(callback);
	}
	
}
