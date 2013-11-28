package com.ese2013.mensaunibe.notification;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.ese2013.mensaunibe.R;

public class OnNotificationAddClicker implements OnClickListener {
	Context context;
	NotificationSettingsAdapter adapter;
	AutoCompleteTextView input;
	public OnNotificationAddClicker(Context context, NotificationSettingsAdapter adapter, AutoCompleteTextView input) {
		assert context != null && adapter != null && input != null;
		this.context = context;
		this.adapter = adapter;
		this.input = input;
	}
	public void onClick(View view) {
		if( adapter.add( input.getText().toString() ) ) {
			Toast.makeText(context, context.getString(R.string.notification_keyword_added), Toast.LENGTH_LONG).show();
		} else {
			Toast.makeText(context, context.getString(R.string.notification_keyword_exist), Toast.LENGTH_LONG).show();
		}
		input.setText("");
		adapter.notifyDataSetChanged();
	}
}