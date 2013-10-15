package com.example.mensaunibe;


import com.example.mensaunibe.api.MensaData;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;


public class MensaList extends ListActivity {

    static final String[] FRUITS = new String[] { "Apple", "Avocado", "Banana",
		"Blueberry", "Coconut", "Durian", "Guava", "Kiwifruit",
		"Jackfruit", "Mango", "Olive", "Pear", "Sugar-apple" };

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.id.listView1);
		setContentView(R.layout.activity_mensa_list);
		
		// no more this
	
		//setListAdapter(new ArrayAdapter<String>(this, R.id.listView1,FRUITS));
		ListAdapter adapter = new ArrayAdapter<String>(this, R.id.listView1, FRUITS);
	
		ListView listView = getListView();
		listView.setTextFilterEnabled(true);
		
		listView.setAdapter(adapter);
	
		listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
			    // When clicked, show a toast with the TextView text
			    Toast.makeText(getApplicationContext(),
				((TextView) view).getText(), Toast.LENGTH_SHORT).show();
			}
		});
	
	}
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.mensa_list, menu);
        return true;
    }
    
}
