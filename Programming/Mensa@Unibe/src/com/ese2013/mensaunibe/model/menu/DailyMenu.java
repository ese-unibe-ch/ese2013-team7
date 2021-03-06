package com.ese2013.mensaunibe.model.menu;

import com.ese2013.mensaunibe.model.utils.ListItem;

/**
 * @author group7
 * @author Andreas Hohler
 */

public class DailyMenu implements ListItem{
	private String title;
	private String menu;
	private MenuDate date;
	
	/**
	 * creates the DailyMenu with the Builder data
	 * @param mb DailyMenuBuilder
	 */
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
