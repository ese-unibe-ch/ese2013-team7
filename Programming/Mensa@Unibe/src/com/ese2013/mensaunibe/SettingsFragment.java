package com.ese2013.mensaunibe;



import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.Toast;

/**
 * @author group7
 * @author Andreas Hohler
 */

public class SettingsFragment extends Fragment{
	private static final String TAG= "SettingsFragment";
	private View view;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		view = inflater.inflate(R.layout.activity_settings, container, false);		
		updateAdapter();
		return view;
	}
	
	private void updateAdapter() {
		NotificationSettingsAdapter adapter = new NotificationSettingsAdapter(getActivity(), R.layout.notification_list_layout);
		
        ListView listView = (ListView) view.findViewById(R.id.listViewNotificationKeywords);
        listView.setAdapter(adapter);
        
        AutoCompleteTextView ac = (AutoCompleteTextView) view.findViewById(R.id.settings_add_keyword);
		ac.setOnKeyListener( new OnKeyEnter(this.getActivity(), adapter) );
	
	}
	@Override
	public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        
    }
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}
	
	private class OnKeyEnter implements OnKeyListener {
		Context context;
		NotificationSettingsAdapter adapter;
		public OnKeyEnter(Context context, NotificationSettingsAdapter adapter) {
			this.context = context;
			this.adapter = adapter;
		}
		@Override
		public boolean onKey(View v, int keyCode, KeyEvent event) {
			if ((event.getAction() == KeyEvent.ACTION_DOWN)) {
				if(keyCode == KeyEvent.KEYCODE_ENTER) {
					Toast.makeText(context, "Keyword added", Toast.LENGTH_LONG).show();
					adapter.add( ((AutoCompleteTextView) v).getText().toString() );
					adapter.notifyDataSetChanged();
				}
			}
			return false;
		}
	}
}