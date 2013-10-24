package com.ese2013.mensaunibe.model.menu;

import java.util.Locale;
import java.text.SimpleDateFormat;

import org.json.JSONObject;
import org.json.JSONArray;

import android.util.Log;

import com.ese2013.mensaunibe.model.MenuDate;

public class DailyMenuBuilder {
	private static final String TAG = "DailyMenuBuilder";
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
			
			/*Translation t = new Translation();
			t.setText(menu);
			t.setLanguage(Translation.LANG_DE, Translation.LANG_EN);
			menu = t.translate();*/
			
		} catch(Exception e) {
			Log.e(TAG, e.getMessage());
		}		
	}
	
}
