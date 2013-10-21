package com.ese2013.mensaunibe;

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

public class MensaListFragment extends ListFragment{
	private static final String TAG= "MensaListFragment";
	
	private OnListItemClickListener listener;

	@Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        
		MensaListAdapter adapter = new MensaListAdapter(getActivity(), android.R.layout.simple_list_item_1);
        setListAdapter(adapter);
        //have to check android.app.LoaderManager
        //i think we could use it
        //setListShown(false);
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
        
    // May also be triggered from the Activity
    public void updateDetail(int MensaId) {
    	Log.d(TAG, "updateDetail called with mensaID: " + MensaId);
      listener.onListItemSelected(MensaId);
    }
}
