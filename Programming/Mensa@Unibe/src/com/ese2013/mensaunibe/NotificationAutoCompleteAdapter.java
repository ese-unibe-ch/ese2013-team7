package com.ese2013.mensaunibe;

import java.util.HashSet;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.ese2013.mensaunibe.model.Model;
import com.ese2013.mensaunibe.model.mensa.Mensa;
import com.ese2013.mensaunibe.model.menu.DailyMenu;
import com.ese2013.mensaunibe.model.menu.Menuplan;

/**
 * @author group17
 * @author Andreas Hohler
 */

class NotificationAutoCompleteAdapter extends ArrayAdapter<String> {

	public NotificationAutoCompleteAdapter(Context context, int resource) {
		super(context, resource);
		populate();
	}
	
	private void populate() {
		HashSet<String> words = new HashSet<String>();
		for(Mensa m : Model.getInstance().getMensaList()) {
			for(Menuplan w : m.getWeeklyMenu()) {
				for(DailyMenu d : w) {
					String t = d.getMenu();
					t.replace("\r\n", " ").replace("\n", " ");
					String[] all = t.split("\\s+");
					for(String s : all) {
						if(s.matches("CHF|Schweiz") || s.length() < 4) continue;
						words.add(s);
					}
				}
			}
		}
		this.addAll(words);
	}
}