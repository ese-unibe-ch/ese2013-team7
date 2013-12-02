package com.ese2013.mensaunibe.notification;

import com.ese2013.mensaunibe.R;

import android.content.Intent;
import android.os.Bundle;
import android.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.Menu;

/**
 * @author group7
 * @author Andreas Hohler
 */

public class NotificationActivity extends ActionBarActivity {
	private NotificationFragment fragment;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setTitle( getString(R.string.notifications) );
		
		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		
		fragment = new NotificationFragment();
		FragmentTransaction ft = getFragmentManager().beginTransaction();

		ft.add(android.R.id.content, fragment);
		ft.commit();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()) {
		case android.R.id.home:
			onBackPressed();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
		//return super.onOptionsItemSelected(item);
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
	}
}
