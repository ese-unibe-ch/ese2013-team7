package com.ese2013.mensaunibe.api;

import org.json.JSONObject;

public class JSONParser {

	public JSONObject parse(String jsonString) {
		JSONObject json = new JSONObject();
		try {
			JSONObject jsonObj = new JSONObject(jsonString);
			json = jsonObj.getJSONObject("result");
		} catch (Exception e) {
		}
		return json;
	}
}