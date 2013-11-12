package com.ese2013.mensaunibe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;

import com.ese2013.mensaunibe.model.api.GetDirectionsAsyncTask;
import com.ese2013.mensaunibe.model.api.MyLocation;
import com.ese2013.mensaunibe.model.mensa.Mensa;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

/**
 * @author group7
 * @author Marc Dojtschinov
 */

public class BaseMapActivity extends ActionBarActivity {
	private GoogleMap map;
	private MyLocation mLocation;
	private boolean locationReady;

	

  @Override
  	protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
	}
	

@Override
  public boolean onCreateOptionsMenu(Menu menu) {
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

public void initilizeMap() {
      
    }

public void setUpMap(){
	
}

public void locationReady(boolean b){
	
}

public void notifyDataSetChanged() {
	// TODO Auto-generated method stub
	
}

}
