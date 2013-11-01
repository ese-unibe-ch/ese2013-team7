package com.ese2013.mensaunibe;

import com.ese2013.mensaunibe.model.Model;
import com.ese2013.mensaunibe.model.api.AppUtils;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.Menu;

public class RatingActivity extends ActionBarActivity {
	private int mMensaId;
	private String mMenu;
	private RatingListFragment fragment;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rating);

		mMensaId = getIntent().getIntExtra(AppUtils.MENSA_ID, 0);
		mMenu = getIntent().getStringExtra("menu");
		
		setTitle( getString(R.string.menurating_title) + " " + Model.getInstance().getMensaById(mMensaId).getName() );
		
		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		
		fragment = (RatingListFragment) getSupportFragmentManager().findFragmentByTag(AppUtils.TAG_RATINGLIST_FRAGMENT);
		if (fragment == null) {
			fragment = new RatingListFragment();
			Bundle args = new Bundle();
			args.putInt(AppUtils.MENSA_ID, mMensaId);
			args.putString("menu", mMenu);
			fragment.setArguments(args);
			
			FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
			ft.add(android.R.id.content, fragment, AppUtils.TAG_RATINGLIST_FRAGMENT);
			ft.commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.rating, menu);
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
	}
}