package com.ese2013.mensaunibe.model.api;

import android.os.AsyncTask;
import android.util.Log;

import com.memetix.mst.language.Language;
import com.memetix.mst.translate.Translate;

//ATM unused
public class Translation {
	private class AsyncTranslation extends AsyncTask<String, Void, String> {
		private Language langFrom;
		private Language langTo;
		private String text;
		
		public AsyncTranslation(String text, Language langFrom, Language langTo) {
			this.langFrom = langFrom;
			this.langTo = langTo;
			this.text = text;
		}
		
		private String request() {
			String result = "";
			try {
				result = Translate.execute(text, langFrom, langTo);
			} catch (Exception e) {
				Log.e(TAG, e.getMessage());
				for(StackTraceElement s: e.getStackTrace()) {
					Log.e(TAG, s.toString());
				}
			}
			return result;
		}
		@Override
		protected String doInBackground(String... params) {
			return this.request();
		}
		
		
	}
	
	private static final String CLIENT_ID = "39va9ZXtJS21GVYFGaf6";
	private static final String CLIENT_TOKEN = "xqYAW+fcf8icgErpva8EVJHZx5gzBEH4iWOZMmlxiGo=";

	public static final String TAG = "API_Translate";
	
	private String text;
	private Language langFrom;
	private Language langTo;
	
	public Translation() {
		Translate.setClientId(CLIENT_ID);
		Translate.setClientSecret(CLIENT_TOKEN);
	}
	
	public void setLanguage(Language lang, Language toLang) {
		this.langFrom = lang;
		this.langTo = toLang;
	}
	
	public void setText(String text) {
		this.text = text;
	}

	public String translate() {
		AsyncTranslation as = new AsyncTranslation(text, langFrom, langTo);
		as.execute();
		String result = "";
		try {
			result = as.get();
		} catch(Exception e) {
			Log.e(TAG, e.getMessage());
		}
		return result;
	}
}
