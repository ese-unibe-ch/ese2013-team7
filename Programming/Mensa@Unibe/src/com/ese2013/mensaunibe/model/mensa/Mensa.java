package com.ese2013.mensaunibe.model.mensa;

import com.ese2013.mensaunibe.ListItem;
import com.ese2013.mensaunibe.model.MenuDate;
import com.ese2013.mensaunibe.model.api.PreferenceRequest;
import com.ese2013.mensaunibe.model.menu.Menuplan;
import com.ese2013.mensaunibe.model.menu.WeeklyMenu;

import android.annotation.SuppressLint;

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
    
    public Mensa(MensaBuilder mb) {
    	id = mb.getId();
    	name = mb.getName();
    	street = mb.getStreet();
    	plz = mb.getPlz();
    	lat = mb.getLat();
    	lon = mb.getLon();
    	isFavorite = mb.getFav();
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
}
