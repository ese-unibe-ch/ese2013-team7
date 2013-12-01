package com.ese2013.mensaunibe.notification;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

import com.ese2013.mensaunibe.model.Model;
import com.ese2013.mensaunibe.model.mensa.Mensa;
import com.ese2013.mensaunibe.model.menu.DailyMenu;
import com.ese2013.mensaunibe.model.menu.MenuDate;
import com.ese2013.mensaunibe.model.menu.Menuplan;
import com.ese2013.mensaunibe.model.utils.AppUtils;

public class WordNotificationUtil {
	private HashSet<String> words;
	private ArrayList<NotificationHolder> notificationHolders;
	private MenuDate today;
	
 	public WordNotificationUtil() {
 		today = new MenuDate(new Date());
		initializeWordList();
	}

	private void initializeWordList() {
		words = new HashSet<String>();
		notificationHolders = new ArrayList<NotificationHolder>();
		for(Mensa m : Model.getInstance().getMensaList()) {
			for(Menuplan w : m.getWeeklyMenu()) {
				for(DailyMenu d : w) {
					String t = d.getMenu();
					t = t.replaceAll(",|«|»", "");
					t = t.replace(".","");
					String[] all = t.split("\\s+");
					for(String s : all) {
						if(s.matches("CHF|Schweiz") || s.length() < 5 || !AppUtils.hasUpperChars(s)) continue;
						words.add(s);
						if(w.getDate().equals(today)) {
							notificationHolders.add( new NotificationHolder( m.getId(), s) );
						}
					}
				}
			}
		}
	}
	
	public ArrayList<NotificationHolder> compareToKeywords( ArrayList<String> list ) {
		ArrayList<NotificationHolder> result = new ArrayList<NotificationHolder>();
		for(NotificationHolder nf : notificationHolders) {
			for(String s : list) {
				if(nf.getKeyword().equals(s)) {
					result.add(nf);
				}
			}
		}
		return result;
	}
	
	public HashSet<String> getWordList() {
		assert words != null;
		return this.words;
	}
}
