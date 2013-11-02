package com.ese2013.mensaunibe.model.api;

import java.util.ArrayList;
import java.util.Map;
import org.w3c.dom.Document;

import com.ese2013.mensaunibe.MapActivity;
import com.ese2013.mensaunibe.R;
import com.google.android.gms.maps.model.LatLng;

import android.app.Dialog;

import android.os.AsyncTask;
import android.widget.Toast;


	public class GetDirectionsAsyncTask extends AsyncTask<Map<String, String>, Object, ArrayList<LatLng>> {

		public static final String USER_CURRENT_LAT = "user_current_lat";
		public static final String USER_CURRENT_LONG = "user_current_long";
		public static final String DESTINATION_LAT = "destination_lat";
		public static final String DESTINATION_LONG = "destination_long";
		public static final String DIRECTIONS_MODE = "directions_mode";
		private MapActivity activity;

		private Exception exception;

		private Dialog progressDialog;

		public GetDirectionsAsyncTask(MapActivity activity ) 
		{
		    super();
		    assert activity != null;
		    this.activity = activity;
		}

		/*public void onPreExecute() {
		    progressDialog = DialogUtils.createProgressDialog(activity, activity.getString(R.string.get_data_dialog_message));
		    progressDialog.show();
		}
*/
		@Override
		public void onPostExecute(ArrayList<LatLng> result) {
		    if (progressDialog != null) progressDialog.dismiss();

		    if (exception == null) {
		        activity.handleGetDirectionsResult(result);
		    } else {
		        processException();
		    }
		}

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
