package com.ese2013.mensaunibe.map;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;

import com.ese2013.mensaunibe.R;
import com.ese2013.mensaunibe.menu.MenuActivity;
import com.ese2013.mensaunibe.model.Model;
import com.ese2013.mensaunibe.model.mensa.Mensa;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * @author group7
 * @author Marc Dojtschinov
 */

public class MapActivityAllMensas extends BaseMapActivity {
	protected ArrayList<Mensa> mensas;
	protected GoogleMap map;
	protected MyLocation mLocation;
	protected LatLng mLocationLatLng;
	protected int mMensaId;

	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.fragment_map);
		
	    ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
	
		initilizeMap();	
	}

	
	@Override
	public void initilizeMap() {
        if (map == null) {
        	map = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
            // check if map is created successfully or not
            if (map != null) {
            	setUpMap();
            }
        }
    }
	
	public void setUpMap(){
		
		mensas = Model.getInstance().getMensaList();
		
		final View mapView = getSupportFragmentManager().findFragmentById(R.id.map).getView();
        if (mapView.getViewTreeObserver().isAlive()) {
            mapView.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                	addMensaMarker();
                    map.moveCamera( CameraUpdateFactory.newLatLngZoom(new LatLng(mensas.get(1).getLat(),mensas.get(1).getLon()),13.0f) );
                    
                }
            });
        }
	}
		
	private void addMensaMarker(){
		for(int i=0; i< mensas.size();i++){
    			map.addMarker(new MarkerOptions().position(new LatLng(mensas.get(i).getLat(),mensas.get(i).getLon()))
    				.title(mensas.get(i).getName()));
		}
		map.setOnInfoWindowClickListener(new OnInfoWindowClickListener(){
			@Override
			 public void onInfoWindowClick(Marker marker) {
				Intent intent = new Intent(MapActivityAllMensas.this, MenuActivity.class);
				intent.putExtra("int_value", Model.getInstance().getMensaIdByName(marker.getTitle()));
				MapActivityAllMensas.this.startActivity(intent); 
			 }
		});
	}		
}
