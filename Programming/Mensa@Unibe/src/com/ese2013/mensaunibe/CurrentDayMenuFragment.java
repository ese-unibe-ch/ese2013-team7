package com.ese2013.mensaunibe;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.ListAdapter;

public class CurrentDayMenuFragment extends ListFragment {

	public static final String MENSA_ID = "MENSA_ID";
	private static final String TAG = "CurrentDayMenuFragment";
	private static int mMensaId;

	@Override
	public void onCreate(Bundle savedInstanceState){
         super.onCreate(savedInstanceState);
         Bundle arguments = getArguments(); 
         if (arguments == null)
             Log.e(TAG, "Arguments is NULL");
         else
             mMensaId = getArguments().getInt(MENSA_ID);
     }
	 
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		ListAdapter adapter = new CurrentDayListAdapter(getActivity(), R.layout.menu_list_item, mMensaId);
		setListAdapter(adapter);
		setHasOptionsMenu(true);
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);
		inflater.inflate(R.menu.actionbar_menu_list, menu);
	}

}
