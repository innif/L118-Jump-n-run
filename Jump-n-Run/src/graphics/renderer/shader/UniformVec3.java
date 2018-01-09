package graphics.renderer.shader;

import org.lwjgl.opengl.GL20;
import org.lwjgl.util.vector.Vector3f;

public class UniformVec3 extends Uniform {
	private float currentX;
	private float currentY;
	private float currentZ;
	private boolean used = false;

	public UniformVec3(String name) {
		super(name);
	}
	/**
	 * 
	 * @param vector - Uniform value
	 * 
	 * Loads a value to the shader
	 */
	public void loadVec3(Vector3f vector) {
		loadVec3(vector.x, vector.y, vector.z);
	}

	/**
	 * 
	 * @param x - Uniform vector X
	 * @param y - Uniform vector y
	 * @param z - Uniform vector z
	 * 
	 * Loads a value to the shader
	 */
	public void loadVec3(float x, float y, float z) {
		if (!used || x != currentX || y != currentY || z != currentZ) {
			this.currentX = x;
			this.currentY = y;
			this.currentZ = z;
			used = true;
			GL20.glUniform3f(super.getLocation(), x, y, z);
		}
	}

}
