package com.ese2013.mensaunibe.model.menu;

import java.util.HashMap;
import java.util.Iterator;

//import android.util.Log;


import android.util.Log;


/**
 * @author group7
 * @author Andreas Hohler
 */
public class WeeklyMenu implements Iterable<Menuplan> {
	private HashMap<String, Menuplan> menuPlans;
	public WeeklyMenu( HashMap<String, Menuplan> map ) {
		assert map != null;
		menuPlans = map;
	}
	@Override
	public Iterator<Menuplan> iterator() {
		Iterator<Menuplan> it = menuPlans.values().iterator();
		return it;
	}
	
	/**
	 * returns the daily menu as menuplan
	 * @param date
	 * @return Menuplan of one day
	 */
	public Menuplan getDailyMenu(MenuDate date) {
		assert date != null;
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