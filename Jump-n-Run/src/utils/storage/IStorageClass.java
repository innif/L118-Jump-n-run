package utils.storage;

import java.util.List;


/*

	@author Stefan
	@version 1.0

 */
public interface IStorageClass<T> {

	/*
		Method that gets called when an object of the class get's turned into a saveable string
		@return         string that'll get stored
	 */
	String toFileFormat();

	/*
		Method which gets called to retrieve the object from the stored string

		@param string   the string as which the object is stored in the file
		@return         object that's representive for the string
	 */
	T fromFileFormat(String string);

}
