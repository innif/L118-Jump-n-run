package graphics.renderer.shader;

import org.lwjgl.opengl.GL20;

public class UniformSampler extends Uniform {

	private int currentValue;
	private boolean used = false;

	public UniformSampler(String name) {
		super(name);
	}
	/**
	 * 
	 * @param int - Uniform value
	 * 
	 * Loads a value to the shader
	 */
	public void loadTexUnit(int texUnit) {
		if (!used || currentValue != texUnit) {
			GL20.glUniform1i(super.getLocation(), texUnit);
			used = true;
			currentValue = texUnit;
		}
	}

}
