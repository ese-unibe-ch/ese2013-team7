package com.ese2013.mensaunibe;

import java.util.Calendar;
import java.util.concurrent.ExecutionException;

import org.json.JSONObject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.Toast;
import android.view.MenuItem;
import android.view.Menu;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import com.ese2013.mensaunibe.model.Model;
import com.ese2013.mensaunibe.model.api.ApiUrl;
import com.ese2013.mensaunibe.model.api.AppUtils;
import com.ese2013.mensaunibe.model.api.LanguageChanger;
import com.ese2013.mensaunibe.model.api.URLRequest;
import com.ese2013.mensaunibe.model.mensa.Mensa;
import com.ese2013.mensaunibe.model.menu.DailyMenu;
import com.ese2013.mensaunibe.model.menu.Menuplan;
import com.ese2013.mensaunibe.MenuListAdapter;
import com.memetix.mst.language.Language;

public class MenuActivity extends ActionBarActivity implements ActionBar.TabListener, MenuListAdapter.TitleListener{
	
	private int mMensaId;
	TabCollectionPagerAdapter mTabCollectionPagerAdapter;
	ViewPager mViewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu_list);

		mMensaId = getIntent().getIntExtra("int_value", 0);
		setTitle( Model.getInstance().getMensaById(mMensaId).getName() );
		//setup action bar 
		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		
		// set up tabs on working days, except Friday
		Calendar cal = Calendar.getInstance();
		if(Calendar.FRIDAY != cal.get(Calendar.DAY_OF_WEEK)
				&&Calendar.SATURDAY != cal.get(Calendar.DAY_OF_WEEK)
				&&Calendar.SUNDAY != cal.get(Calendar.DAY_OF_WEEK)) {
			
			actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
			
			// swipe
			mTabCollectionPagerAdapter = new TabCollectionPagerAdapter(getSupportFragmentManager());
			mViewPager = (ViewPager) findViewById(R.id.pager);
			mViewPager.setAdapter(mTabCollectionPagerAdapter);
			mViewPager.setOnPageChangeListener(
					new ViewPager.SimpleOnPageChangeListener() {
						@Override
						public void onPageSelected(int position) {
							// When swiping between pages, select the
							// corresponding tab.
							getSupportActionBar().setSelectedNavigationItem(position);
						}
					});

			// For each of the sections in the app, add a tab to the action bar.
			for (int i = 0; i < mTabCollectionPagerAdapter.getCount(); i++) {
				if(i==0){
					actionBar.addTab(actionBar.newTab().setText(R.string.today)
							.setTabListener(this));
				}else{
					actionBar.addTab(actionBar.newTab().setText(R.string.comingDaysTabTitle)
							.setTabListener(this));
				}
			}
		}else{// if it's Friday, Saturday or Sunday we don't need tabs
			Fragment fragment = getSupportFragmentManager().findFragmentByTag(AppUtils.TAG_CURRENT_DAY_MENU_FRAGMENT);
			if (fragment == null) {
				fragment =  new TabCollectionPagerAdapter(getSupportFragmentManager()).getItem(0);
				FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
				ft.add(android.R.id.content, fragment, AppUtils.TAG_CURRENT_DAY_MENU_FRAGMENT);
				ft.commit();
			}
		}
	}

	@Override
	public void onTabReselected(Tab arg0, FragmentTransaction arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction arg1) {
		mViewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(Tab arg0, FragmentTransaction arg1) {
		// TODO Auto-generated method stub

	}

	public class TabCollectionPagerAdapter extends FragmentPagerAdapter {
		protected static final int FRAGMENT_COUNT = 2;
		private Fragment fragment = null;
		public TabCollectionPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public int getCount() {
			return FRAGMENT_COUNT;
		}

		@Override
		public Fragment getItem(int position) {
			Bundle args = new Bundle();

			switch (position) {
			case 0:
				fragment = new CurrentDayMenuFragment();
				args.putInt(AppUtils.MENSA_ID, mMensaId);
				break;
			case 1:
				fragment = new ComingDaysMenuFragment();
				args.putInt(AppUtils.MENSA_ID, mMensaId);
				break;
			}
			fragment.setArguments(args);
			return fragment;
		}
		
		public Fragment getActualFragment() {
			return fragment;
		}
	}

	@Override
	public void updateTiteListener(String tabTitle, int firstMenu) {
		Tab tab = getSupportActionBar().getTabAt(firstMenu);
		tab.setText("");
		tab.setText(tabTitle);
	}
	
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		Language lang = Model.getInstance().getMensaById(mMensaId).getLanguage();
		//sorry, had to comment out. I couldn't find out why item is always null for me in ComingDaysMenuFragment
		MenuItem item = menu.findItem(R.id.action_translate);
		if(lang != null && lang.compareTo(Language.GERMAN) != 0) {
			item.setTitle(getString(R.string.action_translate_to_german));
		} else {
			item.setTitle(getString(R.string.action_translate_to_english));
		}
		return true;
	}
	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()) {
			case R.id.action_translate:
				ListFragment f = (ListFragment) mTabCollectionPagerAdapter.getActualFragment();
				MenuListAdapter adapter = (MenuListAdapter) f.getListAdapter();
				
				Mensa mensa = Model.getInstance().getMensaById(mMensaId);
				if(mensa.getLanguage() != null && mensa.getLanguage().compareTo(Language.ENGLISH) == 0) {
					Log.v(AppUtils.TAG_MENSALIST_FRAGMENT, "Refresh data...");
					if( Model.getInstance().forceReload() ) {
						Log.v(AppUtils.TAG_MENSALIST_FRAGMENT, "finished refresh data...");
						//finish();
						//startActivity(getIntent());			
						adapter.update();
						adapter.notifyDataSetChanged();
						Toast.makeText(this,  "Data has been refreshed", Toast.LENGTH_SHORT).show();
					} else {
						Toast.makeText(this,  "Data could not be refreshed", Toast.LENGTH_SHORT).show();
					}
				} else {
					LanguageChanger lc = new LanguageChanger(this, mensa);
									
					adapter.update();
					adapter.notifyDataSetChanged();
					
					lc.setAdapter( adapter );
					lc.execute();
				}
	    		return true;
	    		
			case R.id.action_direction:
				startActivity(new Intent(getApplicationContext(), MapActivity.class));
				return true;
	        default:
	            return super.onOptionsItemSelected(item);
		}
	}
}