package utils.storage;

import de.L118.game.Blocks;
import graphics.Texture;
import utils.JSON.JSONArray;
import utils.JSON.JSONObject;
import utils.storage.map.Entry;
import utils.storage.map.MapStruct;
import utils.storage.map.Tileset;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


/*

	@author Stefan
	@version 1.0

 */
public class Config {
	
	private static ArrayList<IStorageClass> storageClasses = new ArrayList<>();
	
	/*
		Loads {@link StorageObject}'s of specified files into RAM which increases speed but junks the RAM at high amount

		@param  file            the file that should be converted to the StorageObject
		@return StorageObject  the StorageObject that was loaded from the file
	*/
	public static StorageObject loadInRAM(File file) {
		return null;
	}
	
	/*
		Removes a {@link StorageObject}'s from the RAM and stores it into the file

		@param  file            the file that should be removed from the ram
		@return boolean         true if the operation was successful
								false if the file is not loaded in RAM
								or an IOException was thrown
	*/
	public static boolean removeFromRAM(File file){
		return false;
	}
	
	/*

		Since objects in RAM don't update it is necessary to save them (at least on shutdown)

	 */
	public static void saveRAMObjects(){
	
	}
	
	/*
		Loades the {@link StorageObject} from the file (or RAM) and returns it

		@param  file            the file that should be converted
		@return StorageObject  the StorageObject that should be returned
	 */
	public static StorageObject get(File file){
		return null;
	}
	
	/*
		Loades the {@link StorageObject} from the file (or RAM) and returns it

		@param  file            the file that should be converted
		@return StorageObject  the StorageObject that should be returned
	 */
	public static void set(File file, StorageObject storageObject){
	
	}
	
	/*
		Adds the ability to store special class-objects in files

		@param  storageClass    the class the should be able to be saves in files
		@see utils.storage.IStorageClass
	 */
	public static void addIStorageClass(IStorageClass storageClass){
	
	}
	
	/*
	
	
	 */
	public static MapStruct getMap(File file) {
	
		String map_json = readFileAsString(file);
		
		JSONObject map = new JSONObject(map_json);
		List<Object> layers = map.getJSONArray("layers").toList().stream().filter(layer -> ((String)((HashMap<String,Object>)layer).get("type")).equalsIgnoreCase("tilelayer")).collect(Collectors.toList());
		JSONArray tilesets = map.getJSONArray("tilesets");
		
		final Blocks[][][] out;
		
		final int map_depth = layers.size();
		final int map_width = map.getInt("width");
		final int map_height = map.getInt("height");
		
		out = new Blocks[map_depth][map_width][map_height];
		
		final String COLLISION_PROPERTY = "collision";
		
		MapStruct output = new MapStruct();
		output.tilesets = getTilesets(file, tilesets);
		
		for(int i = layers.size()-1; i >= 0;i--) {
			HashMap<String,Object> layer = (HashMap<String,Object>)layers.get(i);
			if(layer.containsKey("properties")) {
				HashMap<String,Object> properties = (HashMap<String,Object>) layer.get("properties");
				if(properties.containsKey(COLLISION_PROPERTY) && (boolean)properties.get(COLLISION_PROPERTY))
					output.collisionLayer = i;
				
				
			}
			
			List<Integer> data = new ArrayList<>();
			((ArrayList<Object>)layer.get("data")).forEach(entry -> data.add((int)entry));
			
			for(int j = 0; j < data.size(); j++) {
				int x = j%map_width;
				int y = map_height-j/map_width-1;
				
				int type = data.get(j);
				int max = 0;
				Tileset tileset_toPut = null;
				if(type != 0) {
					for (Tileset tileset : output.tilesets) {
						if(tileset.getStartID() <= type && max < tileset.getStartID()) {
							tileset_toPut = tileset;
							max = tileset.getStartID();
						}
					}
					type -= tileset_toPut.getStartID()-1;
					
				}
				
				Blocks block = new Blocks(x,y,(short)type,tileset_toPut);
				
				out[i][x][y] = block;
			}
			
		}
		
		output.blocks = out;
		
		return output;
		
	}
	
	private static ArrayList<Tileset> getTilesets(File mapfile,JSONArray tilesets) {
		
		final ArrayList<Tileset> out = new ArrayList<>();
		
		
		tilesets.forEach(thattileset -> {
			JSONObject tileset = (JSONObject) thattileset;
			Tileset toPut = new Tileset();
			toPut.setStartID(tileset.getInt("firstgid"));
			String path = tileset.getString("source");
			
			File tileset_file = new File(mapfile.getParent() + "/" + path);
			
			Entry<Entry<Integer,Integer>,String> tileset_information = getPathFromTileset(tileset_file);
			
			toPut.setImage(new Texture(tileset_information.getValue()));
			toPut.setColumns(tileset_information.getKey().getKey());
			toPut.setTilecount(tileset_information.getKey().getValue());
			
			out.add(toPut);
			
		});
		
		return out;
	}
	
	private final static Pattern PATTERN_TILESET = Pattern.compile("<.+?>\\n<tileset .+?tilecount=\"(\\d+)\" columns=\"(\\d+)\">(?:\\n .*?)+?<image source=\"(.+?)\".+?>(?:\\n .*?)*\\n<\\/tileset>");
	
	private static Entry<Entry<Integer,Integer>,String> getPathFromTileset(File file) {
		String path = "";
		
		String file_content = readFileAsString(file);
		
		Matcher m = PATTERN_TILESET.matcher(file_content);
		
		int columns = 0;
		int tilecount = 0;
		
		while(m.find()) {
			path = m.group(3);
			columns = Integer.parseInt(m.group(2));
			tilecount = Integer.parseInt(m.group(1));
			break;
		}
		
		path = file.getParent() + "/" + path;
		
		Entry<Entry<Integer,Integer>,String> output = new Entry<>();
		output.setValue(path);
		output.setKey(new Entry<>(columns,tilecount));
		return output;
	}
	
	private static BufferedImage getImage(File file) {
		BufferedImage img;
		try {
			return ImageIO.read(file);
		} catch (IOException e) {
			return null;
		}
	}
	
	private static List<String> readFileAsList(File file) {
		if(!file.exists())
			return null;
		
		if(!file.isFile())
			return null;
		
		ArrayList<String> out = new ArrayList<>();
		
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			
			String sCurrentLine;
			
			while ((sCurrentLine = br.readLine()) != null) {
				out.add(sCurrentLine);
			}
			
		} catch (IOException e) {
			return null;
		}
		
		return out;
		
	}
	
	private static String readFileAsString(File file) {
		if(!file.exists())
			return null;
		
		if(!file.isFile())
			return null;
		
		StringBuilder out = new StringBuilder();
		
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			
			String sCurrentLine;
			
			while ((sCurrentLine = br.readLine()) != null) {
				out.append(sCurrentLine + "\n");
			}
			
		} catch (IOException e) {
			return null;
		}
		String output = out.toString();
		return output.substring(0,output.length() - "\n".length());
		
	}
	
	
}
