package com.ese2013.mensaunibe.api.Menu;

import org.json.JSONObject;
import org.json.JSONArray;

public class DailyMenuBuilder {
	private String title;
	private String menu;
	
	public DailyMenuBuilder() {
	}
	
	
	public DailyMenu create() {
		return new DailyMenu(this);
	}
	
	public String getTitle() { return title; }
	public String getMenu() { return menu; }

	public void parseJson(JSONObject obj) {
		try {
			title = obj.getString("title");
			JSONArray infos = obj.getJSONArray("menu");
			menu = "";
			for(int i = 0; i < infos.length(); i++) {
				menu += infos.getString(i) + "\n";
			}
		} catch(Exception e) {
		}		
	}
	
}
