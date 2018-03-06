package utils.storage.map;

import graphics.Texture;


/**
 * Objekt zum Speichern von Tileset spezifischen Daten.
 * Tilesets sind Textur atlanten, die von Tiled benutzt werden um Karten zu erstellen.
 *
 * @author Stefan
 * @version 1.0
 */
public class Tileset {
	
	/**
	 * Bild des Atlas
	 */
	private Texture image;
	
	private int
			/**
			 * Anzahl an Spalten des Atlas
			 */
			columns,
			/**
			 * Anzahl an Zeilen des Atlas
			 */
			rows,
			/**
			 * ID der ersten Textur.
			 */
			startID;
	
	/**
	 * Leerer Constructor. Alle Werte müssen über Methoden gesetzt werden
	 */
	public Tileset() {
	
	}
	
	/**
	 * Ausführlicher Constructor. Setzt direkt alle Werte.
	 *
	 * @param startID
	 * 		ID der ersten Textur
	 * @param columns
	 * 		Anzahl an Spalten
	 * @param tilecount
	 * 		Anzahl an Texturen im Atlas
	 * @param image
	 * 		Textur des Atlas
	 */
	public Tileset(int startID,int columns,int tilecount,Texture image) {
		this.startID = startID;
		this.columns = columns;
		this.rows = tilecount/columns;
		this.image = image;
	}
	
	/**
	 * Setzt die ID der ersten Textur im Atlas
	 *
	 * @param startID
	 * 		ID der ersten Textur
	 * @return
	 * 		Gibt gleiches Objekt zurück um aufeinanderfolgende setter möglich zu machen
	 */
	public Tileset setStartID(int startID) {
		this.startID = startID;
		return this;
	}
	
	/**
	 * Setzt die Anzahl an Spalten
	 *
	 * @param columns
	 * 		Anzahl an Spalten
	 * @return
	 * 		Gibt gleiches Objekt zurück um aufeinanderfolgende setter möglich zu machen
	 */
	public Tileset setColumns(int columns) {
		this.columns = columns;
		return this;
	}
	
	/**
	 * Setzt die Anzahl an Zeilen
	 *
	 * @param tilecounts
	 * 		Gesammtanzahl an Texturen im Atlas
	 * @return
	 * 		Gibt gleiches Objekt zurück um aufeinanderfolgende setter möglich zu machen
	 */
	public Tileset setTilecount(int tilecounts) {
		this.rows = tilecounts/columns;
		return this;
	}
	
	/**
	 * Setzt die Textur des Atlas
	 *
	 * @param image
	 * 		Textur des Atlas
	 * @return
	 * 		Gibt gleiches Objekt zurück um aufeinanderfolgende setter möglich zu machen
	 */
	public Tileset setImage(Texture image) {
		this.image = image;
		return this;
	}
	
	/**
	 * @return
	 * 		Anzahl von Reihen
	 */
	public int getRows() {
		
		return rows;
	}
	
	/**
	 *
	 * @return
	 * 		Anzahl von Zeilen
	 */
	public int getColumns() {
		
		return columns;
	}
	
	/**
	 *
	 * @return
	 * 		Bild des Atlas
	 */
	public Texture getImage() {
		
		return image;
	}
	
	/**
	 *
	 * @return
	 * 		ID der ersten Textur
	 */
	public int getStartID() {
		
		return startID;
	}
}
