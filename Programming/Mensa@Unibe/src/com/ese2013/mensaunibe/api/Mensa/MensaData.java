package com.ese2013.mensaunibe.api.Mensa;


import java.util.ArrayList;
import java.util.HashMap;

import org.json.*;

import android.util.Log;

import com.ese2013.mensaunibe.api.ApiUrl;
import com.ese2013.mensaunibe.api.DataRequest;


public class MensaData {
<<<<<<< HEAD
	private DataRequest rq;
=======
	private static final String TAG = "MensaData";
	private JSONParser parser;
	private RequestData rq;
>>>>>>> 5f0ef65e522163fed8e4dd40ee066789cd4fc7ac
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
				mensas.add( mensa );
				mlist.put( mensa.getName(), Integer.valueOf(mensa.getId()) );
			}
		} catch(Exception e) {
			Log.e(TAG, e.getMessage());
		}
		return mensas;
	}
	
}