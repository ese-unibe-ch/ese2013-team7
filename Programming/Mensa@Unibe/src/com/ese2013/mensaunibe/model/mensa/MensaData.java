package com.ese2013.mensaunibe.model.mensa;


import java.util.ArrayList;
import java.util.HashMap;

import org.json.*;

import android.util.Log;

import com.ese2013.mensaunibe.model.api.ApiUrl;
import com.ese2013.mensaunibe.model.api.DataRequest;
import com.ese2013.mensaunibe.model.menu.MenuData;


public class MensaData {
	private static final String TAG = "MensaData";
	
	private DataRequest rq;
	private final ArrayList<Mensa> mensas;
	private final HashMap<String,Integer> mlist;
	
	public MensaData() {
		rq = new DataRequest();
		mensas = new ArrayList<Mensa>();
		mlist = new HashMap<String,Integer>();
	}
	
	/*public static void main( String[] args )
	{
		MensaData md = new MensaData();
		ArrayList<Mensa> mensas = md.getMensaList();
		for(Mensa m : mensas) {
			System.out.println( m.getName() );
		}
	}*/
	
	public HashMap<String,Integer> getMensaHashMap() {
		return mlist;
	}
	
	public ArrayList<Mensa> getMensaList() {
		rq.setUrl( ApiUrl.API_MENSA_LIST );
		rq.execute();
		try {
			JSONArray js = rq.getJSONData().getJSONArray("content");
					
			for(int i = 0; i<js.length(); i++) {
				JSONObject m = js.getJSONObject(i);
				MensaBuilder mb = new MensaBuilder();
				mb.parseJson(m);
				Mensa mensa = mb.create();
				MenuData md = new MenuData();
				mensa.setWeeklyMenu( md.getWeeklyMenuList( mensa.getId() ) );
				Log.e(TAG, mensa.toString());
				mensas.add( mensa );
				mlist.put( mensa.getName(), Integer.valueOf(mensa.getId()) );
			}
		} catch(Exception e) {
			Log.e(TAG, e.getMessage());
		}
		return mensas;
	}
	
}