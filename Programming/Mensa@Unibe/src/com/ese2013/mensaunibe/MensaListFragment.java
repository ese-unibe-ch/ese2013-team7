package com.ese2013.mensaunibe;

import java.util.ArrayList;
import java.util.List;

import com.ese2013.mensaunibe.model.Model;
import com.ese2013.mensaunibe.model.mensa.Mensa;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MensaListFragment extends ListFragment{
 
	@Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        
        List<String> valueList = new ArrayList<String>();
        
        //final MensaData md = new MensaData();
		ArrayList<Mensa> mensas = Model.getInstance().getMensaList();
		for(Mensa m : mensas) {
			valueList.add(m.getName());
		}
        
        ListAdapter adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, valueList)
        {        
        	@Override
        	public View getView(int position, View convertView,
        			ViewGroup parent) {
        		View view =super.getView(position, convertView, parent);

        		TextView textView=(TextView) view.findViewById(android.R.id.text1);

        		/*YOUR CHOICE OF COLOR*/
        		textView.setTextColor(Color.BLACK);

        		return view;
        	}
        };
        
        setListAdapter(adapter);
    }
    
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
		Intent intent = new Intent();
		intent.setClassName(getActivity().getPackageName(), getActivity().getPackageName()+".MenuList");
		intent.putExtra("int_value", Model.getInstance().getMensaHashMap().get(l.getAdapter().getItem(position).toString()));
		startActivity(intent);

    }
}
