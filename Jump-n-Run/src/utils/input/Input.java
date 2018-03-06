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
	 * Anzahl aller Tasten
	 */
	private static int keyCount = 256;
	/**
	 * Anzahl der Maustasten
	 */
	private static int buttonCount = 3;
	
	/**
	 * Zustaende der einzelnen Knoepfe
	 */
	private static boolean[] keyStates = new boolean[keyCount];
	/**
	 * Zustaende der Maustasten
	 */
	private static boolean[] buttonStates= new boolean[buttonCount];
	
	
	/**
	 * Abfrage, ob eine Taste gedrueckt und wieder losgelassen wurde
	 *
	 * @param key
	 * 		Nummer der abzufragenden Taste
	 * @return
	 * 		boolean mit Zustand false fuer nicht gedrueckt
	 */
	public static boolean isKeyPressed(int key)
	{
		return !keyStates[key] && isKeyDown(key);
	}
	
	/**
	 * Abfraeg, ob eine Taste gerade gedrueckt wird
	 *
	 * @param key
	 * 		Nummer der abzufragenden Taste
	 * @return
	 * 		boolean mit Zustand false fuer gerade nicht gedrueckt
	 */
	public static boolean isKeyDown(int key)
	{
		return Keyboard.isKeyDown(key);
	}
	
	/**
	 * Abfrage, ob eine Taste gerade losgelassen wurde
	 *
	 * @param key
	 * 		Nummer der abzufragenden Taste
	 * @return
	 * 		boolean mit Zustand false fuer gerade nicht losgelassen
	 */
	public static boolean isKeyReleased(int key)
	{
		return !isKeyDown(key) && keyStates[key];
	}
	
	/**
	 * Abfrage, ob ein Mousebutton gerade gedrueckt und wieder losgelassen wurde
	 *
	 * @param button
	 * 		Nummer des entsprechenden Mousebuttons
	 * @return
	 * 		boolean mit Zustand false fuer gerade nicht gedrueckt
	 */
	public static boolean isButtonPressed(int button)
	{
		return !buttonStates[button] && isButtonDown(button);
	}
	
	/**
	 * Abfrage, ob eine Mousetaste gerade gedrueckt wird
	 *
	 * @param button
	 * 		Nummer des entsprechenden Mousebuttons
	 * @return
	 * 		boolean mit Zustand false fuer gerade nicht gedrueckt
	 */
	public static boolean isButtonDown(int button)
	{
		return Mouse.isButtonDown(button);
	}
	
	/**
	 * Abfrage, ob ein Mousebutton gerade losgelassen wurde
	 *
	 * @param button
	 * 		Nummer des entsprechenden Mousebuttons
	 * @return
	 * 		boolean mit Zustand false fuer gerade nicht losgelassen
	 */
	public static boolean isButtonReleased(int button)
	{
		return !isButtonDown(button) && buttonStates[button];
	}
	
	/**
	 * Methode zur Ermittlung der Cursor-Position im Window
	 *
	 * @return
	 * 		Vec2f mit x,y - Position des Cursors
	 */
	public static Vector2f getMousePosition()
	{
		return new Vector2f((float)Mouse.getX(), (float) Mouse.getY());
	}
	
	/**
	 * Update-Methode, die die Werte aller Tasten&Button aktualisiert
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