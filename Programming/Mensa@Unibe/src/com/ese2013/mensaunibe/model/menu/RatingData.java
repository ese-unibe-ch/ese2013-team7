package com.ese2013.mensaunibe.model.menu;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import com.ese2013.mensaunibe.RatingListAdapter;
import com.ese2013.mensaunibe.model.api.ApiUrl;
import com.ese2013.mensaunibe.model.api.AppUtils;
import com.ese2013.mensaunibe.model.api.JSONParser;
import com.ese2013.mensaunibe.model.api.URLRequest;

import android.util.Log;
import android.widget.Toast;


public class RatingData extends AsyncTask<Void, Void, String> {
	
	private static final String TAG = "RatingData";
	public static final int TYPE_LOAD = 1;
	public static final int TYPE_SAVE = 2;
	
	private ProgressDialog dialog;
	private String menu;
	private String menuRaw;
	private int mensaId;
	private Context context;
	private RatingListAdapter adapter;
	private JSONParser parser;
	private String url;
	private String text;
	private int rating;
	private String username;
	private int type;
	private String postData;
	
	public RatingData(Context context, String menu, int mensaId, int type) {
		assert context != null && menu.length() > 2 && type != 0;
		this.dialog = new ProgressDialog(context);
		//this.menu = menu;
		this.context = context;
		this.mensaId = mensaId;
		this.menuRaw = menu;
		parser = new JSONParser();
		//String[] menu2 = this.menu.split("\n");
		//this.menu = menu2[0];
		this.menu = parseMenuTitle( menu );
		this.type = type;
		if(type == TYPE_LOAD) url = ApiUrl.API_RATING_GET + "?androidrequest&mensaid="+this.mensaId
				+"&menutitle="+this.menu.replace(" ", "%20");
		else if(type == TYPE_SAVE) url = ApiUrl.API_RATING_POST;
		Log.v(TAG, url);
	}
	
	private String parseMenuTitle( String menu ) {
		String[] tmp = menu.split("\n");
		return tmp[0];
	}
	public void setPostData(String nickname, String text, int rating) {
		this.text = text;
		this.rating = rating;
		this.username = nickname;
		this.postData = "androidrequest&mensaId="+mensaId+"&usernamemd5="
				+nickname+"&menutitle="+menu.replace(" ", "%20")+"&stars="+rating+"&comment="+text;
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
				ArrayList<Rating> r = new ArrayList<Rating>();
				JSONObject json = parser.parse( result );
				if( !json.has("content") ) {
					Toast.makeText(context, "No ratings available for this menu", Toast.LENGTH_SHORT).show();
				} else {
					float avg = 0;
					try {
						avg = (float) json.getDouble("avgstars");
						JSONArray ratings = json.getJSONArray("content");
						JSONObject rating;
						for(int i = 0; i< ( json.length() == 1 ? 0 : json.length() ); i++) {
							if(!ratings.isNull(i)) {
								rating = ratings.getJSONObject(i);
								r.add( new Rating( rating.getString("username"), rating.getString("comment"), rating.getInt("stars")) );
							}
							
						}
					} catch(Exception e) {
							Log.e(TAG, e.getMessage());
							StackTraceElement[] tt = e.getStackTrace();
							for(StackTraceElement t : tt) {
								Log.e(TAG, t.toString());
							}
					}
					Toast.makeText(context, "Menu ratings habe been loaded", Toast.LENGTH_SHORT).show();
					adapter.populate(r, avg);
					adapter.notifyDataSetChanged();
				}
			} else {
				Toast.makeText(context, "Menu ratings could not be loaded", Toast.LENGTH_SHORT).show();
			}
		} else
		if(type == TYPE_SAVE) {
			Toast.makeText(context, "Your rating has been saved", Toast.LENGTH_SHORT).show();
			//adapter.add( new Rating( this.username, this.text, this.rating ) );
			//adapter.notifyDataSetChanged();
			Intent intent = new Intent();
			intent.setClassName(context.getPackageName(), context.getPackageName()+".RatingActivity");
			intent.putExtra("menu", this.menuRaw);
			intent.putExtra(AppUtils.MENSA_ID, this.mensaId);
			context.startActivity(intent);
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