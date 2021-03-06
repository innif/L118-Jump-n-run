package graphics.renderer.shader;

import org.lwjgl.opengl.GL20;

public class UniformSamplerArray extends Uniform
{
	private int[] locations;

	public UniformSamplerArray(String name, int size)
	{
		super(name);
		this.locations = new int[size];
	}
	
	public void loadValue(int[] values)
	{
		int count = values.length < locations.length ? values.length : locations.length;
		for (int i = 0; i < count; i++)
		{
			GL20.glUniform1i(locations[i], values[i]);
		}
	}
	
	public void loadAsIndex()
	{
		for (int i = 0; i < locations.length; i++)
		{
			GL20.glUniform1i(locations[i], i);
		}
	}
	
	public void loadSingleValue(int value, int index) {
		if (index >= 0 && index < locations.length)
			GL20.glUniform1i(locations[index], value);
	}

	@Override
	public void storeUniformLocation(int programId)
	{
		for (int i = 0; i < locations.length; i++)
		{
			locations[i] = GL20.glGetUniformLocation(programId, this.name + "[" + i + "]");
			if (locations[i] == Uniform.NOT_FOUND)
				System.err.println("Could not find Uniform " + name);
		}
	}
	
}
