package com.ese2013.mensaunibe.api.Mensa;

import android.annotation.SuppressLint;

@SuppressLint("DefaultLocale")
public class Mensa {
	private int id;
	private String name;
	private String street;
    private String plz;
    private Double lat;
    private Double lon;
    
    public Mensa(MensaBuilder mb) {
    	id = mb.getId();
    	name = mb.getName();
    	street = mb.getStreet();
    	plz = mb.getPlz();
    	lat = mb.getLat();
    	lon = mb.getLon();
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
        return String.format( "mensa:%s,street:%s,plz:%s,lon:%f,lat:%f,id:%d", name, street, plz, lat, lon, id);
    }
    
    
}
