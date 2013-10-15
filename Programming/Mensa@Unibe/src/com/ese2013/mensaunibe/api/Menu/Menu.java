package com.ese2013.mensaunibe.api.Menu;

import java.util.Date;
import java.util.Locale;
import java.text.SimpleDateFormat;



public class Menu {
	private String title;
	private String menu;
	private Date date;
	
	public Menu(MenuBuilder mb) {
		title = mb.getTitle();
		menu = mb.getMenu();
		date = mb.getDate();
	}
	
	public String toString() {
		return title+"\n"+menu;
	}
	
	public String getDate() {
		SimpleDateFormat fm = new SimpleDateFormat("dd/MM/yyyy", Locale.GERMAN);
		return fm.format(date);
	}
}
