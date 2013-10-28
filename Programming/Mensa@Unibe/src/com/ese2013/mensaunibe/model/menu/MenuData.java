package com.ese2013.mensaunibe.model.menu;


import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;

import android.util.Log;

import com.ese2013.mensaunibe.model.MenuDate;
import com.ese2013.mensaunibe.model.api.ApiUrl;
import com.ese2013.mensaunibe.model.api.DataRequest;
import com.memetix.mst.language.Language;

public class MenuData {
	
	private static final String TAG = "MenuData";

	private DataRequest rq;
	private Language language;

	public MenuData() {
		rq = new DataRequest();
	}
	
	public void setWeekUrl(int mensaId){
		Calendar calendar = Calendar.getInstance();
		int weekend = calendar.get(Calendar.DAY_OF_WEEK);
		if(weekend == Calendar.SATURDAY || weekend == Calendar.SUNDAY){
			int weekNr =calendar.get(Calendar.WEEK_OF_YEAR);
			int nextWeek = (weekNr < 52) ? weekNr+1 : 1;
			rq.setUrl( String.format(ApiUrl.API_NEXT_WEEK_MENU, mensaId, nextWeek));
		}else{
			rq.setUrl( String.format(ApiUrl.API_WEEKLY_MENU, mensaId));
		}
		rq.setType( "MENU_ " + mensaId );
		rq.execute();
	}
	
	public WeeklyMenu getWeeklyMenuList(int mensaId) {
		setWeekUrl(mensaId);
		try {
			JSONObject content = rq.getJSONData().getJSONObject("content");
			JSONArray menus = content.getJSONArray("menus");
			HashMap<String, Menuplan> menuHashMap = new HashMap<String, Menuplan>();
			
			for(int i = 0; i<menus.length(); i++) {
				DailyMenuBuilder mb = new DailyMenuBuilder();
				mb.setLanguage(this.language);
				mb.parseJson( menus.getJSONObject(i) );
				
				DailyMenu menu = mb.create();
				MenuDate date = menu.getDate();
				if(menuHashMap.containsKey(date.toString())) {
					menuHashMap.get(date.toString()).add(menu);
				} else {
					Menuplan plan = new Menuplan();
					plan.add(menu);
					plan.setDate(date);
					menuHashMap.put(date.toString(), plan);
				}
			}
			WeeklyMenu wk = new WeeklyMenu(menuHashMap);
			Log.i(TAG, "oject weeklymenu: "+wk);
			return wk;
		} catch(Exception e) {
			StackTraceElement[] a = e.getStackTrace();
			for(StackTraceElement aa : a) {
				Log.e(TAG, aa.toString());
			}
			Log.e(TAG, e.getMessage());
		}
		
		return null;
	}
	
	public Menuplan getMenuList(int mensaId) {
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
				plan.setDate(menu.getDate());
			}
		} catch(Exception e) {
			Log.e(TAG, e.getMessage());
		}
		return plan;
	}

	public void setLanguage(Language language) {
		this.language = language;
		
	}
}
