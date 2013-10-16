package com.ese2013.mensaunibe.api.Menu;

import org.json.JSONObject;
import org.json.JSONArray;

<<<<<<< HEAD:Programming/Mensa@Unibe/src/com/ese2013/mensaunibe/api/Menu/DailyMenuBuilder.java
public class DailyMenuBuilder {
=======
import android.util.Log;

public class MenuBuilder {
	private static final String TAG = "MensaBuilder";
>>>>>>> 5f0ef65e522163fed8e4dd40ee066789cd4fc7ac:Programming/Mensa@Unibe/src/com/ese2013/mensaunibe/api/Menu/MenuBuilder.java
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
<<<<<<< HEAD:Programming/Mensa@Unibe/src/com/ese2013/mensaunibe/api/Menu/DailyMenuBuilder.java
		}		
=======
			Log.e(TAG, e.getMessage());
		}
		
		
		
>>>>>>> 5f0ef65e522163fed8e4dd40ee066789cd4fc7ac:Programming/Mensa@Unibe/src/com/ese2013/mensaunibe/api/Menu/MenuBuilder.java
	}
	
}
