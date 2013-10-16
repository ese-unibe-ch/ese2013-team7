package com.ese2013.mensaunibe.api.Menu;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import android.util.Log;

import com.ese2013.mensaunibe.api.ApiUrl;
import com.ese2013.mensaunibe.api.JSONParser;
import com.ese2013.mensaunibe.api.RequestData;

public class MenuData {
	private static final String TAG = "MenuData";
	private JSONParser parser;
	private RequestData rq;
	
	public MenuData() {
		parser = new JSONParser();
		rq = new RequestData();
	}
	
	public ArrayList<DailyMenu> getMenuList(int mensaId) {
		ArrayList<DailyMenu> menus = new ArrayList<DailyMenu>();
		rq.setUrl( String.format(ApiUrl.API_DAILY_MENU, mensaId) );
		rq.execute();
		try {
			JSONObject js = parser.parseMenus( rq.get() );
			JSONArray list = js.getJSONArray("menus");
			for(int i = 0; i<list.length(); i++) {
				MenuBuilder mb = new MenuBuilder();
				mb.parseJson(js,list.getJSONObject(i));
				DailyMenu menu = mb.create();
				menus.add( menu );
			}
		} catch(Exception e) {
			Log.e(TAG, e.getMessage());
		}
		return menus;
	}
}
