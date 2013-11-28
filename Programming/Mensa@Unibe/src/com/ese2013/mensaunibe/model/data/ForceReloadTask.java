package com.ese2013.mensaunibe.model.data;

import com.ese2013.mensaunibe.mensa.MensaListAdapter;
import com.ese2013.mensaunibe.menu.MenuActivity.TabCollectionPagerAdapter;
import com.ese2013.mensaunibe.model.Model;

import android.app.ProgressDialog;
//import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.widget.Toast;

/**
 * @author group7
 * @author Andreas Hohler
 */

public class ForceReloadTask {

	private ProgressDialog dialog;
	private ActionBarActivity context;
	private MensaListAdapter listAdapter;
	private TabCollectionPagerAdapter listAdapter2;
	
	/**
	 * constructor, creates the waiting dialog progress bar
	 * for MensaListAdapter
	 * @param context
	 * @param listAdapter
	 */
	public ForceReloadTask(ActionBarActivity context, MensaListAdapter listAdapter) {
		assert context != null && listAdapter != null;
		this.dialog = new ProgressDialog(context);
		this.listAdapter = listAdapter;
		this.context = context;
	}
	
	/**
	 * constructor, creates the waiting dialog progress bar
	 * for TabCollectionPagerAdapter (Menu lists)
	 * @param context
	 * @param listAdapter
	 */
	public ForceReloadTask(ActionBarActivity context, TabCollectionPagerAdapter listAdapter) {
		assert context != null && listAdapter != null;
		this.dialog = new ProgressDialog(context);
		this.listAdapter2 = listAdapter;
		this.context = context;
	}
	
	/**
	 * pre execute: shows the progress bar
	 */
	protected void onPreExecute() {
        this.dialog.setMessage("Refresh data...");
        this.dialog.show();
    }
	
	/**
	 * post execute: hides the progress bar, handles the result
	 * @param success (true if reload worked, other else false)
	 */
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
	
	/**
	 * executes the task
	 */
	public void execute() {
		onPreExecute();
		boolean result = doInBackground();
		onPostExecute(result);
	}
	
	protected Boolean doInBackground(Void... params) {
		if( Model.getInstance().forceReload() ) {
			return true;
		}
		return false;
	}
}
