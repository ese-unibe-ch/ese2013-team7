package com.ese2013.mensaunibe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;



public class MensaActivity extends ActionBarActivity implements MensaListFragment.OnListItemClickListener{
	private static final String TAG_MENSALIST_FRAGMENT ="MensaListFragment_tag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_mensa_list);
        
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

	@Override
	public void onListItemSelected(int mensaId) {
		   MenuTabHostFragment fragment = (MenuTabHostFragment) getSupportFragmentManager()
		            .findFragmentById(R.id.MenuFragment);
		        if (fragment != null && fragment.isInLayout()) {
		          fragment.update(mensaId);
		        } else{
		        	Intent intent = new Intent();
		            intent.setClassName(getPackageName(), getPackageName()+".MenuActivity");
		            intent.putExtra("int_value", mensaId);
		            startActivity(intent);		              
		        }
	}
    
}