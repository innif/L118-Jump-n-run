package utils.storage;

import java.io.File;


/*

	@author Stefan
	@version 1.0

 */
public class Config {
	
	/*
		Loads {@link StorageObject}'s of specified files into RAM which increases speed but junks the RAM at high amount

		@param  file            the file that should be converted to the StorageObject
		@return StorageObject  the StorageObject that was loaded from the file
	*/
	public StorageObject loadInRAM(File file) {
		return null;
	}
	
	/*
		Removes a {@link StorageObject}'s from the RAM and stores it into the file

		@param  file            the file that should be removed from the ram
		@return boolean         true if the operation was successful
								false if the file is not loaded in RAM
								or an IOException was thrown
	*/
	public boolean removeFromRAM(File file){
		return false;
	}
	
	/*

		Since objects in RAM don't update it is necessary to save them (at least on shutdown)

	 */
	public void saveRAMObjects(){
	
	}
	
	/*
		Loades the {@link StorageObject} from the file (or RAM) and returns it

		@param  file            the file that should be converted
		@return StorageObject  the StorageObject that should be returned
	 */
	public StorageObject get(File file){
		return null;
	}
	
	/*
		Loades the {@link StorageObject} from the file (or RAM) and returns it

		@param  file            the file that should be converted
		@return StorageObject  the StorageObject that should be returned
	 */
	public void set(File file, StorageObject storageObject){
	
	}
	
	/*
		Adds the ability to store special class-objects in files

		@param  storageClass    the class the should be able to be saves in files
		@see utils.storage.IStorageClass
	 */
	public void addIStorageClass(IStorageClass storageClass){
	
	}
	
	
}
