package com.ese2013.mensaunibe.notification;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;

import com.ese2013.mensaunibe.R;
import com.ese2013.mensaunibe.map.MyLocation;
import com.ese2013.mensaunibe.mensa.MensaListFragment;
import com.ese2013.mensaunibe.menu.MenuActivity;
import com.ese2013.mensaunibe.model.utils.AppUtils;
import com.ese2013.mensaunibe.notification.NotificationResultFragment.DataPullingInterface;

public class NotificationResultActivity extends ActionBarActivity implements DataPullingInterface {
	private NotificationResultFragment fragment;
	private ArrayList<NotificationHolder> keywordResultList;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_notification_result);
		
		fragment = (NotificationResultFragment) getSupportFragmentManager().findFragmentByTag(AppUtils.TAG_NOTIFICATION_RESULT_FRAGMENT);
		if (fragment == null) {
			fragment = new NotificationResultFragment();
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

	public void onListItemSelected(int mensaId) {
		Intent intent = new Intent();
		intent.setClass(getApplicationContext(), MenuActivity.class);
		intent.putExtra("int_value", mensaId);
		startActivity(intent);		              
	}
	
	public ArrayList<NotificationHolder> getKeywordList() {
        return keywordResultList;
    }
}
