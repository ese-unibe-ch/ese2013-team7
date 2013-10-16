package com.ese2013.mensaunibe.api.Menu;

public class DailyMenu {
	private String title;
	private String menu;
	
	public DailyMenu(DailyMenuBuilder mb) {
		title = mb.getTitle();
		menu = mb.getMenu();
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
}
