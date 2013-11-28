package com.ese2013.mensaunibe.notification;

import java.util.HashSet;

import android.content.Context;
import android.widget.ArrayAdapter;


/**
 * @author group17
 * @author Andreas Hohler
 */

public class NotificationAutoCompleteAdapter extends ArrayAdapter<String> {
	private Context context;
	public NotificationAutoCompleteAdapter(Context context, int resource) {
		super(context, resource);
		this.context = context;
		populate();
	}
	private void populate() {
		LoadNotificationSettingsTask t = new LoadNotificationSettingsTask(context, this);
		t.execute();
	}
}