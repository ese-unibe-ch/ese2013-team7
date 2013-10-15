package com.ese2013.mensaunibe.api;

import org.json.JSONArray;
import org.json.JSONObject;

public class JSONParser {

	public JSONArray parse(String jsonString) {
		JSONArray json = new JSONArray();
		try {
			JSONObject jsonObj = new JSONObject(jsonString);
			json = jsonObj.getJSONObject("result").getJSONArray("content");
		} catch (Exception e) {
		}
		return json;
	}
}