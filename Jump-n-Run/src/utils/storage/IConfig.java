package utils.storage;

import java.io.File;


/*

	@author Stefan
	@version 1.0

 */
public interface IConfig {

	/*
		Loads {@link IStorageObject}'s of specified files into RAM which increases speed but junks the RAM at high amount

		@param file             the file that should be converted to the StorageObject
		@return IStorageObject  the StorageObject that was loaded from the file
	*/
	IStorageObject loadInRAM(File file);
	/*

		Since objects in RAM don't update it is necessary to save them (at least on shutdown)

	 */
	void saveRAMObjects();

	/*
		Loades the {@link IStorageObject} from the file (or RAM) and returns it

		@param  file            the file that should be converted
		@return IStorageObject  the StorageObject that should be returned
	 */
	IStorageObject get(File file);

	/*
		Loades the {@link IStorageObject} from the file (or RAM) and returns it

		@param  file            the file that should be converted
		@return IStorageObject  the StorageObject that should be returned
	 */
	void set(File file, IStorageObject storageObject);

	/*
		Adds the ability to store special class-objects in files

		@param  storageClass    the class the should be able to be saves in files
		@see utils.storage.IStorageClass
	 */
	void addIStorageClass(IStorageClass storageClass);


}
