package com.ese2013.mensaunibe.api;


import java.util.ArrayList;

import org.json.*;


/** Very important source:
 * http://stackoverflow.com/questions/1688099/converting-json-to-java/1688182#1688182
 */


public class MensaData {

	private JSONParser parser;
	private RequestData rq;
	
	public MensaData() {
		parser = new JSONParser();
		rq = new RequestData();
	}
	
	/*public static void main( String[] args ) 
		MensaData md = new MensaData();
		md.getMensaList();
	}*/
	
	
	public ArrayList<Mensa> getMensaList() {
		ArrayList<Mensa> mensas = new ArrayList<Mensa>();
		rq.setUrl( MensaUrl.API_MENSA_LIST );
		try {
			JSONArray js = parser.parse( rq.request() );
		
			for(int i = 0; i<js.length(); i++) {
				JSONObject m = js.getJSONObject(i);
				MensaBuilder mb = new MensaBuilder();
				mb.parseJson(m);
				Mensa mensa = mb.create();
				mensas.add( mensa );
			}
		} catch(Exception e) {
			
		}
		return mensas;
	}
	
}