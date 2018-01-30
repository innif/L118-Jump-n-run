package graphics.renderer.font;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import org.lwjgl.opengl.GL11;

import graphics.Texture;

public class Font
{
	private static final int PADDING_TOP = 0;
	private static final int PADDING_LEFT = 1;
	private static final int PADDING_BOTTOM = 2;
	private static final int PADDING_RIGHT = 3;
	
	private static final int DESIRED_PADDING = 8;
	
	private HashMap<Integer, Glyph> glyphs = new HashMap<>();
	private HashMap<String, String> values = new HashMap<>();
	
	private Texture texture;
	
	private int lineHeight;
	private int paddingWidth;
	private int paddingHeight;
	private int[] padding;
	
	private float size;
	
	/**
	 * 
	 * @param name - the path of the font without file-ending. Font file with .fnt and font image with .png
	 * @param size - the size of the font
	 */
	public Font(String name, float size)
	{
		this.size = size;
		this.texture = new Texture(name + ".png", GL11.GL_LINEAR);
		loadFont(name + ".fnt");
	}
	
	private int getValue(String name)
	{
		return Integer.parseInt(values.get(name));
	}
	
	private int[] getValues(String name)
	{
		String s = values.get(name);
		String[] stringValues = s.split(",");
		int[] result = new int[stringValues.length];
		for (int i = 0; i < result.length; i++)
			result[i] = Integer.parseInt(stringValues[i]);
		return result;
	}
	
	private void loadFont(String path)
	{
		String content = loadFile(path);
		String[] lines = content.split("\n");
		for (String line : lines) {
			processLine(line);
		}
	}
	
	private void processLine(String line)
	{
		storeValues(line);
		if (line.startsWith("info "))
		{
			this.padding = getValues("padding");
			this.paddingWidth = padding[PADDING_LEFT] + padding[PADDING_RIGHT];
			this.paddingHeight = padding[PADDING_TOP] + padding[PADDING_BOTTOM];
		}
		else if (line.startsWith("common "))
		{
			this.lineHeight = getValue("lineHeight");
		}
		else if (line.startsWith("char "))
		{
			loadChar(texture.getWidth());
		}
	}
	
	private void loadChar(int textureSize)
	{
		int id = getValue("id");
		float xTex = ((float)getValue("x") + (padding[PADDING_LEFT]  - DESIRED_PADDING)) / (float)textureSize;
		float yTex = ((float)getValue("y") + (padding[PADDING_TOP] - DESIRED_PADDING)) / (float)textureSize;
		int width = getValue("width") - (paddingWidth - (2 * DESIRED_PADDING));
		int height = getValue("height") - (paddingHeight - (2 * DESIRED_PADDING));
		float xTexSize = (float)width / textureSize;
		float yTexSize = (float)height / textureSize;
		float xOff = (getValue("xoffset") + padding[PADDING_LEFT] - DESIRED_PADDING);
		float yOff = (getValue("yoffset") + padding[PADDING_TOP] - DESIRED_PADDING);
		float xAdvance = getValue("xadvance") - paddingWidth;
		
		glyphs.put(id, new Glyph(id, xTex, yTex, xTexSize, yTexSize, xOff, yOff, width, height, xAdvance));
	}
	
	private void storeValues(String line)
	{
		String[] args = line.split("\\s+");
		for (String arg : args) {
			String[] values = arg.split("=");
			if (values.length == 2)
				this.values.put(values[0], values[1]);
		}
	}
	
	private String loadFile(String path)
	{
		StringBuilder builder = new StringBuilder();
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(path));
			String line = "";
			while ((line = reader.readLine()) != null)
			{
				builder.append(line + "\n");
			}
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return builder.toString();
	}
	
	private static final int QUAD_SIZE = 6 * 4;
	
	public float[] getVertices(String text)
	{
		float[] result = new float[text.length() * QUAD_SIZE];
		float cursorX = 0.0f;
		float cursorY = 0.0f;
		for (int i = 0; i < text.length(); i++)
		{
			char c = text.charAt(i);
			if (c == '\n')
			{
				cursorX = 0.0f;
				cursorY += this.lineHeight;
				continue;
			}
			Glyph g = getGlyph((int) c);
			
			storeDataInArray(result, i, g, cursorX, cursorY, size);
			cursorX += g.getxAdvance() * this.size;
		}
		return result;
	}
	
	private void storeDataInArray(float[] array, int index, Glyph g, float cursorX, float cursorY, float fontSize)
	{
		float x = cursorX + (g.getxOffset() * fontSize);
		float y = cursorY - ((g.getyOffset() - lineHeight) * fontSize);
		float maxX = x + (g.getSizeX() * fontSize);
		float maxY = y - ((g.getSizeY()) * fontSize);
		float properX = (2 * x) - 1;
		float properY = (-2 * y) + 1;
		float properMaxX = (2 * maxX) - 1;
		float properMaxY = (-2 * maxY) + 1;
		
		array[index * QUAD_SIZE + 0] = x;
		array[index * QUAD_SIZE + 1] = y;
		array[index * QUAD_SIZE + 2] = g.getxTextureCoord();
		array[index * QUAD_SIZE + 3] = g.getyTextureCoord();

		array[index * QUAD_SIZE + 4] = x;
		array[index * QUAD_SIZE + 5] = maxY;
		array[index * QUAD_SIZE + 6] = g.getxTextureCoord();
		array[index * QUAD_SIZE + 7] = g.getyMaxTextureCoord();

		array[index * QUAD_SIZE + 8] = maxX;
		array[index * QUAD_SIZE + 9] = maxY;
		array[index * QUAD_SIZE + 10] = g.getxMaxTextureCoord();
		array[index * QUAD_SIZE + 11] = g.getyMaxTextureCoord();

		array[index * QUAD_SIZE + 12] = maxX;
		array[index * QUAD_SIZE + 13] = maxY;
		array[index * QUAD_SIZE + 14] = g.getxMaxTextureCoord();
		array[index * QUAD_SIZE + 15] = g.getyMaxTextureCoord();

		array[index * QUAD_SIZE + 16] = maxX;
		array[index * QUAD_SIZE + 17] = y;
		array[index * QUAD_SIZE + 18] = g.getxMaxTextureCoord();
		array[index * QUAD_SIZE + 19] = g.getyTextureCoord();

		array[index * QUAD_SIZE + 20] = x;
		array[index * QUAD_SIZE + 21] = y;
		array[index * QUAD_SIZE + 22] = g.getxTextureCoord();
		array[index * QUAD_SIZE + 23] = g.getyTextureCoord();
	}
	
	public Glyph getGlyph(int ascii)
	{
		return glyphs.get(ascii);
	}
	
	public Texture getTexture() 
	{
		return texture;
	}
	
	public float getSize() {
		return size;
	}
	
}
