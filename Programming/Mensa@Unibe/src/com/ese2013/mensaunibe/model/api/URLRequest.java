package com.ese2013.mensaunibe.model.api;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class URLRequest {
	
	public String get(String inputUrl) throws Exception {
		String result = "";
		URL url;
		HttpURLConnection conn;
		BufferedReader rd;
		String line;
		url = new URL(inputUrl);
		conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		while ((line = rd.readLine()) != null) {
			result += line;
		}
		rd.close();
		if(conn != null) conn.disconnect();
		return result;	
	}
	
	public String post(String inputUrl, String postData) throws Exception {
		String result = "";
		URL url;
		HttpURLConnection conn;
		BufferedReader rd;
		DataOutputStream wr;
		String line;
		url = new URL(inputUrl);
		conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("POST");
		conn.setUseCaches (false);
	    conn.setDoInput(true);
	    conn.setDoOutput(true);
	       
	    wr = new DataOutputStream(conn.getOutputStream());
	    wr.writeBytes(postData);
	    wr.flush();
	    wr.close();
	    
		rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		while ((line = rd.readLine()) != null) {
			result += line;
		}
		rd.close();
		if(conn != null) conn.disconnect();
		return result;	
	}
}
