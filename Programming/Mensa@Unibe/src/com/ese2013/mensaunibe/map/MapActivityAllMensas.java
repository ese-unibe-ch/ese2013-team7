package com.ese2013.mensaunibe.map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.ese2013.mensaunibe.R;
import com.ese2013.mensaunibe.R.id;
import com.ese2013.mensaunibe.R.layout;
import com.ese2013.mensaunibe.R.menu;
import com.ese2013.mensaunibe.model.Model;
import com.ese2013.mensaunibe.model.mensa.Mensa;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.Toast;

/**
 * @author group7
 * @author Marc Dojtschinov
 */

public class MapActivityAllMensas extends BaseMapActivity {
	protected ArrayList<Mensa> mensas;
	protected GoogleMap map;
	protected MyLocation mLocation;
	protected LatLng mLocationLatLng;
	private boolean locationReady = false;
	private Marker mensaMarker;
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
	public boolean onCreateOptionsMenu(Menu menu) {
		  getMenuInflater().inflate(R.menu.actionbar_map, menu);
	    return super.onCreateOptionsMenu(menu);
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
                    map.moveCamera( CameraUpdateFactory.newLatLngZoom(new LatLng(mensas.get(1).getLat(),mensas.get(1).getLon()),14.0f) );
                    for(int i=0; i< mensas.size();i++){
                		mensaMarker = map.addMarker(new MarkerOptions().position(new LatLng(mensas.get(i).getLat(),mensas.get(i).getLon()))
                				.title(mensas.get(i).getName()));
                		}
                }
            });}
	}
		
		
	
		
}
