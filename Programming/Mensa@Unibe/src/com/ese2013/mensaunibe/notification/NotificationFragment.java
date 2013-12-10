package com.ese2013.mensaunibe.notification;


import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Switch;

import com.ese2013.mensaunibe.R;
import com.ese2013.mensaunibe.model.Model;
import com.ese2013.mensaunibe.notificationservice.MensaService;

/**
 * @author group7
 * @author Andreas Hohler
 */

public class NotificationFragment extends Fragment{
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
		//List with keywords
		ArrayList<String> keywords = Model.getInstance().loadNotificationKeywords();
		adapter = new NotificationSettingsAdapter(getActivity(), R.layout.notification_list_layout, keywords);

        ListView listView = (ListView) view.findViewById(R.id.listViewNotificationKeywords);
        listView.setAdapter(adapter);
        
        //Auto complete text field for keywords
        AutoCompleteTextView ac = (AutoCompleteTextView) view.findViewById(R.id.settings_add_keyword);
		ac.setOnKeyListener( new NotificationOnKeyEnter(this.getActivity(), adapter) );
		ac.setAdapter( new NotificationAutoCompleteAdapter(getActivity(), android.R.layout.simple_dropdown_item_1line) );
		
		//Add-Button for keywords
		ImageButton add = (ImageButton) view.findViewById(R.id.settings_add_button);
		add.setOnClickListener( new OnNotificationAddClicker(this.getActivity(), adapter, ac));
		
		//Test notification button
		Button test = (Button) view.findViewById(R.id.testNotification);
		test.setOnClickListener( new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				save();
				getActivity().startService(new Intent(getActivity(), MensaService.class));
			}
		});
	}
	@Override
	public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
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