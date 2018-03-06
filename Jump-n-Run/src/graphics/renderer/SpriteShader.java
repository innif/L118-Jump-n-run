package graphics.renderer;

import graphics.renderer.shader.ShaderProgram;
import graphics.renderer.shader.UniformMatrix;
import graphics.renderer.shader.UniformSampler;
import graphics.renderer.shader.UniformVec2;

public class SpriteShader extends ShaderProgram{

	private static final String VERTEX_PATH = "shaders/default_sprite.vert";
	private static final String FRAGMENT_PATH = "shaders/default_sprite.frag";
	
	public UniformMatrix modelMatrix = new UniformMatrix("ml_matrix");
	public UniformVec2 screenSize = new UniformVec2("screenSize");
	public UniformSampler sampler = new UniformSampler("sampler");
	
	/**
	 * Erstellt einen Standart Textur-Shader
	 */
	public SpriteShader() {
		super(VERTEX_PATH, FRAGMENT_PATH);
		storeAllUniformLocation(modelMatrix, screenSize, sampler);
	}
	
	/**
	 * Erstellt ein neuen Textur Shader
	 * 
	 * @param vertPath - Der Pfad zum Vertex-Shader
	 * @param fragPath - Der Pfad zum Fragment-Shader
	 */
	protected SpriteShader(String vertPath, String fragPath) {
		super(vertPath, fragPath);
		storeAllUniformLocation(modelMatrix, screenSize, sampler);
	}
	
}
