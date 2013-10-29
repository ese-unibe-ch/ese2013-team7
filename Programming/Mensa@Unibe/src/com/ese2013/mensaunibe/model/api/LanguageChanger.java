package com.ese2013.mensaunibe.model.api;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.ese2013.mensaunibe.MenuListAdapter;
import com.ese2013.mensaunibe.model.mensa.Mensa;
import com.ese2013.mensaunibe.model.menu.DailyMenu;
import com.ese2013.mensaunibe.model.menu.Menuplan;
import com.memetix.mst.language.Language;

import android.util.Log;
import android.widget.Toast;

import com.memetix.mst.translate.Translate;


public class LanguageChanger extends AsyncTask<Void, Void, Boolean> {
	
	
	private static final String CLIENT_ID = "39va9ZXtJS21GVYFGaf6";
	private static final String CLIENT_TOKEN = "xqYAW+fcf8icgErpva8EVJHZx5gzBEH4iWOZMmlxiGo=";
	
	private static final String TAG = "LanguageChanger";
	
	private ProgressDialog dialog;
	private Mensa mensa;
	private Context context;
	private MenuListAdapter adapter;

	
	public LanguageChanger(Context context, Mensa mensa) {
		this.dialog = new ProgressDialog(context);
		this.mensa = mensa;
		this.context = context;
		Translate.setClientId(CLIENT_ID);
		Translate.setClientSecret(CLIENT_TOKEN);
	}
	
	protected void onPreExecute() {
        this.dialog.setMessage("Translating menu data...");
        this.dialog.show();
    }
	
	protected void onPostExecute(final Boolean success) {
		if (dialog.isShowing()) {
			dialog.dismiss();
		}
		
		if(success) {
			Toast.makeText(context, "Menus have been translated", Toast.LENGTH_SHORT).show();
			adapter.update();
			adapter.notifyDataSetChanged();
		} else {
			Toast.makeText(context, "Menus could not have been translated", Toast.LENGTH_SHORT).show();
		}
		
	}
	
	public void setAdapter(MenuListAdapter adapter) {
		this.adapter = adapter;
	}
	
	private String translate(String text) {
		String result = "";
		try {
			result = Translate.execute(text, Language.GERMAN, Language.ENGLISH);
		} catch (Exception e) {
			Log.e(TAG, e.getMessage());
			for(StackTraceElement s: e.getStackTrace()) {
				Log.e(TAG, s.toString());
			}
		}
		return result;
	}
	
	protected Boolean doInBackground(Void... params) {
		String result = "";
		int success = 0;
		for(Menuplan m: mensa.getWeeklyMenu()) {
			for(DailyMenu d : m) {
				result = this.translate(d.getMenu().replace("\n", "\r\n"));
				if(result.length() > 1) {
					d.setMenu(result);
					success+=1;
				}
				result = "";
				result = this.translate(d.getTitle());
				if(result.length() > 1) {
					d.setTitle(result);
					success+=1;
				}
			}
		}
		if(success >= 2) {
			mensa.setLanguage(Language.ENGLISH);
			return true;
		}
		return false;
	}
}