package com.ese2013.mensaunibe;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.widget.ListAdapter;

public class ComingDaysMenuFragment extends ListFragment {
	private static int mMensaId;

	@Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        
		ListAdapter adapter = new ComingDaysListAdapter(getActivity(), R.layout.menu_list_item, mMensaId);
        setListAdapter(adapter);
        

    }

	public static void updateMensaId(int mensaId) {
		mMensaId = mensaId;
		
	}
    
}
