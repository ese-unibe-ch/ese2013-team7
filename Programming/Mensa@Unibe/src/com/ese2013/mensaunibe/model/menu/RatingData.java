package com.ese2013.mensaunibe.model.menu;

import java.util.concurrent.ExecutionException;

import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.ese2013.mensaunibe.MenuActivity.TabCollectionPagerAdapter;
import com.ese2013.mensaunibe.RatingListAdapter;
import com.ese2013.mensaunibe.model.api.ApiUrl;
import com.ese2013.mensaunibe.model.api.JSONParser;
import com.ese2013.mensaunibe.model.api.URLRequest;
import com.ese2013.mensaunibe.model.mensa.Mensa;
import com.ese2013.mensaunibe.model.menu.DailyMenu;
import com.ese2013.mensaunibe.model.menu.Menuplan;
import com.memetix.mst.language.Language;

import android.util.Log;
import android.widget.Toast;

import com.memetix.mst.translate.Translate;


public class RatingData extends AsyncTask<Void, Void, String> {
	
	private static final String TAG = "RatingData";
	public static final int TYPE_LOAD = 1;
	public static final int TYPE_SAVE = 2;
	
	private ProgressDialog dialog;
	private String menu;
	private Context context;
	private RatingListAdapter adapter;
	private JSONParser parser;
	private String url;
	private String text;
	private int rating;
	private String nickname;
	private int type;
	private String postData;
	
	public RatingData(Context context, String menu, int type) {
		assert context != null && menu.length() > 2 && type != 0;
		this.dialog = new ProgressDialog(context);
		this.menu = menu;
		this.context = context;
		parser = new JSONParser();
		String[] menu2 = this.menu.split("\n");
		this.menu = menu2[0];
		this.type = type;
		if(type == TYPE_LOAD) url = ApiUrl.API_RATING_GET + "?androidrequest&menutitle="+menu;
		else if(type == TYPE_SAVE) url = ApiUrl.API_RATING_POST;
	}
	
	public void setPostData(String nickname, String text, int rating) {
		this.text = text;
		this.rating = rating;
		this.nickname = nickname;
		this.postData = "androidrequest&usernamemd5="+nickname+"menutitle="+menu+"stars="+rating+"comment="+text;
	}
	
	protected void onPreExecute() {
        this.dialog.setMessage("Load menu ratings...");
        this.dialog.show();
    }
	
	protected void onPostExecute(final String result) {
		if (dialog.isShowing()) {
			dialog.dismiss();
		}
		
		if(type == TYPE_LOAD) {
			if(result.length() > 2) {
				
				JSONObject json = parser.parse( result );
				
				Toast.makeText(context, "Menu ratings have been loaded", Toast.LENGTH_SHORT).show();
				adapter.notifyDataSetChanged();
			} else {
				Toast.makeText(context, "Menu ratings could not have been loaded", Toast.LENGTH_SHORT).show();
			}
		} else
		if(type == TYPE_SAVE) {
			
		}
		
	}
	
	public void setAdapter(RatingListAdapter adapter) {
		this.adapter = adapter;
	}
	
	protected String doInBackground(Void... params) {
		String result = "";
		URLRequest urlRequest = new URLRequest();
		try {
			if(this.type == TYPE_LOAD) {
				result = urlRequest.get(this.url);
			} else
			if(this.type == TYPE_SAVE) {
				result = urlRequest.post(this.url, this.postData);
			}
		} catch(Exception e) {
			e.printStackTrace();
			Log.e(TAG, e.getMessage());
		}
		return result;
	}
}