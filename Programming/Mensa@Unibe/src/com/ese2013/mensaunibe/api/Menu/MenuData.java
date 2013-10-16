package com.ese2013.mensaunibe.api.Menu;


import org.json.JSONArray;
import org.json.JSONObject;

import com.ese2013.mensaunibe.api.ApiUrl;
import com.ese2013.mensaunibe.api.DataRequest;

public class MenuData {

	private DataRequest rq;

	public MenuData() {
		rq = new DataRequest();
	}
	
	public WeeklyMenu getWeeklyMenuList(int mensaId) {
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
		}
		return plan;
	}
}
