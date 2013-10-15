package com.ese2013.mensaunibe.api.Menu;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.json.JSONObject;
import org.json.JSONArray;

public class MenuBuilder {
	private String title;
	private String menu;
	private Date date;
	
	public MenuBuilder() {
	}
	
	
	public Menu create() {
		return new Menu(this);
	}
	
	public String getTitle() { return title; }
	public String getMenu() { return menu; }
	public Date getDate() { return date; }

	public void parseJson(JSONObject js, JSONObject obj) {
		try {
			SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd", Locale.GERMAN);
			date = fm.parse( js.getString("date") );

			title = obj.getString("title");
			JSONArray infos = obj.getJSONArray("menu");
			for(int i = 0; i < infos.length(); i++) {
				menu += infos.getString(i) + "\n";
			}

		} catch(Exception e) {
		}
		
		
		
	}
	
}
