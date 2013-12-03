package com.ese2013.mensaunibe.notification;


import java.util.ArrayList;

import com.ese2013.mensaunibe.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ListView;



public class NotificationResultFragment extends ListFragment{
	private static final String TAG = "NotificationResultFragment";
	private OnListItemClickListener listener;
	private ArrayList<NotificationHolder> keywordResultList;
	private DataPullingInterface mHostInterface;

	

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		NotificationResultAdapter adapter = new NotificationResultAdapter(getActivity(), R.layout.notifications_result_row, 
			keywordResultList);
		setListAdapter(adapter);
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		NotificationResultAdapter a = (NotificationResultAdapter) l.getAdapter();
		if(a.getItem(position) != null) {
			listener.onListItemSelected(a.getItem(position).getMensaId());			
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
	    try {
	        mHostInterface = (DataPullingInterface) activity;
	    } catch(ClassCastException e) {
	        throw new ClassCastException(activity.toString() + " must implement DataPullingInterface");
	    }
	    ArrayList<NotificationHolder>  keywordResultList = mHostInterface.getKeywordList();           
	}
	

	public interface OnListItemClickListener {
		public void onListItemSelected(int itemId);
	}
	//Used to Pass Result List to Fragment
	public interface DataPullingInterface {
	    public ArrayList<NotificationHolder> getKeywordList();
	}
}