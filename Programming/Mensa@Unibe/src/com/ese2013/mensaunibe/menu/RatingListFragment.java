package com.ese2013.mensaunibe.menu;

import com.ese2013.mensaunibe.R;
import com.ese2013.mensaunibe.model.Model;
import com.ese2013.mensaunibe.model.menu.RatingData;
import com.ese2013.mensaunibe.model.utils.AppUtils;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

/**
 * @author group7
 * @author Andreas Hohler
 */

public class RatingListFragment extends Fragment{
	private static final String TAG = RatingListFragment.class.getName();

	private String menu;
	private int mMensaId;
	private View view;
	private RatingListAdapter adapter;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.activity_rating, container, false);
		updateAdapter();
		return view;
	}
	
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
	}
	
	private void updateAdapter() {
		adapter = new RatingListAdapter(getActivity(), R.layout.rating_list_row_layout);
		adapter.setBaseView(view);
		ListView listView = (ListView) view.findViewById(R.id.ratingListView);
        listView.setAdapter(adapter);
        
		Model.getInstance().loadMenuRating(getActivity(), adapter, menu, mMensaId, RatingData.TYPE_LOAD);
	}
	/**
	 * public method to repopulate the rating list
	 */
	public void update() {
		Model.getInstance().loadMenuRating(getActivity(), adapter, menu, mMensaId, RatingData.TYPE_LOAD);
	}
}