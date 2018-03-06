package graphics.renderer;

import graphics.renderer.shader.ShaderProgram;
import graphics.renderer.shader.UniformMatrix;
import graphics.renderer.shader.UniformVec2;
import graphics.renderer.shader.UniformVec3;

public class ColorShader extends ShaderProgram{

	private static final String VERTEX_PATH = "shaders/default_color.vert";
	private static final String FRAGMENT_PATH = "shaders/default_color.frag";
	
	public UniformMatrix modelMatrix = new UniformMatrix("ml_matrix");
	public UniformVec2 screenSize = new UniformVec2("screenSize");
	public UniformVec3 color = new UniformVec3("color");
	
	/**
	 * Erstellt einen Standart Farb-Shader
	 */
	public ColorShader() {
		super(VERTEX_PATH, FRAGMENT_PATH);
		storeAllUniformLocation(modelMatrix, screenSize, color);
	}
	
	
	/**
	 * Erstellt ein neuen Color Shader
	 * 
	 * @param vertPath - Der Pfad zum Vertex-Shader
	 * @param fragPath - Der Pfad zum Fragment-Shader
	 */
	public ColorShader(String vertPath, String fragPath) {
		super(vertPath, fragPath);
		storeAllUniformLocation(modelMatrix, screenSize, color);
	}
	
}
