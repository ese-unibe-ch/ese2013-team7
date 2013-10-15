package com.ese2013.mensaunibe.api;

import org.json.JSONObject;

public class MensaBuilder {
	private int id;
	private String name;
	private String street;
	private String plz;
	private Double lat;
	private Double lon;
	
	public MensaBuilder() {
	}
	
	public void parseJson(JSONObject o) {
		try {
			id = o.getInt("id");
			name = o.getString("mensa");
			street = o.getString("street");
			plz = o.getString("plz");
			lat = o.getDouble("lat");
			lon = o.getDouble("lon");
		} catch(Exception e) {
		}
	}
	
	public Mensa create() {
		return new Mensa(this);
	}
	
	public int getId() { return id; }
	public String getName() { return name; }
	public String getStreet() { return street; }
	public String getPlz() { return plz; }
	public Double getLat() { return lat; }
	public Double getLon() { return lon; }
	
}
