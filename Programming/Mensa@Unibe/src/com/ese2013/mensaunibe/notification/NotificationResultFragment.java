package com.ese2013.mensaunibe.notification;

package com.ese2013.mensaunibe.mensa;

import com.ese2013.mensaunibe.R;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ListView;



public class NotificationResultFragment extends ListFragment{
	private static final String TAG = "NotificationResultFragment";
	private OnListItemClickListener listener;

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		NotificationResultAdapter adapter = new NotificationResultAdapter(getActivity(), R.layout.notifications_result_row, mTag, mBackStackNesting, null);
		setListAdapter(adapter);
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		NotificationResultAdapter a = (NotificationResultAdapter) l.getAdapter();
		if(a.getItem(position) != null) {
			listener.onListItemSelected(a.getItem(position).getId());			
		}
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		if (activity instanceof OnListItemClickListener) {
			listener = (OnListItemClickListener) activity;
		} else {
			throw new ClassCastException(activity.toString()
					+ " must implemenet " +TAG +".OnItemSelectedListener");
		}
	}

	public interface OnListItemClickListener {
		public void onListItemSelected(int itemId);
	}
}