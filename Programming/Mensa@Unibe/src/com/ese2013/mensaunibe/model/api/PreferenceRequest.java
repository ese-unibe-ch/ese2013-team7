package com.ese2013.mensaunibe.model.api;

import android.content.Context;
import android.content.SharedPreferences;

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
	 * reads favorite mensa setting out of the memory
	 * @param mensaId
	 * @return
	 */
	public boolean readPreference (int mensaId) {
		return sharedPref.getBoolean("MENSA_IS_FAVORIT_" + mensaId, false);
	}
}
