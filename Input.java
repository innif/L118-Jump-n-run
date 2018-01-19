package utils.input;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector2f;

public class Input 
{
	private static int keyCount = 256;
	private static int buttonCount = 3;
	
	private static boolean[] keyStates = new boolean[keyCount];
	private static boolean[] buttonStates= new boolean[buttonCount];
	
	
	public static boolean isKeyPressed(int key)
	{
		return !keyStates[key] && isKeyDown(key);
	}
	
	public static boolean isKeyDown(int key)
	{
		return Keyboard.isKeyDown(key);
	}
	
	public static boolean isKeyReleased(int key)
	{
		return !isKeyDown(key) && keyStates[key];
	}
	
	public static boolean isButtonPressed(int button)
	{
		return !buttonStates[button] && isButtonDown(button);
	}
	
	public static boolean isButtonDown(int button)
	{
		return Mouse.isButtonDown(button);
	}
	
	public static boolean isButtonReleased(int button)
	{
		return !isButtonDown(button) && buttonStates[button];
	}
	
	public static Vector2f getMousePosition()
	{
		return new Vector2f((float)Mouse.getX(), (float) Mouse.getY());
	}
	
	public static void update(){
		for(int i = 0; i<keyCount; i++){	//Keystates updaten
			keyStates[i] = isKeyDown(i);
		}
		
		for(int i = 0; i<buttonCount;i++){	//Buttonstates updaten
			buttonStates[i] = isButtonDown(i);
		}
	}
	
}