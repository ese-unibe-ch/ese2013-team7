package com.ese2013.mensaunibe.menu;

import com.ese2013.mensaunibe.R;
import com.ese2013.mensaunibe.model.utils.AppUtils;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.ListAdapter;

/**
 * @author group7
 * @author Sandor Torok
 * @see MenuActivity
 */

public class CurrentDayMenuFragment extends ListFragment{

	private static final String TAG = "CurrentDayMenuFragment";
	private static int mMensaId;

	@Override
	public void onCreate(Bundle savedInstanceState){
         super.onCreate(savedInstanceState);
         Bundle arguments = getArguments(); 
         if (arguments == null)
             Log.e(TAG, "Arguments is NULL");
         else
             mMensaId = getArguments().getInt(AppUtils.MENSA_ID);
     }
	 
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		ListAdapter adapter = new MenuListAdapter(getActivity(), 
				R.layout.menu_list_item, mMensaId, 
				AppUtils.FIRST_MENU);
		setListAdapter(adapter);
		setHasOptionsMenu(true);
	}
	
	@Override
	public void onStart() {
	    super.onStart();
	    setEmptyText(getString(R.string.no_menu));
	}
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);
		inflater.inflate(R.menu.current_day_actionbar, menu);
	}
}
