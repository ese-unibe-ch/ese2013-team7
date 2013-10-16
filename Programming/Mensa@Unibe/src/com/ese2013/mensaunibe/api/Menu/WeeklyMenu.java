package com.ese2013.mensaunibe.api.Menu;

import java.util.ArrayList;
import java.util.Iterator;

public class WeeklyMenu implements Iterable<Menuplan> {
	private ArrayList<Menuplan> menuPlans;
	public WeeklyMenu() {
		menuPlans = new ArrayList<Menuplan>();
	}
	@Override
	public Iterator<Menuplan> iterator() {
		Iterator<Menuplan> it = menuPlans.iterator();
		return it;
	}
}