package graphics.renderer.font;

import graphics.renderer.shader.ShaderProgram;
import graphics.renderer.shader.UniformSampler;
import graphics.renderer.shader.UniformVec2;
import graphics.renderer.shader.UniformVec3;

public class TextShader extends ShaderProgram
{
	public UniformVec3 color = new UniformVec3("color");
	public UniformSampler font = new UniformSampler("font");
	public UniformVec2 screenSize = new UniformVec2("screenSize");
	public UniformVec2 translation = new UniformVec2("translation");
	
	public TextShader()
	{
		super("shaders/text.vert", "shaders/text.frag");
		super.storeAllUniformLocation(color, font, screenSize, translation);
	}

}
