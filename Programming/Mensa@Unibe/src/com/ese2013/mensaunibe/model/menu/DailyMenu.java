package com.ese2013.mensaunibe.model.menu;


import com.ese2013.mensaunibe.ListItem;
import com.ese2013.mensaunibe.model.MenuDate;

public class DailyMenu implements ListItem{
	private String title;
	private String menu;
	private MenuDate date;
	
	public DailyMenu(DailyMenuBuilder mb) {
		assert mb != null;
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

	@Override
	public boolean isSection() {
		return false;
	}

	public void setMenu(String menu) {
		this.menu = menu;
	}

	public void setTitle(String title) {
		this.title = title;		
	}
}
