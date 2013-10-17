package com.ese2013.mensaunibe.model.menu;

import java.util.Locale;
import java.text.SimpleDateFormat;

import org.json.JSONObject;
import org.json.JSONArray;

import com.ese2013.mensaunibe.model.MenuDate;

public class DailyMenuBuilder {
	private String title;
	private String menu;
	private MenuDate date;
	
	public DailyMenuBuilder() {
	}
	
	
	public DailyMenu create() {
		return new DailyMenu(this);
	}
	
	public String getTitle() { return title; }
	public String getMenu() { return menu; }
	public MenuDate getDate() { return date; }

	public void parseJson(JSONObject obj) {
		try {
			title = obj.getString("title");
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.GERMAN);
			try {
				this.date = new MenuDate( sdf.parse(obj.getString("date")) );
			} catch(Exception e) {
			}
			
			JSONArray infos = obj.getJSONArray("menu");
			
			menu = "";
			for(int i = 0; i < infos.length(); i++) {
				menu += infos.getString(i) + "\n";
			}
		} catch(Exception e) {
		}		
	}
	
}
