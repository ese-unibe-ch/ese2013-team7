package com.ese2013.mensaunibe.model.mensa;


import java.util.ArrayList;
import org.json.*;
import android.util.Log;

import com.ese2013.mensaunibe.model.api.ApiUrl;
import com.ese2013.mensaunibe.model.api.DataRequest;
import com.ese2013.mensaunibe.model.menu.MenuData;

/**
 * @author group7
 * @author Andreas Hohler
 */

public class MensaData {
	private static final String TAG = "MensaData";
	
	private DataRequest rq;
	private final ArrayList<Mensa> mensas;

	public MensaData() {
		rq = new DataRequest();
		mensas = new ArrayList<Mensa>();
	}
	
	/**
	 * returns a ArrayList of all loaded Mensas without reload
	 * @return ArrayList of Mensas
	 */
	public ArrayList<Mensa> getMensaList() {
		return getMensaList(false);
	}
	
	/**
	 * Initial start of the loading process
	 * Sets all needed settings for the request
	 * @param forceReload: true if force reload, or false if not
	 * @return ArrayList of loaded Mensas
	 */
	public ArrayList<Mensa> getMensaList(boolean forceReload) {
		rq.setUrl( ApiUrl.API_MENSA_LIST );
		rq.setType( ApiUrl.API_TYP_MENSA, forceReload );
		try {
			rq.execute();
			JSONArray js = rq.getJSONData().getJSONArray("content");
					
			for(int i = 0; i<js.length(); i++) {
				JSONObject m = js.getJSONObject(i);
				MensaBuilder mb = new MensaBuilder(m);
				Mensa mensa = mb.create();
				MenuData md = new MenuData();
				mensa.setWeeklyMenu( md.getWeeklyMenuList( mensa.getId() ) );
				//Log.i(TAG, mensa.toString());
				mensas.add( mensa );
			}
		}
		catch(Exception e) {
			Log.e(TAG, e.getMessage());
		}
		return mensas;
	}
}