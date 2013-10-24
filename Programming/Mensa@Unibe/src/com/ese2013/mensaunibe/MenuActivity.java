package com.ese2013.mensaunibe;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBarActivity;

import com.ese2013.mensaunibe.model.Model;

public class MenuActivity extends ActionBarActivity 
{
	private static final String TAG_MENULIST_FRAGMENT ="MenuListFragment_tag";
	private int mMensaId;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu_list);
		mMensaId = getIntent().getIntExtra("int_value", 0);
		setTitle( Model.getInstance().getMensaById(mMensaId).getName() );
		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);

		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		ActionBar.Tab CurrentDayTab = actionBar.newTab().setText(getString(R.string.today));
		ActionBar.Tab ComingDaysTab = actionBar.newTab().setText(getString(R.string.comingDaysTabTitle));

		Fragment CurrentDayFrag= new CurrentDayMenuFragment();
		Fragment ComingDaysFrag = new ComingDaysMenuFragment();
		updateFragmetsMensaId(mMensaId);
		
		CurrentDayTab.setTabListener(new MenuTabsListener(CurrentDayFrag));
		ComingDaysTab.setTabListener(new MenuTabsListener(ComingDaysFrag));

		actionBar.addTab(CurrentDayTab);
		actionBar.addTab(ComingDaysTab);


		/*MenuTabHostFragment fragment = (MenuTabHostFragment)getSupportFragmentManager()
        								.findFragmentByTag(TAG_MENULIST_FRAGMENT);
        if (fragment == null) {
            fragment = new MenuTabHostFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.add(android.R.id.content, fragment, TAG_MENULIST_FRAGMENT);
            ft.commit();
        }

        fragment.update(mMensaId);*/

	}

	private void updateFragmetsMensaId(int mensaId) {
		mMensaId=mensaId;
		CurrentDayMenuFragment.updateMensaId(mMensaId);
		ComingDaysMenuFragment.updateMensaId(mMensaId);
	}

	public class MenuTabsListener  implements ActionBar.TabListener {
		public Fragment fragment;

		public MenuTabsListener(Fragment fragment) {
			this.fragment = fragment;
		}

		@Override
		public void onTabReselected(Tab tab, FragmentTransaction ft) {

		}

		@Override
		public void onTabSelected(Tab tab, FragmentTransaction ft) {
			ft.replace(R.id.menu_fragment_container, fragment);
		}

		@Override
		public void onTabUnselected(Tab tab, FragmentTransaction ft) {
			ft.remove(fragment);
		}

	}
}