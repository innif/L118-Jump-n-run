package graphics.renderer.shader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

public abstract class ShaderProgram
{
	private int programID;
	
	/**
	 * @param vertexPath - File path to vertex shader
	 * @param fragmentPath - File path to fragment shader
	 */
	protected ShaderProgram(String vertexPath, String fragmentPath)
	{
		int vertexID = loadShader(vertexPath, GL20.GL_VERTEX_SHADER);
		int fragmentID = loadShader(fragmentPath, GL20.GL_FRAGMENT_SHADER);
		this.programID = GL20.glCreateProgram();
		GL20.glAttachShader(programID, vertexID);
		GL20.glAttachShader(programID, fragmentID);
		GL20.glLinkProgram(programID);
		GL20.glDetachShader(programID, vertexID);
		GL20.glDetachShader(programID, fragmentID);
		GL20.glDeleteShader(vertexID);
		GL20.glDeleteShader(fragmentID);
	}
	
	/**
	 * @param uniforms - All uniforms for the shader
	 * 
	 * Saves all uniforms as Uniform-class
	 */
	protected void storeAllUniformLocation(Uniform... uniforms)
	{
		for (Uniform uniform : uniforms)
		{
			uniform.storeUniformLocation(programID);
		}
		GL20.glValidateProgram(programID);
	}
	
	/**
	 * Runs the Shader program
	 */
	public void start()
	{
		GL20.glUseProgram(programID);
	}
	
	/**
	 * Stops the Shader program
	 */
	public void stop()
	{
		GL20.glUseProgram(0);
	}
	
	/**
	 * Deletes the Shader program
	 */
	public void cleanUp()
	{
		GL20.glUseProgram(0);
		GL20.glDeleteProgram(programID);
	}
	
	/**
	 * 
	 * @param path - path to the shader-file
	 * @param type - opengl shader-type 
	 * @return Shader-ID
	 */
	private int loadShader(String path, int type)
	{
		String shaderSource = loadFile(path);
		int shaderID = GL20.glCreateShader(type);
		GL20.glShaderSource(shaderID, shaderSource);
		GL20.glCompileShader(shaderID);
		if (GL20.glGetShaderi(shaderID, GL20.GL_COMPILE_STATUS) == GL11.GL_FALSE)
		{
			System.err.println("Could not compile shader " + path);
			System.err.println(GL20.glGetShaderInfoLog(shaderID, 500));
		}
		return shaderID;
	}
	
	/**
	 * Loads a file as String
	 * @param path
	 * @return
	 */
	private String loadFile(String path)
	{
		try
		{
			@SuppressWarnings("resource")
			BufferedReader br = new BufferedReader(new FileReader(path));
			StringBuilder sb = new StringBuilder();
			String line = "";
			while ((line = br.readLine()) != null)
			{
				sb.append(line).append("\n");
			}
			return sb.toString();
		}
		catch (IOException e)
		{
			e.printStackTrace();
			return "";
		}
	}
}
