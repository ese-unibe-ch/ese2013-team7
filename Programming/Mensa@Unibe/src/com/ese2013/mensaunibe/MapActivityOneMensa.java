package com.ese2013.mensaunibe;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.Menu;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.Toast;

import com.ese2013.mensaunibe.model.Model;
import com.ese2013.mensaunibe.model.mensa.Mensa;
import com.ese2013.mensaunibe.model.api.GetDirectionsAsyncTask;
import com.ese2013.mensaunibe.model.api.GetMapDirection;
import com.ese2013.mensaunibe.model.api.MyLocation;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;


import android.support.v7.app.ActionBarActivity;

public class MapActivityOneMensa extends BaseMapActivity  {
		private Mensa mensa;
		private GoogleMap map;
		private MyLocation mLocation;
		private LatLng mLocationLatLng;
		private boolean locationReady = false;
		private Marker mensaMarker;
		private int mMensaId;
		
	
	  @Override
	  	protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.fragment_map);
	    //Setup action bar
	    ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		
				
		initilizeMap();
		mensaMarker = map.addMarker(new MarkerOptions().position(new LatLng(mensa.getLat(),mensa.getLon()))
				.title(mensa.getName()));	   
		findDirections(mLocationLatLng.latitude, mLocationLatLng.longitude,
             mensaMarker.getPosition().latitude, mensaMarker.getPosition().longitude, GetMapDirection.MODE_WALKING );
	
		}
	 
	
	@Override
	  public boolean onCreateOptionsMenu(Menu menu) {
		  getMenuInflater().inflate(R.menu.actionbar_map, menu);
	    return super.onCreateOptionsMenu(menu);
	  }


	public void handleGetDirectionsResult(ArrayList<LatLng> directionPoints) {
		 Polyline newPolyline;
		 PolylineOptions rectLine = new PolylineOptions().width(3).color(Color.RED);		 
		    for(int i = 0 ; i < directionPoints.size() ; i++) 
		    {          
		        rectLine.add(directionPoints.get(i));
		    }
		    newPolyline = map.addPolyline(rectLine);
		
	}


	@Override
	public void findDirections(double fromPositionDoubleLat, double fromPositionDoubleLong, double toPositionDoubleLat, double toPositionDoubleLong, String mode) {
		Map<String, String> map = new HashMap<String, String>();
	    map.put(GetDirectionsAsyncTask.USER_CURRENT_LAT, String.valueOf(fromPositionDoubleLat));
	    map.put(GetDirectionsAsyncTask.USER_CURRENT_LONG, String.valueOf(fromPositionDoubleLong));
	    map.put(GetDirectionsAsyncTask.DESTINATION_LAT, String.valueOf(toPositionDoubleLat));
	    map.put(GetDirectionsAsyncTask.DESTINATION_LONG, String.valueOf(toPositionDoubleLong));
	    map.put(GetDirectionsAsyncTask.DIRECTIONS_MODE, mode);

	    GetDirectionsAsyncTask asyncTask = new GetDirectionsAsyncTask(this);
	    asyncTask.execute(map); 
		
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
	@Override
	public void onResume() {
        super.onResume();
        mLocation.callOnResume();
	}
	@Override
	public void onStop() {
		super.onStop();
		mLocation.callOnStop();
		
	}

	@Override
	public void onPause() {
		super.onPause();
		mLocation.callOnPause();
		
	}
	@Override
	public void onStart() {
		super.onStart();
		mLocation.callOnStart();
	}

	
	public void setUpMap(){
		if(mLocation == null){
			mLocation = MyLocation.getInstance();
			mLocation.setActivity(this);
		}
		mMensaId = getIntent().getIntExtra("int_value",0);
		mensa = Model.getInstance().getMensaById(mMensaId);
		mLocationLatLng =new LatLng(mLocation.getLocation().getLatitude(),mLocation.getLocation().getLongitude());
		
		final View mapView = getSupportFragmentManager().findFragmentById(R.id.map).getView();
        if (mapView.getViewTreeObserver().isAlive()) {
            mapView.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
                @SuppressLint("NewApi") // We check which build version we are using.
                @Override
                public void onGlobalLayout() {
                    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
                      mapView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                    } else {
                      mapView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                    }
                    map.moveCamera( CameraUpdateFactory.newLatLngZoom(new LatLng(mLocationLatLng.latitude, mLocationLatLng.longitude),14.0f) );
                }
            });}
		
	}
	
	public void locationReady(boolean b){
		if(b){
			locationReady = true;
		}else locationReady = false;
	}
	
}
