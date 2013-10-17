package com.ese2013.mensaunibe.model.menu;


import com.ese2013.mensaunibe.model.MenuDate;

public class DailyMenu {
	private String title;
	private String menu;
	private MenuDate date;
	
	public DailyMenu(DailyMenuBuilder mb) {
		title = mb.getTitle();
		menu = mb.getMenu();
		date = mb.getDate();
	}
	
	public String toString() {
		return title+"\n"+menu;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getMenu() {
		return menu;
	}
	
	public MenuDate getDate() {
		return date;
	}
}
