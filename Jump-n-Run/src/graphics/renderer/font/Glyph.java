package graphics.renderer.font;

public class Glyph {
	
	private int id;
	private float xTextureCoord;
	private float yTextureCoord;
	private float xMaxTextureCoord;
	private float yMaxTextureCoord;
	private float xOffset;
	private float yOffset;
	private float sizeX;
	private float sizeY;
	private float xAdvance;
	
	public Glyph(int id, float xTextureCoord, float yTextureCoord, float xTexSize, float yTexSize,
			float xOffset, float yOffset, float sizeX, float sizeY, float xAdvance) {
		this.id = id;
		this.xTextureCoord = xTextureCoord;
		this.yTextureCoord = yTextureCoord;
		this.xMaxTextureCoord = xTexSize + xTextureCoord;
		this.yMaxTextureCoord = yTexSize + yTextureCoord;
		this.xOffset = xOffset;
		this.yOffset = yOffset;
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.xAdvance = xAdvance;
	}
	
	public int getId() {
		return id;
	}
	
	public float getxTextureCoord() {
		return xTextureCoord;
	}
	
	public float getyTextureCoord() {
		return yTextureCoord;
	}
	
	public float getxMaxTextureCoord() {
		return xMaxTextureCoord;
	}
	
	public float getyMaxTextureCoord() {
		return yMaxTextureCoord;
	}
	
	public float getxOffset() {
		return xOffset;
	}
	
	public float getyOffset() {
		return yOffset;
	}
	
	public float getSizeX() {
		return sizeX;
	}
	
	public float getSizeY() {
		return sizeY;
	}
	
	public float getxAdvance() {
		return xAdvance;
	}
}
