package com.ese2013.mensaunibe.api.Menu;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.ese2013.mensaunibe.api.ApiUrl;
import com.ese2013.mensaunibe.api.JSONParser;
import com.ese2013.mensaunibe.api.RequestData;

public class MenuData {

	private JSONParser parser;
	private RequestData rq;
	
	public MenuData() {
		parser = new JSONParser();
		rq = new RequestData();
	}
	
	public ArrayList<Menu> getMenuList(int mensaId) {
		ArrayList<Menu> menus = new ArrayList<Menu>();
		rq.setUrl( String.format(ApiUrl.API_DAILY_MENU, mensaId) );
		rq.execute();
		try {
			JSONObject js = parser.parseMenus( rq.get() );
			JSONArray list = js.getJSONArray("menus");
			for(int i = 0; i<list.length(); i++) {
				MenuBuilder mb = new MenuBuilder();
				mb.parseJson(js,list.getJSONObject(i));
				Menu menu = mb.create();
				menus.add( menu );
			}
		} catch(Exception e) {
		}
		return menus;
	}
}
