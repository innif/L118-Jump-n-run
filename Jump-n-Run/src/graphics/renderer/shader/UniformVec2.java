package graphics.renderer.shader;

import org.lwjgl.opengl.GL20;
import org.lwjgl.util.vector.Vector2f;

public class UniformVec2 extends Uniform {

	private float currentX;
	private float currentY;
	private boolean used = false;

	public UniformVec2(String name) {
		super(name);
	}

	/**
	 * 
	 * @param vector - Uniform value
	 * 
	 * Loads a value to the shader
	 */
	public void loadVec2(Vector2f vector) {
		loadVec2(vector.x, vector.y);
	}

	/**
	 * 
	 * @param x - Uniform vector x
	 * @param y - Uniform vector y
	 * 
	 * Loads a value to the shader
	 */
	public void loadVec2(float x, float y) {
		if (!used || x != currentX || y != currentY) {
			this.currentX = x;
			this.currentY = y;
			used = true;
			GL20.glUniform2f(super.getLocation(), x, y);
		}
	}

}
