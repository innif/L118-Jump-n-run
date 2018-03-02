package gui;

import java.awt.Color;

import graphics.Texture;
import graphics.renderer.SpriteRenderer;
import graphics.renderer.font.Font;
import graphics.renderer.font.FontAlignmentHorizontal;
import graphics.renderer.font.FontAlignmentVertical;
import graphics.renderer.font.GUIText;
import graphics.renderer.font.TextRenderer;

public class Button extends GUIElement
{
	public GUIText text;
	public Font font;
	public Color color;
	private Texture mainTexture;
	private Texture pressedTexture;

	public Button(float x, float y, float width, float height, String text)
	{
		super(x, y, width, height);
		this.text = new GUIText(text);
		this.font = Font.defaultFont;
		this.color = Color.gray;
		
		this.mainTexture = new Texture("res/textures/Button/Button.png");
		this.pressedTexture = new Texture("res/textures/Button/Button_pressed.png");
	}
	
	@Override
	public void render()
	{
		SpriteRenderer.drawSprite(x, y, width, height, isHold ? pressedTexture : mainTexture, null);
		TextRenderer.drawString(text, x, y, width, height, font, FontAlignmentVertical.CENTER, FontAlignmentHorizontal.TOP, false);
	}

}
