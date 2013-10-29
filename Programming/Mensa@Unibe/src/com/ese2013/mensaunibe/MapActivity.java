package com.ese2013.mensaunibe;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.Document;

import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.Menu;

import com.ese2013.mensaunibe.model.api.GetDirectionsAsyncTask;
import com.ese2013.mensaunibe.model.api.GetMapDirection;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.Toast;
import android.view.MenuItem;
import android.view.Menu;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.ese2013.mensaunibe.model.Model;
import com.ese2013.mensaunibe.model.api.AppUtils;
import com.ese2013.mensaunibe.MenuListAdapter;
import com.memetix.mst.language.Language;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class MapActivity extends ActionBarActivity {
		private final LatLng mensaLocation;
		private GoogleMap map;
		
		MapActivity(LatLng cord){
			mensaLocation=cord;
		}
	  @Override
	  	protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.fragment_map);
	    //Setup action bar
	    ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		
	    map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
	    Marker mensaMarker = map.addMarker(new MarkerOptions().position(mensaLocation));
	    
	   
	    // add User Location
	    findDirections(46.94824814351828, 7.440162956845597,
                mensaMarker.getPosition().latitude, mensaMarker.getPosition().longitude, GetMapDirection.MODE_WALKING );
  }

	  @Override
	  public boolean onCreateOptionsMenu(Menu menu) {
   // getMenuInflater().inflate(R.menu.activity_main, menu);
	    return true;
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

}
