package com.ese2013.mensaunibe;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ListView;

/**
 * @author group7
 * @author Sandor Torok
 */

public class MensaListFragment extends ListFragment{
	private static final String TAG = "MensaListFragment";
	private OnListItemClickListener listener;

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		MensaListAdapter adapter = new MensaListAdapter(getActivity(), R.layout.mensa_list_row_layout);
		setListAdapter(adapter);
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		MensaListAdapter a = (MensaListAdapter) l.getAdapter();
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