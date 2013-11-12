package com.ese2013.mensaunibe.model.mensa;

import org.json.JSONObject;

import android.util.Log;

import com.ese2013.mensaunibe.model.api.PreferenceRequest;

/**
 * @author group7
 * @author Andreas Hohler
 */

public class MensaBuilder {
	private static final String TAG = "MensaBuilder";
	private int id;
	private String name;
	private String street;
	private String plz;
	private Double lat;
	private Double lon;
	private boolean fav;
	
	/**
	 * uses the json object input and parse the data
	 * @param o JsonObject
	 */
	public void parseJson(JSONObject o) {
		try {
			id = o.getInt("id");
			name = o.getString("mensa");
			street = o.getString("street");
			plz = o.getString("plz");
			lat = o.getDouble("lat");
			lon = o.getDouble("lon");
			loadFavorit();
		} catch(Exception e) {
			Log.e(TAG, e.getMessage());
		}
	}
	
	/**
	 * creates a mensa object out of itself
	 * @return Mensa object
	 */
	public Mensa create() {
		return new Mensa(this);
	}
	
	public int getId() { return id; }
	public String getName() { return name; }
	public String getStreet() { return street; }
	public String getPlz() { return plz; }
	public Double getLat() { return lat; }
	public Double getLon() { return lon; }
	public boolean getFav() { return fav; }

	/**
	 * loads the user favorite setting of one mensa
	 */
	public void loadFavorit() {
		PreferenceRequest pr = new PreferenceRequest();
		fav = pr.readPreference(id);		
	}
	
}
