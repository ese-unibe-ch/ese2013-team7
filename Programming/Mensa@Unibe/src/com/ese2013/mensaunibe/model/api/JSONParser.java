package com.ese2013.mensaunibe.model.api;

import org.json.JSONObject;
import android.util.Log;

/**
 * @author group7
 * @author Andreas Hohler
 */

public class JSONParser {
	private static final String TAG = "JSONParser";
	
	/**
	 * parses a json string
	 * @param jsonString
	 * @return a json object
	 */
	public JSONObject parse(String jsonString) {
		JSONObject json = new JSONObject();
		try {
			JSONObject jsonObj = new JSONObject(jsonString);
			json = jsonObj.getJSONObject("result");
		} catch (Exception e) {
			Log.e(TAG, e.getMessage());
		}
		return json;
	}
}