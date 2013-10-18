package com.ese2013.mensaunibe;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;



public class MensaList extends ActionBarActivity {
	private static final String TAG_MENSALIST_FRAGMENT ="MensaListFragment_tag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mensa_list);
        
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(TAG_MENSALIST_FRAGMENT);
        if (fragment == null) {
            fragment = new MensaListFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.add(android.R.id.content, fragment, TAG_MENSALIST_FRAGMENT);
            ft.commit();
        }
       
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar_mensa_list, menu);

        //MenuItem shareItem = menu.findItem(R.id.action_share);
        return super.onCreateOptionsMenu(menu);

    }
    
}
