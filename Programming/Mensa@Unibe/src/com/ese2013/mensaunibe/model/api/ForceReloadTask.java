package com.ese2013.mensaunibe.model.api;

import com.ese2013.mensaunibe.MensaListAdapter;
import com.ese2013.mensaunibe.MenuActivity.TabCollectionPagerAdapter;
import com.ese2013.mensaunibe.model.Model;

import android.app.ProgressDialog;
//import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.widget.Toast;

public class ForceReloadTask /*extends AsyncTask<Void, Void, Boolean>*/{

	private ProgressDialog dialog;
	private ActionBarActivity context;
	private MensaListAdapter listAdapter;
	private TabCollectionPagerAdapter listAdapter2;
	
	public ForceReloadTask(ActionBarActivity context, MensaListAdapter listAdapter) {
		this.dialog = new ProgressDialog(context);
		this.listAdapter = listAdapter;
		this.context = context;
	}
	
	public ForceReloadTask(ActionBarActivity context, TabCollectionPagerAdapter listAdapter) {
		this.dialog = new ProgressDialog(context);
		this.listAdapter2 = listAdapter;
		this.context = context;
	}
	
	protected void onPreExecute() {
        this.dialog.setMessage("Refresh data...");
        this.dialog.show();
    }
	
	protected void onPostExecute(final Boolean success) {
		if (dialog.isShowing()) {
			dialog.dismiss();
		}
		
		if(success) {
			Toast.makeText(context,  "Data has been refreshed", Toast.LENGTH_SHORT).show();
			if(listAdapter != null) {
				listAdapter.notifyDataSetChanged();
			} else {
				listAdapter2.notifyDataSetChanged();
			}
		} else {
			Toast.makeText(context,  "Data could not be refreshed", Toast.LENGTH_SHORT).show();
		}
	}
	
	public void execute() {
		onPreExecute();
		boolean result = doInBackground();
		onPostExecute(result);
	}
	
	//private boolean result = false;
	protected Boolean doInBackground(Void... params) {
		if( Model.getInstance().forceReload() ) {
			return true;
		}
		return false;
		
		/*context.runOnUiThread(new Runnable() {
		    @Override
		    public void run() {
		    	result = Model.getInstance().forceReload();
		    	Log.e("ForceReloadTask", "inner boolean: "+result);
		    }
		});
		Log.e("ForceReloadTask", "boolean: "+result);
		return result;*/
	}
}
