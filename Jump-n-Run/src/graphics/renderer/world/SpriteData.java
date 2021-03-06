package graphics.renderer.world;

public class SpriteData
{
	public float x;
	public float y;
	public float t;
	public float c;
	public float tid;
	
	public SpriteData(float x, float y, float t, float c, float tid)
	{
		this.x = x;
		this.y = y;
		this.t = t;
		this.c = c;
		this.tid = tid;
	}
	
	public float[] toFloatArray()
	{
		return new float[] {this.x, this.y, this.t, this.c, this.tid};
	}
}
