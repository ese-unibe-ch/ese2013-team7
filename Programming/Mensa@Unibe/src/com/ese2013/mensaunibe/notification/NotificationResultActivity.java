package com.ese2013.mensaunibe.notification;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.ese2013.mensaunibe.R;

import com.ese2013.mensaunibe.model.utils.AppUtils;
import com.ese2013.mensaunibe.notification.NotificationResultFragment.DataPullingInterface;

/**
 * @author group7
 * @author Marc Dojtschinov
 */

public class NotificationResultActivity extends ActionBarActivity implements DataPullingInterface {
	private NotificationResultFragment fragment;
	private ArrayList<NotificationHolder> keywordResultList;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.fragment_notification_result);
		setTitle( getString(R.string.notification_result) );
		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		
		keywordResultList = getIntent().getParcelableArrayListExtra("keywordResultList");
		
		fragment = (NotificationResultFragment) getSupportFragmentManager().findFragmentByTag(AppUtils.TAG_NOTIFICATION_RESULT_FRAGMENT);
		if (fragment == null) {
			fragment = new NotificationResultFragment();
			FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
			ft.add(android.R.id.content, fragment, AppUtils.TAG_NOTIFICATION_RESULT_FRAGMENT);
			ft.commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return super.onCreateOptionsMenu(menu);
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
	
	@Override
	public ArrayList<NotificationHolder> getKeywordList() {
        return keywordResultList;
    }
}
