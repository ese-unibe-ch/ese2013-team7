package com.ese2013.mensaunibe.model.menu;

import java.util.HashMap;
import java.util.Iterator;

//import android.util.Log;


import android.util.Log;

import com.ese2013.mensaunibe.model.MenuDate;

public class WeeklyMenu implements Iterable<Menuplan> {
	private HashMap<String, Menuplan> menuPlans;
	public WeeklyMenu( HashMap<String, Menuplan> map ) {
		menuPlans = map;
	}
	@Override
	public Iterator<Menuplan> iterator() {
		Iterator<Menuplan> it = menuPlans.values().iterator();
		return it;
	}
	public Menuplan getDailyMenu(MenuDate date) {
		Menuplan m = menuPlans.get(date.toString());
		Log.d("WeeklyMenu", "date: "+date+" menuplan: "+m);
		return m;
	}
	
	public String toString() {
		String res = "";
		for(Menuplan mp : menuPlans.values()) {
			res += mp.toString()+"\n";
		}
		return res;
	}
}