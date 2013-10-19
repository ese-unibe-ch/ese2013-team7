package com.ese2013.mensaunibe;



//import com.ese2013.mensaunibe.model.Model;
//import com.ese2013.mensaunibe.model.mensa.Mensa;

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
        
        /*List<String> valueList = new ArrayList<String>();
        
		ArrayList<Mensa> mensas = Model.getInstance().getMensaList();
		for(Mensa m : mensas) {
			valueList.add(m.getName());
		}*/
        
        /*ListAdapter adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, valueList)
        {        
        	@Override
        	public View getView(int position, View convertView,
        			ViewGroup parent) {
        		View view =super.getView(position, convertView, parent);

        		TextView textView=(TextView) view.findViewById(android.R.id.text1);
        		textView.setTextColor(Color.BLACK);

        		return view;
        	}
        };*/
		MensaListAdapter adapter = new MensaListAdapter(getActivity(), android.R.layout.simple_list_item_1);
		
        setListAdapter(adapter);
    }
    
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
		Intent intent = new Intent();
		intent.setClassName(getActivity().getPackageName(), getActivity().getPackageName()+".MenuList");
		//intent.putExtra("int_value", Model.getInstance().getMensaHashMap().get(l.getAdapter().getItem(position).toString()));
		MensaListAdapter a = (MensaListAdapter) l.getAdapter();
		intent.putExtra("int_value", a.getItem(position).getId() );
		startActivity(intent);

    }
}
