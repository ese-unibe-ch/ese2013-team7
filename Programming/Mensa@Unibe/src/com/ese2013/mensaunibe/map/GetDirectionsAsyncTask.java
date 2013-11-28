package com.ese2013.mensaunibe.map;

import java.util.ArrayList;
import java.util.Map;
import org.w3c.dom.Document;

import com.ese2013.mensaunibe.R;
import com.google.android.gms.maps.model.LatLng;

import android.app.Dialog;
import android.app.ProgressDialog;

import android.os.AsyncTask;
import android.widget.Toast;

/**
 * @author group7
 * @author Marc Dojtschinov
 */

public class GetDirectionsAsyncTask extends AsyncTask<Map<String, String>, Object, ArrayList<LatLng>> {
	public static final String USER_CURRENT_LAT = "user_current_lat";
	public static final String USER_CURRENT_LONG = "user_current_long";
	public static final String DESTINATION_LAT = "destination_lat";
	public static final String DESTINATION_LONG = "destination_long";
	public static final String DIRECTIONS_MODE = "directions_mode";
	private BaseMapActivity activity;

	private Exception exception;

	private Dialog progressDialog;

	public GetDirectionsAsyncTask(BaseMapActivity activity ) 
	{
	    super();
	    assert activity != null;
	    this.activity = activity;
	}

	public void onPreExecute() {
		    progressDialog = new ProgressDialog(activity);
		    progressDialog.setTitle(R.string.load_map_message);
		    progressDialog.setCancelable(false);
		    progressDialog.show();
		}

	@Override
	public void onPostExecute(ArrayList<LatLng> result) {
	    if (progressDialog != null) progressDialog.dismiss();

	    if (exception == null) {
	        activity.handleGetDirectionsResult(result);
	    } else {
	        processException();
	    }
	}
	/**
	 * Creates An Array with direction points
	 * for TabCollectionPagerAdapter (Menu lists)
	 * @param Map
	 * @return returns an Array<LatLng> with direction pint coordinates
	 */
	@Override
	protected ArrayList<LatLng> doInBackground(Map<String, String>... params) {

	    Map<String, String> paramMap = params[0];
	    try{
	        LatLng fromPosition = new LatLng(Double.valueOf(paramMap.get(USER_CURRENT_LAT)) , Double.valueOf(paramMap.get(USER_CURRENT_LONG)));
	        LatLng toPosition = new LatLng(Double.valueOf(paramMap.get(DESTINATION_LAT)) , Double.valueOf(paramMap.get(DESTINATION_LONG)));
	        GetMapDirection md = new GetMapDirection();
	        Document doc = md.getDocument(fromPosition, toPosition, paramMap.get(DIRECTIONS_MODE));
	        ArrayList<LatLng> directionPoints = md.getDirection(doc);
	        return directionPoints;

	    }
	    catch (Exception e) {
	        exception = e;
	        return null;
	    }
	}

	private void processException() {
	    Toast.makeText(activity, activity.getString(R.string.error_when_retrieving_data), Toast.LENGTH_SHORT).show();
	}

}
