package com.ese2013.mensaunibe;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import com.ese2013.mensaunibe.model.Model;

public class MenuActivity extends ActionBarActivity 
{
	private static final String TAG_MENULIST_FRAGMENT ="MenuListFragment_tag";
	private int mMensaId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_menu_list);
        
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        // Specify that tabs should be displayed in the action bar.
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        
        mMensaId = getIntent().getIntExtra("int_value", 0);
        setTitle( Model.getInstance().getMensaById(mMensaId).getName() );
        
        MenuTabHostFragment fragment = (MenuTabHostFragment)getSupportFragmentManager()
        								.findFragmentByTag(TAG_MENULIST_FRAGMENT);
        if (fragment == null) {
            fragment = new MenuTabHostFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.add(android.R.id.content, fragment, TAG_MENULIST_FRAGMENT);
            ft.commit();
        }
        
        fragment.update(mMensaId);
       
    }
}
