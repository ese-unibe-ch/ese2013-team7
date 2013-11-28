package com.ese2013.mensaunibe.model.data;

import java.util.concurrent.ExecutionException;

import org.json.JSONObject;

import com.ese2013.mensaunibe.model.api.JSONParser;

import android.os.AsyncTask;
import android.util.Log;

/**
 * @author group7
 * @author Andreas Hohler
 * @author Jan Binzegger
 */

public class DataRequest extends AsyncTask<String, Void, String> {
	private static final String TAG = "DataRequest";
	private String url;
	private String type;
	private JSONParser parser;
	private boolean forceReload;
	
	public DataRequest() {
		this.url = "";
		parser = new JSONParser();
	}
	
	/**
	 * Set the url to where the request will be sent
	 * @param url: Url as string
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	
	/**
	 * sets the type of the request
	 * @param type: contains the type (filename)
	 */
	public void setType(String type) {
		this.type = type;
		this.forceReload = false;
	}
	
	/**
	 * sets the type of the request and can handle force reload
	 * @param type: contains the type (filename)
	 * @param forceReload: true if yes, otherelse false
	 */
	public void setType(String type, boolean forceReload) {
		this.type = type;
		this.forceReload = forceReload;
	}
	
	/**
	 * handles the request
	 * @return string of the result
	 */
	private String request() {
		
		CacheHandler handler = new CacheHandler();
		String result = "";
		URLRequest urlRequest = new URLRequest();
		
		if (handler.needNewCache(type) || this.forceReload) {
			try {
				result = urlRequest.get(this.url);
				handler.setNewCache(result, type);
			} catch(Exception e) {
				e.printStackTrace();
				Log.e(TAG, e.getMessage());
			}
			
			
		} else {		
			result = handler.loadCache(type);
		}
		return result;
	}
	
	/**
	 * return the data from the request as json object
	 * @return JSONObject
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	public JSONObject getJSONData() throws InterruptedException, ExecutionException {
		return parser.parse( this.get() );
	}

	protected String doInBackground(String... arg0) {
		return this.request();
	}
}
