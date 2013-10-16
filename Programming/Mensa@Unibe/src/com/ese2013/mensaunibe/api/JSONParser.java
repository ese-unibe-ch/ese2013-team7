package com.ese2013.mensaunibe.api;

import org.json.JSONArray;
import org.json.JSONObject;

import android.util.Log;

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
		JSONObject json = new JSONObject();
		try {
			JSONObject jsonObj = new JSONObject(jsonString);
			json = jsonObj.getJSONObject("result").getJSONObject("content");
		} catch (Exception e) {
			Log.e(TAG, e.getMessage());
		}
		return json;
	}
}