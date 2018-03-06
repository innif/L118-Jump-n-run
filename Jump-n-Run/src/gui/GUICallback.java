package gui;

/**
 * 
 * @author james_000
 *
 *Ein Callback für GUI-Events
 *
 */
public interface GUICallback
{

	default void onPressed() {} 
	default void onMouseEnter() {}
	default void onMouseLeave() {}
	
}
