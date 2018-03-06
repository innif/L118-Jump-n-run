package gui;

/**
 * 
 * @author james_000
 *
 *Ein Callback f�r GUI-Events
 *
 */
public interface GUICallback
{

	default void onPressed() {} 
	default void onMouseEnter() {}
	default void onMouseLeave() {}
	
}
