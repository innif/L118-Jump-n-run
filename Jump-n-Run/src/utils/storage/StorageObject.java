package utils.storage;

/*

	@author Stefan
	@version 1.0

 */

import utils.JSON.JSONObject;

import java.io.File;
import java.util.HashMap;

public class StorageObject extends HashMap<String,Object>{
	
	public StorageObject(String JSONString) {
		this.putAll(new JSONObject(JSONString).toMap());
	}
	
	public void saveTo(File file){
		Config.set(file,this);
	}

}
