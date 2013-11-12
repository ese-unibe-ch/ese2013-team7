package com.ese2013.mensaunibe.model.mensa;

import com.ese2013.mensaunibe.ListItem;
import com.ese2013.mensaunibe.model.MenuDate;
import com.ese2013.mensaunibe.model.api.MyLocation;
import com.ese2013.mensaunibe.model.api.PreferenceRequest;
import com.ese2013.mensaunibe.model.menu.Menuplan;
import com.ese2013.mensaunibe.model.menu.WeeklyMenu;
import com.memetix.mst.language.Language;

import android.annotation.SuppressLint;
import android.location.Location;

/**
 * @author group7
 * @author Andreas Hohler
 * @author Sandor Torok
 */

@SuppressLint("DefaultLocale")
public class Mensa implements ListItem {
	private int id;
	private String name;
	private String street;
    private String plz;
    private Double lat;
    private Double lon;
    private WeeklyMenu menu;
    private boolean isFavorite;
    private Location location;
    private Language language;
    
    /**
     * creates the Mensa object with the MensaBuilder data
     * @param mb MensaBuilder
     */
    public Mensa(MensaBuilder mb) {
    	assert mb != null;
    	id = mb.getId();
    	name = mb.getName();
    	street = mb.getStreet();
    	plz = mb.getPlz();
    	lat = mb.getLat();
    	lon = mb.getLon();
    	isFavorite = mb.getFav();
    	setupLocation(lat, lon);
    	language = Language.GERMAN;
    }
    
    /**
     * setup the location with coordinates
     * @param latitude
     * @param longitude
     */
	private void setupLocation(Double latitude, Double longitude) {
		location = new Location(name);
		location.setLatitude(latitude);
		location.setLongitude(longitude);
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getPlz() {
		return plz;
	}
	public void setPlz(String plz) {
		this.plz = plz;
	}
	public Double getLat() {
		return lat;
	}
	public void setLat(Double lat) {
		this.lat = lat;
	}
	public Double getLon() {
		return lon;
	}
	public void setLon(Double lon) {
		this.lon = lon;
	}
	public void setLanguage(Language language) {
		this.language = language;
	}
	public Language getLanguage() {
		return language;
	}
	
	public String toString() {
        return String.format("mensa:%s,street:%s,plz:%s,lon:%f,lat:%f,id:%d,menu:%s", name, street, plz, lat, lon, id, menu);
    }

	public WeeklyMenu getWeeklyMenu() {
		return menu;
	}

	public void setWeeklyMenu(WeeklyMenu menu) {
		this.menu = menu;
	}

	public Menuplan getDailyMenu(MenuDate date) {
		return menu.getDailyMenu(date);
	}

	@Override
	public boolean isSection() {
		return false;
	}

	public boolean isFavorite() {
		return isFavorite;
	}

	public void setFavorite(boolean b) {
		this.isFavorite = b;
		PreferenceRequest pr = new PreferenceRequest();
		pr.writePreference(b, id);
	}
	
	public Location getLocation(){
		return location;
	}
	
	public String getDistance(MyLocation myLocation){
		assert myLocation != null;
		float distanceInMeters = location.distanceTo(myLocation.getLocation());
		String meters = formatDist(distanceInMeters);
		return meters;
	}

	private static String formatDist(float meters) {
		if (meters < 1000) {
			return ((int) meters) + " m";
		} else if (meters < 10000) {
			return formatDec(meters / 1000f, 1) + " km";
		} else {
			return ((int) (meters / 1000f)) + " km";
		}
	}
	
	private  static String formatDec(float val, int dec) {
		int factor = (int) Math.pow(10, dec);
		int front = (int) (val);
		int back = (int) Math.abs(val * (factor)) % factor;

		return front + "." + back;
	}
}