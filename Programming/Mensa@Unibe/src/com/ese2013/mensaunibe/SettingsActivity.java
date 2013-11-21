package com.ese2013.mensaunibe;

import com.ese2013.mensaunibe.model.api.AppUtils;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
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
		setContentView(R.layout.activity_rating);

		setTitle( getString(R.string.settings) );
		
		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		
		fragment = (SettingsFragment) getSupportFragmentManager().findFragmentByTag(AppUtils.TAG_SETTINGS_FRAGMENT);
		if (fragment == null) {
			fragment = new SettingsFragment();
			FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
			ft.add(android.R.id.content, fragment, AppUtils.TAG_RATINGLIST_FRAGMENT);
			ft.commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.rating, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		/*switch(item.getItemId()) {
		case R.id.action_rate:
			Intent intent = new Intent(getApplicationContext(), NewRatingActivity.class);
			intent.putExtra("mensaid", mMensaId);
			intent.putExtra("menu", mMenu);
			startActivityForResult(intent, 1);
			return true;	
		case android.R.id.home:
				onBackPressed();
				return true;
	        default:
	            return super.onOptionsItemSelected(item);
		}*/
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		/*if(resultCode == RESULT_OK) {
			fragment.update();
		}*/
	}
	
}
