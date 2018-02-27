package graphics.renderer.font;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;

public class TextRenderer
{
	private static TextShader shader;
	
	public static void init()
	{
		shader = new TextShader();
	}
	
	public static void cleanUp()
	{
		shader.cleanUp();
	}
	
	public static void drawString(GUIText text, float x, float y, float width, float height, Font font)
	{
		if (width > 0.1f && height > 0.1f)
			GL11.glEnable(GL11.GL_SCISSOR_TEST);
		GL11.glScissor((int)x, (int)y, (int)width, (int) height);
		//GL11.glPolygonMode( GL11.GL_FRONT_AND_BACK, GL11.GL_LINE );
		shader.start();
		shader.color.loadVec3(text.getColor());
		shader.translation.loadVec2(x, y);
		shader.screenSize.loadVec2(Display.getWidth(), Display.getHeight());
		GL30.glBindVertexArray(text.getVAO(font));
		
		font.getTexture().bind(0);
		
		GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, text.getVertexCount());
		shader.stop();
		GL11.glDisable(GL11.GL_SCISSOR_TEST);
		//SpriteRenderer.drawSprite(x, y, 94, 94 * 2, Color.WHITE, null);
	}
	
	public static void drawString(GUIText text, float x, float y, float width, float height, Font font, FontAlignmentVertical vAlign, FontAlignmentHorizontal hAlign, boolean scissor)
	{
		float xx = x;
		float yy = y;
		
		switch (vAlign)
		{
		case LEFT:
			xx = x;
			break;
		case CENTER:
			xx = x + (width / 2.0f) - (font.getTextWidth(text.getText()) / 2.0f);
			break;
		case RIGHT:
			xx = x + width - font.getTextWidth(text.getText());
			break;
		}
		
		switch (hAlign)
		{
		case TOP:
			yy = y;
			break;
		case CENTER:
			yy = y;
			break;
		case BOTTOM:
			yy = y;
			break;
		}
		
		if (scissor)
			GL11.glEnable(GL11.GL_SCISSOR_TEST);
		GL11.glScissor((int)x, (int)y, (int)width, (int) height);
		//GL11.glPolygonMode( GL11.GL_FRONT_AND_BACK, GL11.GL_LINE );
		shader.start();
		shader.color.loadVec3(text.getColor());
		shader.translation.loadVec2(xx, yy);
		shader.screenSize.loadVec2(Display.getWidth(), Display.getHeight());
		GL30.glBindVertexArray(text.getVAO(font));
		
		font.getTexture().bind(0);
		
		GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, text.getVertexCount());
		shader.stop();
		GL11.glDisable(GL11.GL_SCISSOR_TEST);
		//SpriteRenderer.drawSprite(x, y, 94, 94 * 2, Color.WHITE, null);
	}
	
}
