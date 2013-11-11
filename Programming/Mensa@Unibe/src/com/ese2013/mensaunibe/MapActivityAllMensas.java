package com.ese2013.mensaunibe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.ese2013.mensaunibe.model.Model;
import com.ese2013.mensaunibe.model.api.GetDirectionsAsyncTask;
import com.ese2013.mensaunibe.model.api.MyLocation;
import com.ese2013.mensaunibe.model.mensa.Mensa;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.widget.Toast;

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
	
		initializeMap();
		for(Mensa m: mensas){
		mensaMarker = map.addMarker(new MarkerOptions().position(new LatLng(mensas.get(m.getId()).getLat(),mensas.get(m.getId()).getLon()))
				.title(mensas.get(m.getId()).getName()));
		}
		
	}
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		  getMenuInflater().inflate(R.menu.actionbar_mensa_list, menu);
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

	public void findDirections(double fromPositionDoubleLat, double fromPositionDoubleLong, double toPositionDoubleLat,
			double toPositionDoubleLong, String mode) {
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
	public void onResume() {
	    super.onResume();
	    initializeMap();
	}

	public void locationReady(boolean b) {
		if(b){
			locationReady = true;
		}else locationReady = false;
	}
	
	public void setUpMap(){
		if(mLocation == null){
			mLocation = MyLocation.getInstance();
			mLocation.setActivity(this);
		}
		mensas = Model.getInstance().getMensaList();
		mLocationLatLng =new LatLng(mLocation.getLocation().getLatitude(),mLocation.getLocation().getLongitude());	
	}
	
	protected void initializeMap() {
        if (map == null) {
        	setUpMap();
        	map = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
        	map.moveCamera( CameraUpdateFactory.newLatLngZoom(new LatLng(mLocationLatLng.latitude, mLocationLatLng.longitude),14.0f) );
            // check if map is created successfully or not
            if (map == null) {
                Toast.makeText(getApplicationContext(),
                        "Sorry! unable to create maps", Toast.LENGTH_SHORT)
                        .show();
            }
        }
    }
}
