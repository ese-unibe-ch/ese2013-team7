package com.example.mensaunibe.api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


public class MensaData {
	private final static String API_TOKEN = "6112255ca02b3040711015bbbda8d955";
	private final static String API_MENSA_LIST = "http://mensa.xonix.ch/v1/mensas?tok="+API_TOKEN; 
	
	
	public TypeToken<List<String>> getMensaList() {
		return this.decodeJson( this.getHTTP( API_MENSA_LIST ) );
	}
	
	private TypeToken<List<String>> decodeJson(String jsonString) {
		Gson json = new Gson();
		/*ArrayList< ArrayList< ArrayList <String> > > mensas = json.fromJson(jsonString, ArrayList.class);*/
		TypeToken<List<String>> list = new TypeToken<List<String>>() {};
		Type listType = list.getType();
		list = json.fromJson(jsonString, listType);
		return list;
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
		return result;
	}
	
}