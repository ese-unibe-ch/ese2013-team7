package com.ese2013.mensaunibe;

import java.util.ArrayList;

import android.view.View;
import android.view.View.OnClickListener;

/**
 * @author group17
 * @author Andreas Hohler
 */

public class NotificationEntryListener implements OnClickListener {
	String item;
	NotificationSettingsAdapter adapter;
	public NotificationEntryListener(String item, NotificationSettingsAdapter adapter) {
		assert item != null && adapter != null;
		this.item = item;
		this.adapter = adapter;
	}
	@Override
	public void onClick(View arg0) {
		adapter.remove(item);
		adapter.notifyDataSetChanged();
	}
}
