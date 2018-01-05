package utils.storage;

/*

	@author Stefan
	@version 1.0

 */

public interface IStorageObject {

	/*
		Sets or creates an entry to/for the StorageObject.

		@param key      the key of the entry you want to change
		@param object   the object that should be stored

	 */
	void setValue(String key, Object value);

	/*
		Gets the value of a specified key. Returns the value as object.

		@param key      the key of the entry that should be get
		@return         the object related to the given key
	 */
	Object getValue(String key);

}
