package com.ese2013.mensaunibe.api.Mensa;


import java.util.ArrayList;
import java.util.HashMap;

import org.json.*;

import com.ese2013.mensaunibe.api.ApiUrl;
import com.ese2013.mensaunibe.api.JSONParser;
import com.ese2013.mensaunibe.api.RequestData;


/** Very important source:
 * http://stackoverflow.com/questions/1688099/converting-json-to-java/1688182#1688182
 */


public class MensaData {

	private JSONParser parser;
	private RequestData rq;
	private final ArrayList<Mensa> mensas;
	private final HashMap<String,Integer> mlist;
	
	public MensaData() {
		parser = new JSONParser();
		rq = new RequestData();
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
			JSONArray js = parser.parse( rq.get() );
		
			for(int i = 0; i<js.length(); i++) {
				JSONObject m = js.getJSONObject(i);
				MensaBuilder mb = new MensaBuilder();
				mb.parseJson(m);
				Mensa mensa = mb.create();
				mensas.add( mensa );
				mlist.put( mensa.getName(), Integer.valueOf(mensa.getId()) );
			}
		} catch(Exception e) {
			
		}
		return mensas;
	}
	
}