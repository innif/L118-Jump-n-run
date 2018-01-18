package utils.storage.map;

import graphics.Texture;

public class Tileset {
	
	private Texture image;
	private int
			columns,
			rows,
			startID;
	
	public Tileset() {
	
	}
	
	public Tileset(int startID,int columns,int tilecount,Texture image) {
		this.startID = startID;
		this.columns = columns;
		this.rows = tilecount/columns;
		this.image = image;
	}
	
	public Tileset setStartID(int startID) {
		this.startID = startID;
		return this;
	}
	
	public Tileset setColumns(int columns) {
		this.columns = columns;
		return this;
	}
	
	public Tileset setTilecount(int tilecounts) {
		this.rows = tilecounts/columns;
		return this;
	}
	
	public Tileset setImage(Texture image) {
		this.image = image;
		return this;
	}
	
	public int getRows() {
		
		return rows;
	}
	
	public int getColumns() {
		
		return columns;
	}
	
	public Texture getImage() {
		
		return image;
	}
	
	public int getStartID() {
		
		return startID;
	}
}
