package com.ese2013.mensaunibe.notification;


import java.util.ArrayList;

import com.ese2013.mensaunibe.R;
import com.ese2013.mensaunibe.mensa.MensaListFragment.OnListItemClickListener;
import com.ese2013.mensaunibe.menu.MenuActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ListView;



public class NotificationResultFragment extends ListFragment{
	private static final String TAG = "NotificationResultFragment";
	private ArrayList<NotificationHolder> keywordResultList;
	private DataPullingInterface mHostInterface;

	

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		NotificationResultAdapter adapter = new NotificationResultAdapter(getActivity(), R.layout.notification_result_item , 
			keywordResultList);
		setListAdapter(adapter);
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		NotificationResultAdapter a = (NotificationResultAdapter) l.getAdapter();
		Intent intent = new Intent();
		intent.setClass(getActivity().getApplicationContext(), MenuActivity.class );
		intent.putExtra("int_value", a.getItem(position).getMensaId());
		startActivity(intent);	
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
	    try {
	        mHostInterface = (DataPullingInterface) activity;
	    } catch(ClassCastException e) {
	        throw new ClassCastException(activity.toString() + " must implement DataPullingInterface");
	    }
	    ArrayList<NotificationHolder>  keywordResultList = mHostInterface.getKeywordList();           
	}
	

	//Used to pass Result list to Fragment
	public interface DataPullingInterface {
	    public ArrayList<NotificationHolder> getKeywordList();
	}
}