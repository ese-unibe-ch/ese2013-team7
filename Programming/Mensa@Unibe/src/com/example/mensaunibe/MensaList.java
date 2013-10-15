package com.example.mensaunibe;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MensaList extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mensa_list);
        
        List valueList = new ArrayList<String>();
        for (int i = 0; i < 10; i++)
        {
        	valueList.add("value"+i);
        }    
        
        ListAdapter adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, valueList)
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
        
        final ListView lv = (ListView)findViewById(R.id.listView1);

        lv.setAdapter(adapter);    
    }
    
    


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.mensa_list, menu);
        return true;
    }
    
}
