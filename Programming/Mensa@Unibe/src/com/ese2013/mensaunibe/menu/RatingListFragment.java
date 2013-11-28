package com.ese2013.mensaunibe.menu;

import com.ese2013.mensaunibe.R;
import com.ese2013.mensaunibe.R.layout;
import com.ese2013.mensaunibe.model.Model;
import com.ese2013.mensaunibe.model.menu.RatingData;
import com.ese2013.mensaunibe.model.utils.AppUtils;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;

/**
 * @author group7
 * @author Andreas Hohler
 */

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
		
		RatingListAdapter adapter = new RatingListAdapter(getActivity(), R.layout.rating_list_row_layout);
		setListAdapter(adapter);
		Model.getInstance().loadMenuRating(getActivity(), adapter, menu, mMensaId, RatingData.TYPE_LOAD);
	}
	
	/**
	 * public method to repopulate the rating list
	 */
	public void update() {
		RatingListAdapter a = (RatingListAdapter) getListAdapter();
		Model.getInstance().loadMenuRating(getActivity(), a, menu, mMensaId, RatingData.TYPE_LOAD);
	}
}