package com.ese2013.mensaunibe.api;

import org.json.JSONObject;

import android.util.Log;

public class JSONParser {
	private static final String TAG = "JSONParser";
	
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