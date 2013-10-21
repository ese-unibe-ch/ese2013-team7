package com.ese2013.mensaunibe.model.api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;

import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;

public class DataRequest extends AsyncTask<String, Void, String> {
	private static final String TAG = "DataRequest";
	private String url;
	private String type;
	private JSONParser parser;
	
	public DataRequest() {
		this.url = "";
		parser = new JSONParser();
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public void setType(String type) {
		this.type = type;
	}
		
	public String request() {
		
		CacheHandler handler = new CacheHandler();
		String result = "";
		
		if (handler.needNewCache(type)) {
			URL url;
			HttpURLConnection conn;
			BufferedReader rd;
			String line;			
			try {
				url = new URL(this.url);
				conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("GET");
				rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				while ((line = rd.readLine()) != null) {
					result += line;
				}
				rd.close();
				handler.setNewCache(result, type);
			} catch (Exception e) {
				e.printStackTrace();
				Log.e(TAG, e.getMessage());
			}
		} else {		
			result = handler.loadCache(type);
		}
		return result;
	}
	
	public JSONObject getJSONData() throws InterruptedException, ExecutionException {
		return parser.parse( this.get() );
	}

	protected String doInBackground(String... arg0) {
		return this.request();
	}
}
