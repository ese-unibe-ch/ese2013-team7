package com.ese2013.mensaunibe;

import java.util.Calendar;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
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
import android.os.AsyncTask;

import com.ese2013.mensaunibe.model.Model;
import com.ese2013.mensaunibe.model.api.AppUtils;
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

		public TabCollectionPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public int getCount() {
			return FRAGMENT_COUNT;
		}

		@Override
		public Fragment getItem(int position) {
			Fragment fragment = null;
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
	}

	@Override
	public void updateTiteListener(String tabTitle, int firstMenu) {
		Tab tab = getSupportActionBar().getTabAt(firstMenu);
		tab.setText("");
		tab.setText(tabTitle);
	}
	
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		Language lang = Model.getInstance().getLanguage();
		//sorry, had to comment out. I couldn't find out why item is always null for me in ComingDaysMenuFragment
		MenuItem item = menu.findItem(R.id.action_translate);
		if(lang != null && lang.compareTo(Language.GERMAN) != 0) {
			item.setTitle(getString(R.string.action_translate_to_german));
		} else {
			item.setTitle(getString(R.string.action_translate_to_english));
		}
		return true;
	}

	
	private class LanguageChanger extends AsyncTask<Void, Void, Boolean> {
		private ProgressDialog dialog;
		private Context context;
		
		public LanguageChanger(Context context) {
			this.dialog = new ProgressDialog(context);
			this.context = context;
		}
		
		protected Boolean doInBackground(Void... params) {
			return Model.getInstance().changeLanguage();
		}
		
		protected void onPreExecute() {
	        this.dialog.setMessage("Translating menu data...");
	        this.dialog.show();
	    }
		
		protected void onPostExecute(final Boolean success) {
			if (dialog.isShowing()) {
				dialog.dismiss();
			}
			if(success) {
				Toast.makeText(context, "Menus have been translated", Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(context, "Menus could not have been translated", Toast.LENGTH_SHORT).show();
			}
			if( success ) {
	    		//Log.v("MenuActivity", "finished translating data...");
	    		finish();
	    		startActivity(getIntent());
	    	}
		}
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()) {
			case R.id.action_translate:

				//LanguageChanger lc = new LanguageChanger(this);
				//lc.execute();
				//AsyncTask<Void, Void, Boolean> task = new LanguageChanger(this);
				//task.execute();
				boolean result = Model.getInstance().changeLanguage();
				if(result) {
					Toast.makeText(this, "Menus have been translated", Toast.LENGTH_SHORT).show();
					finish();
					startActivity(getIntent());
				} else {
					Toast.makeText(this, "Menus could not have been translated", Toast.LENGTH_SHORT).show();
				}
	    		return true;
	        default:
	            return super.onOptionsItemSelected(item);
		}
	}
}