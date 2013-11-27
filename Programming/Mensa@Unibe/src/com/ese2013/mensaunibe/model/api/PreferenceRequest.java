package com.ese2013.mensaunibe.model.api;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.ese2013.mensaunibe.App;

/**
 * @author group7
 * @author Jan Binzegger
 */

public class PreferenceRequest {
	private Context context = App.getAppContext();
	private SharedPreferences sharedPref = context.getSharedPreferences("com.ese2013.mensaunibe.PREFERENCE_FILE_KEY", Context.MODE_PRIVATE);
	
	/**
	 * save favorite mensa setting to memory 
	 * @param toWrite
	 * @param mensaId
	 */
	public void writePreference(boolean toWrite, int mensaId) {
		SharedPreferences.Editor editor = sharedPref.edit();
		editor.putBoolean("MENSA_IS_FAVORIT_" + mensaId, toWrite);
		editor.commit();
	}
	
	/**
	 * save notification setting to memory 
	 * @param notify: if notification is on/off
	 * @param keywords : list of keywords
	 */
	public void writeNotificationKeywords(ArrayList<String> keywords) {
		SharedPreferences.Editor editor = sharedPref.edit();
		StringBuilder builder = new StringBuilder();
		for(String k : keywords) {
			builder.append(k);
			builder.append(",");
		}
		builder.deleteCharAt( builder.length()-1 );
		editor.putString("KEYWORDS_NOTIFY_LIST", builder.toString());
		editor.commit();
		Log.v("prefs save","save keywords: "+builder.toString());
	}
	
	@SuppressLint("CommitPrefEdits")
	public void writeNotification(boolean notify) {
		SharedPreferences.Editor editor = sharedPref.edit();
		editor.putBoolean("KEYWORDS_NOTIFY_STATUS", notify);
		editor.commit();
		Log.v("prefs save","save notify status: "+notify);
	}
	
	/**
	 * reads favorite mensa setting out of the memory
	 * @param mensaId
	 * @return
	 */
	public boolean readPreference (int mensaId) {
		return sharedPref.getBoolean("MENSA_IS_FAVORIT_" + mensaId, false);
	}
	
	/**
	 * reads favorite mensa setting out of the memory
	 * @param mensaId
	 * @return
	 */
	public boolean readNotification () {
		boolean b = sharedPref.getBoolean("KEYWORDS_NOTIFY_STATUS", false);
		Log.v("read prefs","notify status: "+b);
		return b;
	}
	public ArrayList<String> readNotificationKeywords() {
		String keywords = sharedPref.getString("KEYWORDS_NOTIFY_LIST", "");
		ArrayList<String> result = new ArrayList<String>();
		if(keywords.length() > 1) {
			for(String k : keywords.split(",")) {
				result.add(k);
			}
		}
		Log.v("read prefs", "keywords: "+keywords);
		return result;
	}
}
