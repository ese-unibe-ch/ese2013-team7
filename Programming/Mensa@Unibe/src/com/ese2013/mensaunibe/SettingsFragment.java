package com.ese2013.mensaunibe;



import android.os.Bundle;
import android.support.v4.app.ListFragment;

/**
 * @author group7
 * @author Andreas Hohler
 */

public class SettingsFragment extends ListFragment{
	private static final String TAG= "SettingsFragment";
	
	@Override
	public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		NotificationSettingsAdapter adapter = new NotificationSettingsAdapter(getActivity(), R.layout.notification_list_layout);
		setListAdapter(adapter);
		/*RatingListAdapter adapter = new RatingListAdapter(getActivity(), R.layout.rating_list_row_layout);
		setListAdapter(adapter);
		Model.getInstance().loadMenuRating(getActivity(), adapter, menu, mMensaId, RatingData.TYPE_LOAD);*/
	}
}