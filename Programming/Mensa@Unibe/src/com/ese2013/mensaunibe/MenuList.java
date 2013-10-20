package com.ese2013.mensaunibe;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.ShareActionProvider;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ese2013.mensaunibe.model.Model;
import com.ese2013.mensaunibe.model.menu.DailyMenu;
import com.ese2013.mensaunibe.model.menu.Menuplan;

public class MenuList extends ActionBarActivity 
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_list);
        
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    
        Intent intent = getIntent();
        
        List<String> valueList = new ArrayList<String>();
        
        int mensaId = intent.getIntExtra("int_value", 0);
        
        setTitle( Model.getInstance().getMensaById(mensaId).getName() );
        
        Menuplan plan = Model.getInstance().getTodaysMenu(mensaId);
        
        if(plan.size() == 0) {
        	Toast t = Toast.makeText(this, getString(R.string.no_menu), Toast.LENGTH_SHORT);
            t.show();
        } else {
        
	        for(DailyMenu m : plan) {
	        	valueList.add(m.getTitle() + "\n" + m.getMenu());
	        }
        }
    
        ListAdapter adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, valueList)
        {        
        	@Override
        	public View getView(int position, View convertView, ViewGroup parent) {
        		View view =super.getView(position, convertView, parent);

        		TextView textView=(TextView) view.findViewById(android.R.id.text1);

        		//YOUR CHOICE OF COLOR
        		textView.setTextColor(Color.BLACK);

        		return view;
        	}
        };
    
        final ListView lv = (ListView)findViewById(R.id.listView1);

        lv.setAdapter(adapter);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar_menu_list, menu);

        return super.onCreateOptionsMenu(menu);

    }
}
