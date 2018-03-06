package graphics.renderer.world;

import graphics.renderer.shader.ShaderProgram;
import graphics.renderer.shader.UniformSamplerArray;
import graphics.renderer.shader.UniformVec2;

public class WorldShader extends ShaderProgram
{
	public UniformVec2 screenSize = new UniformVec2("screenSize");
	public UniformVec2 worldOffset = new UniformVec2("worldOffset");
	public UniformSamplerArray samplers = new UniformSamplerArray("textures", 32);
	
	/**
	 * Erstellt einen Standart World-Shader
	 */
	protected WorldShader()
	{
		super("shaders/world.vert", "shaders/world.frag");
		storeAllUniformLocation(screenSize, worldOffset, samplers);
	}

}
