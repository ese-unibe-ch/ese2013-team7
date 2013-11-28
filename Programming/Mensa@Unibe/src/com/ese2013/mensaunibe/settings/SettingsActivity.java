package com.ese2013.mensaunibe.settings;

import com.ese2013.mensaunibe.R;
import com.ese2013.mensaunibe.R.string;

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

public class SettingsActivity extends ActionBarActivity {
	private SettingsFragment fragment;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setTitle( getString(R.string.settings) );
		
		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		
		fragment = new SettingsFragment();
		FragmentTransaction ft = getFragmentManager().beginTransaction();

		ft.add(android.R.id.content, fragment);
		ft.commit();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.rating, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
	}
}
