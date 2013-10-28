package com.ese2013.mensaunibe.model.menu;

import java.util.Locale;
import java.text.SimpleDateFormat;

import org.json.JSONObject;
import org.json.JSONArray;

import android.util.Log;

import com.ese2013.mensaunibe.model.MenuDate;
import com.ese2013.mensaunibe.model.api.Translation;
import com.memetix.mst.language.Language;

public class DailyMenuBuilder {
	private static final String TAG = "DailyMenuBuilder";
	private String title;
	private String menu;
	private MenuDate date;
	private Language language;
	
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
			
			Translation t = new Translation();
			
			menu = "";
			String menuLine = "";
			String translated = "";
			
			/*for(int i = 0; i < infos.length(); i++) {
				menuLine = infos.getString(i);			
				menu += menuLine + "\n";
			}*/
			
			for(int i = 0; i < infos.length(); i++) {
				//menuLine = infos.getString(i);
				//if(!this.language.equals(Language.GERMAN)) {
				//if(this.language != null && this.language.name().compareTo( Language.ENGLISH.name() ) == 0) {
				
				
				menu += infos.getString(i) + "\n";
			}
			
			if(this.language != null && this.language.compareTo(Language.ENGLISH) == 0) {
				//if(this.language != Language.GERMAN) {
				t.setText(menu.replace("\n", "\r\n"));
				t.setLanguage(Language.GERMAN, Language.ENGLISH);
				translated = t.translate();
				if(translated.length() > 1) menu = translated;
				
				t.setText(title);
				translated = t.translate();
				if(translated.length() > 1) title = translated;
			}
			
			/*Translation t = new Translation();
			t.setText(menu);
			t.setLanguage(Translation.LANG_DE, Translation.LANG_EN);
			menu = t.translate();*/
			
		} catch(Exception e) {
			StackTraceElement[] a = e.getStackTrace();
			for(StackTraceElement aa : a) {
				Log.e(TAG, aa.toString());
			}
			//Log.e(TAG, e.getMessage());
		}		
	}


	public void setLanguage(Language language) {
		this.language = language;	
	}
	
}
