package com.ese2013.mensaunibe;

import com.ese2013.mensaunibe.model.Model;
import com.ese2013.mensaunibe.model.api.AppUtils;
import com.ese2013.mensaunibe.model.menu.RatingData;

import android.app.Activity;
//import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
/*import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;*/
import android.widget.ListView;

public class RatingListFragment extends ListFragment{
	private static final String TAG= "RatingListFragment";

	private String menu;
	private int mMensaId;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments(); 
        if (arguments == null)
            Log.e(TAG, "Arguments is NULL");
        else
            mMensaId = getArguments().getInt(AppUtils.MENSA_ID);
        	menu = getArguments().getString("menu");
    }
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		//MensaListAdapter adapter = new MensaListAdapter(getActivity(), android.R.layout.simple_list_item_1);
		RatingListAdapter adapter = new RatingListAdapter(getActivity(), R.layout.rating_list_row_layout, this.mMensaId, this.menu);
		setListAdapter(adapter);
		//Model.getInstance().loadMenuRating(getActivity(), adapter, menu, RatingData.TYPE_LOAD);
		
		//have to check android.app.LoaderManager
		//i think we could use it
		//setListShown(false);
	}

	/*@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		RatingListAdapter a = (RatingListAdapter) l.getAdapter();
		if(a.getItem(position) != null) {
			listener.onListItemSelected(a.getItem(position).getId());			
		}
	}*/

	/*@Override
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
	}*/
}