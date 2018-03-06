package utils.storage.map;

import de.L118.game.Blocks;

import java.util.List;

/**
 * Objekt zum Speichern der information von Karten
 * @author Stefan
 * @version 1.0
 */
public class MapStruct {
	
	/**
	 * Liste der Texture Atlanten
	 */
	public List<Tileset> tilesets;
	/**
	 * Array für alle Blöcke auf der Map
	 * x,y,layer als index
	 */
	public Blocks[][][] blocks;
	/**
	 * Layer auf dem der Spieler läuft
	 */
	public int
			collisionLayer;
	
	
	
}
