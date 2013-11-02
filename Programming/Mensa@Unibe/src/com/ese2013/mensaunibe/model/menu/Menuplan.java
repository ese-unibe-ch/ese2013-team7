package com.ese2013.mensaunibe.model.menu;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;

import android.util.Log;

import com.ese2013.mensaunibe.model.MenuDate;

public class Menuplan implements Iterable<DailyMenu> {
	private static final String TAG = "Menuplan";
	
	private ArrayList<DailyMenu> menuList;
	private MenuDate date;
	
	public Menuplan() {
		menuList = new ArrayList<DailyMenu>();
	}
	
	public void add(DailyMenu m) {
		assert m != null;
		menuList.add(m);
	}

	@Override
	public Iterator<DailyMenu> iterator() {
		Iterator<DailyMenu> it = menuList.iterator();
		return it;
	}
	
	public void parseDate(String stringDate) {
		try {
			SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd", Locale.GERMAN);
			this.date = new MenuDate( fm.parse(stringDate) );
			Log.e(TAG, date.toString());
		} catch(Exception e) {
			Log.e(TAG, e.getMessage());
		}
	}
	
	public MenuDate getDate() {
		return date;
	}
	
	public String toString() {
		String res = "";
		for(DailyMenu m :  menuList) {
			res += m.toString()+"\n";
		}
		return res;
	}

	public int size() {
		return menuList.size();
	}

	public void setDate(MenuDate date) {
		assert date != null;
		this.date = date;
		
	}
	
	
}
