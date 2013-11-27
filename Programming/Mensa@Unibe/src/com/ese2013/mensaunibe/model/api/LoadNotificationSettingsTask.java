package com.ese2013.mensaunibe.model.api;

import java.util.HashSet;

import com.ese2013.mensaunibe.model.Model;
import com.ese2013.mensaunibe.model.mensa.Mensa;
import com.ese2013.mensaunibe.model.menu.DailyMenu;
import com.ese2013.mensaunibe.model.menu.Menuplan;
import com.ese2013.mensaunibe.NotificationAutoCompleteAdapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

/**
 * @author group7
 * @author Andreas Hohler
 */

public class LoadNotificationSettingsTask extends AsyncTask<Void, Void, Boolean> {

	private ProgressDialog dialog;
	private Context context;
	private NotificationAutoCompleteAdapter adapter;
	private HashSet<String> words;
	/**
	 * constructor, creates the waiting dialog progress bar
	 * for MensaListAdapter
	 * @param context
	 * @param listAdapter
	 */
	public LoadNotificationSettingsTask(Context context, NotificationAutoCompleteAdapter adapter) {
		assert context != null && adapter != null;
		this.context = context;
		this.dialog = new ProgressDialog(this.context);
		this.adapter = adapter;
	}
	
	/**
	 * pre execute: shows the progress bar
	 */
	protected void onPreExecute() {
        this.dialog.setMessage("Loading...");
        this.dialog.show();
    }
	
	/**
	 * post execute: hides the progress bar, handles the result
	 * @param success (true if reload worked, other else false)
	 */
	protected void onPostExecute(final Boolean success) {
		if (dialog.isShowing()) {
			dialog.dismiss();
		}
		
		if(success) {
			if(adapter != null) {
				adapter.addAll(words);
				adapter.notifyDataSetChanged();
			}
		}
	}

	protected Boolean doInBackground(Void... params) {
		loadData();
		return true;
	}
	
	private void loadData() {
		words = new HashSet<String>();
		for(Mensa m : Model.getInstance().getMensaList()) {
			for(Menuplan w : m.getWeeklyMenu()) {
				for(DailyMenu d : w) {
					String t = d.getMenu();
					t.replace("\r\n", " ").replace("\n", " ").replace(".", " ").replace(",", " ");
					String[] all = t.split("\\s+");
					for(String s : all) {
						if(s.matches("CHF|Schweiz") || s.length() < 4) continue;
						words.add(s);
					}
				}
			}
		}
	}
}
