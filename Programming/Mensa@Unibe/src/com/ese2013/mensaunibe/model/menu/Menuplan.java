package com.ese2013.mensaunibe.model.menu;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;

import android.util.Log;

public class Menuplan implements Iterable<DailyMenu> {
	private static final String TAG = "Menuplan";
	
	private ArrayList<DailyMenu> menuList;
	private Date date;
	
	public Menuplan() {
		menuList = new ArrayList<DailyMenu>();
	}
	
	public void add(DailyMenu m) {
		menuList.add(m);
	}

	@Override
	public Iterator<DailyMenu> iterator() {
		Iterator<DailyMenu> it = menuList.iterator();
		return it;
	}
	
	public void parseDate(String date) {
		try {
			SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd", Locale.GERMAN);
			this.date = fm.parse( date );
		} catch(Exception e) {
			Log.e(TAG, e.getMessage());
		}
	}
	
	public String getDate() {
		SimpleDateFormat fm = new SimpleDateFormat("dd/MM/yyyy", Locale.GERMAN);
		return fm.format(date);
	}
	
	public String toString() {
		String res = "";
		for(DailyMenu m :  menuList) {
			res += m.toString()+"\n";
		}
		return res;
	}
	
	
}