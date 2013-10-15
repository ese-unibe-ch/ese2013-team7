package com.example.mensaunibe.api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import com.google.gson.Gson;

/** Very important source:
 * http://stackoverflow.com/questions/1688099/converting-json-to-java/1688182#1688182
 */


public class MensaData {
	private final static String API_TOKEN = "6112255ca02b3040711015bbbda8d955";
	private final static String API_MENSA_LIST = "http://mensa.xonix.ch/v1/mensas?tok="+API_TOKEN; 
	
	/*public static void main( String[] args ) {
		MensaData md = new MensaData();
		List<Data> d = md.getMensaList();
		for(Data g : d) {
			System.out.println(g);
		}
	}*/
	
	public List<Data> getMensaList() {
		Data d =  this.decodeJson( this.getHTTP( API_MENSA_LIST ) );
		return d.getContents();
		
	}
	
	private Data decodeJson(String jsonString) {
		Gson json = new Gson();
		Data data = json.fromJson(jsonString, Data.class);
		return data;
	}
	
	private String getHTTP(String urlString) {
		URL url;
		HttpURLConnection conn;
		BufferedReader rd;
		String line;
		String result = "";
		try {
			url = new URL(urlString);
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			while ((line = rd.readLine()) != null) {
				result += line;
			}
			rd.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		result = result.replace("{  \"result\":","");
		result = result.substring(0, result.length()-1 );
		return result;
	}
	
}