package com.ese2013.mensaunibe.notification;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

import android.annotation.SuppressLint;
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
					t = t.replaceAll(",|«|»|:", "");
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
	
	@SuppressLint("DefaultLocale")
	public ArrayList<NotificationHolder> compareToKeywords( ArrayList<String> list ) {
		HashSet<NotificationHolder> result = new HashSet<NotificationHolder>();
		for(NotificationHolder nf : notificationHolders) {
			for(String s : list) {
				if(nf.getKeyword().toLowerCase().contains(s.toLowerCase())) {
					result.add(nf);
				}
			}
		}
		return new ArrayList<NotificationHolder>(result);
	}
	
	public HashSet<String> getWordList() {
		assert words != null;
		return this.words;
	}
}
