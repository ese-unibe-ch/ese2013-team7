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

import com.ese2013.mensaunibe.model.Model;
import com.ese2013.mensaunibe.MenuListAdapter;

public class MenuActivity extends ActionBarActivity implements ActionBar.TabListener, MenuListAdapter.TitleListener{
	private static final String TAG_CURRENT_DAY_MENU_FRAGMENT = "CURRENT_DAY_MENU_FRAGMENT";
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
			Fragment fragment = getSupportFragmentManager().findFragmentByTag(TAG_CURRENT_DAY_MENU_FRAGMENT);
			if (fragment == null) {
				fragment =  new TabCollectionPagerAdapter(getSupportFragmentManager()).getItem(0);
				FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
				ft.add(android.R.id.content, fragment, TAG_CURRENT_DAY_MENU_FRAGMENT);
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
				args.putInt(CurrentDayMenuFragment.MENSA_ID, mMensaId);
				break;
			case 1:
				fragment = new ComingDaysMenuFragment();
				args.putInt(ComingDaysMenuFragment.MENSA_ID, mMensaId);
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
}