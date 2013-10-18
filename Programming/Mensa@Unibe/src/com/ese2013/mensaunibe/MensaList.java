package com.ese2013.mensaunibe;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;


public class MensaList extends ActionBarActivity {
	private static final String TAG_MENSALISTFRAGMENT ="MensaListFragment_tag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mensa_list);
        
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(TAG_MENSALISTFRAGMENT);
        if (fragment == null) {
            fragment = new MensaListFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.add(android.R.id.content, fragment, TAG_MENSALISTFRAGMENT);
            ft.commit();
        }
       
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
