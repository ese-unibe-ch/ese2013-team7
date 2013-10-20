package com.ese2013.mensaunibe;

import android.content.Intent;
//import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
/*import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;*/
import android.widget.ListView;

public class MensaListFragment extends ListFragment{
 
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	    return super.onCreateView(inflater, container, savedInstanceState);  
	}
	
	@Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        
		MensaListAdapter adapter = new MensaListAdapter(getActivity(), android.R.layout.simple_list_item_1);
        setListAdapter(adapter);
    }
    
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
		Intent intent = new Intent();
		intent.setClassName(getActivity().getPackageName(), getActivity().getPackageName()+".MenuList");
		MensaListAdapter a = (MensaListAdapter) l.getAdapter();
		if(a.getItem(position) != null) {
			intent.putExtra("int_value", a.getItem(position).getId() );
			startActivity(intent);
		}
    }
}
