package com.ese2013.mensaunibe.model.menu;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.ese2013.mensaunibe.menu.RatingListAdapter;
import com.ese2013.mensaunibe.model.api.ApiUrl;
import com.ese2013.mensaunibe.model.api.JSONParser;
import com.ese2013.mensaunibe.model.data.URLRequest;

import android.util.Log;
import android.widget.Toast;

/* should be refactored in two classes for saving and storing */

/**
 * @author group7
 * @author Andreas Hohler
 */

public class RatingData extends AsyncTask<Void, Void, String> {
	
	private static final String TAG = "RatingData";
	public static final int TYPE_LOAD = 1;
	public static final int TYPE_SAVE = 2;
	
	private ProgressDialog dialog;
	private String menu;
	private int mensaId;
	private Context context;
	private RatingListAdapter adapter;
	private JSONParser parser;
	private String url;
	private int type;
	private String postData;
	
	
	public RatingData(Context context, String menu, int mensaId, int type) {
		assert context != null && menu.length() > 2 && type != 0;
		this.dialog = new ProgressDialog(context);
		this.context = context;
		this.mensaId = mensaId;
		parser = new JSONParser();
		this.menu = parseMenuTitle( menu );
		this.type = type;
		if(type == TYPE_LOAD) url = ApiUrl.API_RATING_GET + "&mensaid="+this.mensaId
				+"&menutitle="+this.menu.replace(" ", "%20");
		else if(type == TYPE_SAVE) url = ApiUrl.API_RATING_POST;
	}
	
	/**
	 * parse menu title (if VEGI+, take the second line)
	 * @param menu
	 * @return revelant menu title
	 */
	private String parseMenuTitle( String menu ) {
		String[] tmp = menu.split("\n");
		if(tmp[0].contains("VEGI+")) {
			return tmp[1];
		}
		tmp[0] = tmp[0].replaceAll(",|«|»", "");
		return encodeString(tmp[0]);
	}
	
	/**
	 * sets the data that will be posted to the API (submit rating)
	 * @param nickname
	 * @param text
	 * @param rating
	 */
	
	private String decodeString(String text) {
		String decodedText = text;
		try {
		  decodedText = URLDecoder.decode(text, "UTF-8");
		} catch (Exception e) {
		}
		return decodedText;
	}
	
	private String encodeString(String text) {
		String encodedText = text;
		try {
		    encodedText= URLEncoder.encode(text, "UTF-8");
		} catch (Exception e) {
		}
		return encodedText;
	}
	
	public void setPostData(String nickname, String text, int rating) {
		this.postData = "androidrequest=1&mensaid="+mensaId+"&usernamemd5="
				+nickname+"&menutitle="+menu.replace(" ", "%20")+"&stars="+rating+"&comment="+encodeString(text);
	}
	
	protected void onPreExecute() {
        if(this.type == TYPE_LOAD) this.dialog.setMessage("Load menu ratings...");
        else if(this.type == TYPE_SAVE) this.dialog.setMessage("Save menu rating...");
        this.dialog.show();
    }
	
	protected void onPostExecute(final String result) {
		try {
			if (dialog.isShowing()) {
				dialog.dismiss();
			}
		}catch(Exception e) {
			
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
						for(int i = 0; i< ( json.length() == 1 ? 0 : json.length()+1 ); i++) {
							if(!ratings.isNull(i)) {
								rating = ratings.getJSONObject(i);
								r.add( new Rating( rating.getString("username"), decodeString( rating.getString("comment") ), rating.getInt("stars"), rating.getLong("time")) );
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
		}
		
	}
	
	/**
	 * set the RatingListAdapter for updates
	 * @param adapter
	 */
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