package com.ese2013.mensaunibe;

/**
 * @author group7
 * @author Andreas Hohler
 * @see Mensa
 * @see ListSectionItem
 */

public interface ListItem {
	/**
	 * Is used for ListAdapter to show sections and real items.
	 * @return true if ListItem is a section, other else false
	 */
	public boolean isSection();
}
