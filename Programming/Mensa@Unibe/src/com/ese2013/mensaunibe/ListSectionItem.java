package com.ese2013.mensaunibe;

/**
 * @author group7
 * @author Andreas Hohler
 *
 */

public class ListSectionItem implements ListItem {
	private String title;
	
	public ListSectionItem(String title) {
		this.title = title;
	}
	
	public String toString() {
		return title;
	}

	@Override
	public boolean isSection() {
		return true;
	}

}
