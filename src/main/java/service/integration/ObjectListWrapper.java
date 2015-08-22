/**
 * 
 */
package service.integration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

/**
 * Little artifact I like to use in order to generate a proper json string with a list of
 * objects with json.org
 */
public class ObjectListWrapper<T> {

	//-------------------------------------------------------------------
	// Class properties
	
	// List of objects
	private List<T> o;
	// Identifier of the list for the json object
	private String identifier;
	
	//-------------------------------------------------------------------
	// Class methods
	
	/**
	 * Builds a wrapper
	 * @param identifier String to be used in the first level of the json string
	 * @param objects List of objects to be wrapped
	 */
	public ObjectListWrapper(String identifier, List<T> objects) {
		this.o = objects;
		this.identifier = identifier;
	}
	
	@Override
	public String toString() {
		Map<String, List<T>> temp = new HashMap<>();
		temp.put(identifier, o);
		return new JSONObject(temp).toString();
	}
	
}
