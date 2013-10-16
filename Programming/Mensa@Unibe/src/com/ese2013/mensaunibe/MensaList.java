package com.ese2013.mensaunibe;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.ese2013.mensaunibe.api.Mensa.Mensa;
import com.ese2013.mensaunibe.api.Mensa.MensaData;

public class MensaList extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mensa_list);
        
        List<String> valueList = new ArrayList<String>();
        
        final MensaData md = new MensaData();
		ArrayList<Mensa> mensas = md.getMensaList();
		for(Mensa m : mensas) {
			valueList.add(m.getName());
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
        
        lv.setOnItemClickListener(new OnItemClickListener()
        {
        	@Override
        	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3)
        	{
        		Intent intent = new Intent();
        		intent.setClassName(getPackageName(), getPackageName()+".MenuList");
        		intent.putExtra("int_value", md.getMensaHashMap().get(lv.getAdapter().getItem(arg2).toString()));
        		startActivity(intent);
        	}
        });
    }
    
    


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        // getMenuInflater().inflate(R.menu.mensa_list, menu);
        
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.actionbar_mensa_list, menu);
        return super.onCreateOptionsMenu(menu);

    }
    
}