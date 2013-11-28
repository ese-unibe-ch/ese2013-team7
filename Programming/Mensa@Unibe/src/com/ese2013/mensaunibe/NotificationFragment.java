package com.ese2013.mensaunibe;



import java.util.ArrayList;

import com.ese2013.mensaunibe.model.Model;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

/**
 * @author group7
 * @author Andreas Hohler
 */

public class NotificationFragment extends Fragment{
	private static final String TAG= "SettingsFragment";
	private View view;
	private NotificationSettingsAdapter adapter;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.activity_settings, container, false);	

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
		ac.setOnKeyListener( new OnKeyEnter(this.getActivity(), adapter) );
		ac.setAdapter( new NotificationAutoCompleteAdapter(getActivity(), android.R.layout.simple_dropdown_item_1line) );
		
		ImageButton add = (ImageButton) view.findViewById(R.id.settings_add_button);
		add.setOnClickListener( new OnNotificationAddClicker(this.getActivity(), adapter, ac));
	
	}
	@Override
	public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        
    }
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}
	
	private class OnNotificationAddClicker implements OnClickListener {
		Context context;
		NotificationSettingsAdapter adapter;
		AutoCompleteTextView input;
		public OnNotificationAddClicker(Context context, NotificationSettingsAdapter adapter, AutoCompleteTextView input) {
			this.context = context;
			this.adapter = adapter;
			this.input = input;
		}
		public void onClick(View view) {
			if( adapter.add( input.getText().toString() ) ) {
				Toast.makeText(context, getString(R.string.notification_keyword_added), Toast.LENGTH_LONG).show();
			} else {
				Toast.makeText(context, getString(R.string.notification_keyword_exist), Toast.LENGTH_LONG).show();
			}
			adapter.notifyDataSetChanged();
		}
	}
	
	private class OnKeyEnter implements OnKeyListener {
		Context context;
		NotificationSettingsAdapter adapter;
		public OnKeyEnter(Context context, NotificationSettingsAdapter adapter) {
			this.context = context;
			this.adapter = adapter;
		}
		@Override
		public boolean onKey(View v, int keyCode, KeyEvent event) {
			if ((event.getAction() == KeyEvent.ACTION_DOWN)) {
				if(keyCode == KeyEvent.KEYCODE_ENTER) {
					if( adapter.add( ((AutoCompleteTextView) v).getText().toString() ) ) {
						Toast.makeText(context, getString(R.string.notification_keyword_added), Toast.LENGTH_LONG).show();
					} else {
						Toast.makeText(context, getString(R.string.notification_keyword_exist), Toast.LENGTH_LONG).show();
					}
					adapter.notifyDataSetChanged();
				}
			}
			return false;
		}
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
		boolean notifyStatus = sw.isChecked();
		Model.getInstance().saveNotificationSettings(notifyStatus, adapter.getKeywords());
	}
}