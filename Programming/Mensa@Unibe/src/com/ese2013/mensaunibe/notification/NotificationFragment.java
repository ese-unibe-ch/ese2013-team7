package com.ese2013.mensaunibe.notification;



import java.util.ArrayList;

import com.ese2013.mensaunibe.R;
import com.ese2013.mensaunibe.model.Model;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Switch;

/**
 * @author group7
 * @author Andreas Hohler
 */

public class NotificationFragment extends Fragment{
	private static final String TAG = NotificationFragment.class.getName();
	private View view;
	private NotificationSettingsAdapter adapter;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.activity_notifications, container, false);	

		Switch sw = (Switch) view.findViewById(R.id.tgl_notifications);
		sw.setChecked( Model.getInstance().loadNotificationStatus() );
		
		updateAdapter();
		return view;
	}
	
	private void updateAdapter() {
		ArrayList<String> keywords = Model.getInstance().loadNotificationKeywords();
		adapter = new NotificationSettingsAdapter(getActivity(), R.layout.notification_list_layout, keywords);
		
        ListView listView = (ListView) view.findViewById(R.id.listViewNotificationKeywords);
        listView.setAdapter(adapter);
        
        AutoCompleteTextView ac = (AutoCompleteTextView) view.findViewById(R.id.settings_add_keyword);
		ac.setOnKeyListener( new NotificationOnKeyEnter(this.getActivity(), adapter) );
		ac.setAdapter( new NotificationAutoCompleteAdapter(getActivity(), android.R.layout.simple_dropdown_item_1line) );
		
		ImageButton add = (ImageButton) view.findViewById(R.id.settings_add_button);
		add.setOnClickListener( new OnNotificationAddClicker(this.getActivity(), adapter, ac));
	
	}
	@Override
	public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Log.d(TAG, "NotificationFragment created");
    }
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}
	
	@Override
	public void onPause() {
		super.onPause();
		save();
	}
	
	@Override
	public void onStop() {
		super.onStop();
		save();
	}
	
	private void save() {
		Switch sw = (Switch) view.findViewById(R.id.tgl_notifications);
		assert sw != null;
		boolean notifyStatus = sw.isChecked();
		Model.getInstance().saveNotificationSettings(notifyStatus, adapter.getKeywords());
	}
}