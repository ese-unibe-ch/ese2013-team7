package com.ese2013.mensaunibe.api;

import org.json.JSONObject;

import android.util.Log;

<<<<<<< HEAD
	public JSONObject parse(String jsonString) {
=======
public class JSONParser {
	private static final String TAG = "JSONParser";
	
	public JSONArray parse(String jsonString) {
		JSONArray json = new JSONArray();
		try {
			JSONObject jsonObj = new JSONObject(jsonString);
			json = jsonObj.getJSONObject("result").getJSONArray("content");
		} catch (Exception e) {
			Log.e(TAG, e.getMessage());
		}
		return json;
	}
	
	public JSONObject parseMenus(String jsonString) {
>>>>>>> 5f0ef65e522163fed8e4dd40ee066789cd4fc7ac
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