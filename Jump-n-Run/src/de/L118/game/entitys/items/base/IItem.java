package de.L118.game.entitys.items.base;

import de.L118.game.Player;
import de.L118.game.World;
import graphics.Texture;
import utils.storage.map.Tileset;

import java.util.ArrayList;

public abstract class IItem {
	
	
	private static ArrayList<IItem> items          = new ArrayList<>();
	private static Tileset          MissingTexture = new Tileset(
			1,
			1,
			1,
			new Texture("./res/textures/MissingTexture.png")
	);
	
	
	private String  name;
	private short   id;
	private short   tilesetID;
	private Tileset tileset;
	
	
	public IItem(String name) {
		
		items.add(this);
		this.name = name;
		initialiseTexture(null, (short) 0);
		this.id = (short) (items.size() - 1);
		
	}
	
	
	public IItem(String name, Tileset tileset, short tilesetID) {
		
		items.add(this);
		this.name = name;
		initialiseTexture(tileset, tilesetID);
		this.id = (short) (items.size() - 1);
		
	}
	
	
	private void initialiseTexture(Tileset tileset, short tilesetID) {
		
		if (tileset != null) {
			this.tileset = tileset;
			this.tilesetID = tilesetID;
		} else {
			this.tileset = MissingTexture;
			this.tilesetID = 1;
		}
	}
	
	public String getName() {
		
		return name;
	}
	
	public int getSizeX() {
		
		return World.TILESIZE;
	}
	
	public int getSizeY() {
		
		return World.TILESIZE;
	}
	
	public short getTilesetID() {
		return tilesetID;
	}
	
	public short getID() {
		
		return id;
	}
	
	public Tileset getTileset() {
		
		return tileset;
	}
	
	abstract public void onUse(Player p);
	
	abstract public void onPickup(Player p);
	
	public static IItem getItemByID(int id) {
		
		return items.get(id);
	}
	
}
