package com.ese2013.mensaunibe.mensa;

import com.ese2013.mensaunibe.R;
import com.ese2013.mensaunibe.map.MapActivityAllMensas;
import com.ese2013.mensaunibe.map.MyLocation;
import com.ese2013.mensaunibe.mensa.MensaListAdapter;
import com.ese2013.mensaunibe.menu.MenuActivity;
import com.ese2013.mensaunibe.model.data.ForceReloadTask;
import com.ese2013.mensaunibe.model.utils.AppUtils;
import com.ese2013.mensaunibe.notification.NotificationActivity;
import com.ese2013.mensaunibe.notificationservice.MensaService;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

/**
 * @author group7
 * @author Andreas Hohler
 */

public class MensaActivity extends ActionBarActivity implements MensaListFragment.OnListItemClickListener{

	private MyLocation mLocation;
	private MensaListFragment fragment;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_mensa_list);
		
		if(mLocation == null){
			mLocation = MyLocation.getInstance();
			mLocation.setActivity(this);
		}
		
		fragment = (MensaListFragment) getSupportFragmentManager().findFragmentByTag(AppUtils.TAG_MENSALIST_FRAGMENT);
		if (fragment == null) {
			fragment = new MensaListFragment();
			FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
			ft.add(android.R.id.content, fragment, AppUtils.TAG_MENSALIST_FRAGMENT);
			ft.commit();
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.actionbar_mensa_list, menu);
		return super.onCreateOptionsMenu(menu);

	}

	@Override
	public void onListItemSelected(int mensaId) {
		Intent intent = new Intent();
		//intent.setClassName(getPackageName(), getPackageName()+".MenuActivity");
		intent.setClass(getApplicationContext(), MenuActivity.class);
		intent.putExtra("int_value", mensaId);
		startActivity(intent);		              
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle presses on the action bar items
		switch (item.getItemId()) {
		case R.id.action_refresh:
			Log.v(AppUtils.TAG_MENSALIST_FRAGMENT, "Refresh data...");
			ForceReloadTask task = new ForceReloadTask(this, (MensaListAdapter) fragment.getListAdapter());
			task.execute();
			return true;
		case R.id.action_map:
			startActivity(new Intent(getApplicationContext(), MapActivityAllMensas.class));
			return true;
		case R.id.action_notification:
			startActivity(new Intent(getApplicationContext(), NotificationActivity.class));
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	public void onStop() {
		mLocation.callOnStop();
		Intent intent =new Intent(this, MensaService.class);
		   startService(intent);
		super.onStop();
	}

	@Override
	public void onPause() {
		mLocation.callOnPause();
		
		Intent intent =new Intent(this, MensaService.class);
		   startService(intent);
		   
		super.onPause();
	}

	@Override
	public void onStart() {
		super.onStart();
		mLocation.callOnStart();
	}

	@Override
	public void onResume() {
		super.onResume();
		mLocation.callOnResume();
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
		mLocation.callOnActivityResult(requestCode, resultCode, intent);
	}
}