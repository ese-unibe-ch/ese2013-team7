package com.ese2013.mensaunibe.model.menu;


import org.json.JSONArray;
import org.json.JSONObject;
import java.util.HashMap;

import android.util.Log;

import com.ese2013.mensaunibe.model.MenuDate;
import com.ese2013.mensaunibe.model.api.ApiUrl;
import com.ese2013.mensaunibe.model.api.DataRequest;

public class MenuData {
	
	private static final String TAG = "MenuData";

	private DataRequest rq;

	public MenuData() {
		rq = new DataRequest();
	}
	
	public WeeklyMenu getWeeklyMenuList(int mensaId) {
		rq.setUrl( String.format(ApiUrl.API_WEEKLY_MENU, mensaId));
		rq.execute();
		try {
			JSONObject content = rq.getJSONData().getJSONObject("content");
			JSONArray menus = content.getJSONArray("menus");
			HashMap<String, Menuplan> menuHashMap = new HashMap<String, Menuplan>();
			
			for(int i = 0; i<menus.length(); i++) {
				DailyMenuBuilder mb = new DailyMenuBuilder();
				mb.parseJson( menus.getJSONObject(i) );
				
				DailyMenu menu = mb.create();
				MenuDate date = menu.getDate();
				if(menuHashMap.containsKey(date.toString())) {
					menuHashMap.get(date.toString()).add(menu);
				} else {
					Menuplan plan = new Menuplan();
					plan.add(menu);
					menuHashMap.put(date.toString(), plan);
				}
			}
			WeeklyMenu wk = new WeeklyMenu(menuHashMap);
			Log.e(TAG, "oject weeklymenu: "+wk);
			return wk;
		} catch(Exception e) {
			Log.e(TAG, e.getMessage());
		}
		
		return null;
	}
	
	public Menuplan getMenuList(int mensaId) {
		//ArrayList<DailyMenu> menus = new ArrayList<DailyMenu>();
		Menuplan plan = new Menuplan();
		rq.setUrl( String.format(ApiUrl.API_DAILY_MENU, mensaId) );
		rq.execute();
			
		try {
			JSONObject js = rq.getJSONData();
			JSONObject content = js.getJSONObject("content");
			String date = content.getString("date");
			plan.parseDate(date);
			JSONArray list = content.getJSONArray("menus");
			
			for(int i = 0; i<list.length(); i++) {
				DailyMenuBuilder mb = new DailyMenuBuilder();
				mb.parseJson(list.getJSONObject(i));
				DailyMenu menu = mb.create();
				plan.add( menu );
			}
		} catch(Exception e) {
			Log.e(TAG, e.getMessage());
		}
		return plan;
	}
}
