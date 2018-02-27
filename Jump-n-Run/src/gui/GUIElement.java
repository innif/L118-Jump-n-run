package gui;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.util.vector.Vector2f;

import graphics.renderer.SpriteRenderer;
import utils.input.Input;

public class GUIElement {
	
	private Color color;
	
	protected float x;
	protected float y;
	protected float width;
	protected float height;

	private boolean isMouseOver;
	private boolean lastMouseOver;
	
	private List<GUICallback> callbacks = new ArrayList<>();
	
	public GUIElement(float x, float y, float width, float height)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.color = Color.GRAY;
	}
	
	public void render()
	{
		SpriteRenderer.drawSprite(x, y, width, height, color, null);
	}
	
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
			if (Input.isButtonPressed(0))
			{
				for (GUICallback guiCallback : callbacks)
				{
					guiCallback.onPressed();
				}
			}
		}
		
		lastMouseOver = isMouseOver;
		onUpdate();
	}
	
	protected void onUpdate() {}

	public void addGUICallback(GUICallback callback)
	{
		callbacks.add(callback);
	}
	
}
