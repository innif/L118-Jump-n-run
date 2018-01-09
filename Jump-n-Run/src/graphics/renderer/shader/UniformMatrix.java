package graphics.renderer.shader;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL20;
import org.lwjgl.util.vector.Matrix4f;

public class UniformMatrix extends Uniform{
	
	private static FloatBuffer matrixBuffer = BufferUtils.createFloatBuffer(16);

	public UniformMatrix(String name) {
		super(name);
	}
	/**
	 * 
	 * @param Matrix4f - Uniform value
	 * 
	 * Loads a value to the shader
	 */
	public void loadMatrix(Matrix4f matrix){
		matrix.store(matrixBuffer);
		matrixBuffer.flip();
		GL20.glUniformMatrix4(super.getLocation(), false, matrixBuffer);
	}
	
	

}
