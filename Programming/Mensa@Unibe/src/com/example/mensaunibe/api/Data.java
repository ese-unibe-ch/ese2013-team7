package com.example.mensaunibe.api;

import android.annotation.SuppressLint;
import java.util.List;

@SuppressLint("DefaultLocale")
public class Data {
    private String mensa;
    private String street;
    private String plz;
    private Float lat;
    private Float lon;
    private int id;
    private List<Data> content;

    public String getMensa() { return mensa; }
    public String getStreet() { return street; }
    public String getPlz() { return plz; }
    public Float getLat() { return lat; }
    public Float getLon() { return lon; }
    public int getId() { return id; }
    public List<Data> getContents() { return content; }

    public void setMensa(String mensa) { this.mensa = mensa; }
    public void setStreet(String street) { this.street = street; }
    public void setPlz(String plz) { this.plz = plz; }
    public void setLat(Float lat) { this.lat = lat; }
    public void setLon(Float lon) { this.lon = lon; }
    public void setId(int id) { this.id = id; }
    public void setContents(List<Data> content) { this.content = content; }

    public String toString() {
        return String.format( "mensa:%s,street:%s,plz:%s,lon:%f,lat:%f,id:%d,contents:%s", mensa, street, plz, lat, lon, id, content);
    }
}