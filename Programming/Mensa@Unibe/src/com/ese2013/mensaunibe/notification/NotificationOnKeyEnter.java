package com.ese2013.mensaunibe.notification;

import android.content.Context;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.ese2013.mensaunibe.R;

public class NotificationOnKeyEnter implements OnKeyListener {
	Context context;
	NotificationSettingsAdapter adapter;
	public NotificationOnKeyEnter(Context context, NotificationSettingsAdapter adapter) {
		assert context != null && adapter != null;
		this.context = context;
		this.adapter = adapter;
	}
	@Override
	public boolean onKey(View v, int keyCode, KeyEvent event) {
		if ((event.getAction() == KeyEvent.ACTION_DOWN)) {
			if(keyCode == KeyEvent.KEYCODE_ENTER) {
				if( adapter.add( ((AutoCompleteTextView) v).getText().toString() ) ) {
					Toast.makeText(context, context.getString(R.string.notification_keyword_added), Toast.LENGTH_LONG).show();
				} else {
					Toast.makeText(context, context.getString(R.string.notification_keyword_exist), Toast.LENGTH_LONG).show();
				}
				((AutoCompleteTextView) v).setText("");
				adapter.notifyDataSetChanged();
			}
		}
		return false;
	}
}