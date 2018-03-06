package utils.input;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector2f;

/**
 * Interface zum managen der Maus- und Tatstertureingaben
 *
 * @author Nico
 * @version 1.0
 */
public class Input
{
	
	/**
	 * [Beschreibung]
	 */
	private static int keyCount = 256;
	/**
	 * [Beschreibung]
	 */
	private static int buttonCount = 3;
	
	/**
	 * [Beschreibung]
	 */
	private static boolean[] keyStates = new boolean[keyCount];
	/**
	 * [Beschreibung]
	 */
	private static boolean[] buttonStates= new boolean[buttonCount];
	
	
	/**
	 * [Beschreibung]
	 *
	 * @param key
	 * 		[Beschreibung der Paramters]
	 * @return
	 * 		[Beschreibung, was zurückgegeben wird]
	 */
	public static boolean isKeyPressed(int key)
	{
		return !keyStates[key] && isKeyDown(key);
	}
	
	/**
	 * [Beschreibung]
	 *
	 * @param key
	 * 		[Beschreibung der Paramters]
	 * @return
	 * 		[Beschreibung, was zurückgegeben wird]
	 */
	public static boolean isKeyDown(int key)
	{
		return Keyboard.isKeyDown(key);
	}
	
	/**
	 * [Beschreibung]
	 *
	 * @param key
	 * 		[Beschreibung der Paramters]
	 * @return
	 * 		[Beschreibung, was zurückgegeben wird]
	 */
	public static boolean isKeyReleased(int key)
	{
		return !isKeyDown(key) && keyStates[key];
	}
	
	/**
	 * [Beschreibung]
	 *
	 * @param button
	 * 		[Beschreibung der Paramters]
	 * @return
	 * 		[Beschreibung, was zurückgegeben wird]
	 */
	public static boolean isButtonPressed(int button)
	{
		return !buttonStates[button] && isButtonDown(button);
	}
	
	/**
	 * [Beschreibung]
	 *
	 * @param button
	 * 		[Beschreibung der Paramters]
	 * @return
	 * 		[Beschreibung, was zurückgegeben wird]
	 */
	public static boolean isButtonDown(int button)
	{
		return Mouse.isButtonDown(button);
	}
	
	/**
	 * [Beschreibung]
	 *
	 * @param button
	 * 		[Beschreibung der Paramters]
	 * @return
	 * 		[Beschreibung, was zurückgegeben wird]
	 */
	public static boolean isButtonReleased(int button)
	{
		return !isButtonDown(button) && buttonStates[button];
	}
	
	/**
	 * [Beschreibung]
	 *
	 * @return
	 * 		[Beschreibung, was zurückgegeben wird]
	 */
	public static Vector2f getMousePosition()
	{
		return new Vector2f((float)Mouse.getX(), (float) Mouse.getY());
	}
	
	/**
	 * [Beschreibung]
	 *
	 */
	public static void update(){
		for(int i = 0; i<keyCount; i++){	//Keystates updaten
			keyStates[i] = isKeyDown(i);
		}
		
		for(int i = 0; i<buttonCount;i++){	//Buttonstates updaten
			buttonStates[i] = isButtonDown(i);
		}
	}
	
}