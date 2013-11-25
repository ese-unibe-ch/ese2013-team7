package com.ese2013.mensaunibe;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author group17
 * @author Andreas Hohler
 */

public class NotificationSettingsAdapter extends BaseAdapter {
	private Context context;
	private int resource;
	private LayoutInflater inflater;

	private ArrayList<String> items;
	
	public NotificationSettingsAdapter(Context context, int resource) {
		super();
		this.context = context;
		this.resource = resource;
		populate();
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		if(view == null) inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		view = inflater.inflate(this.resource, parent, false);

		//TextView textView=(TextView) view.findViewById(android.R.id.text1);
		TextView textView=(TextView) view.findViewById(R.id.notification_list_text);
		Button delete = (Button) view.findViewById(R.id.delete_keyword);
		String item = items.get(position);

		textView.setText( item );
		return view;
	}

	/**
	 * Is the public method to repopulate the whole List.
	 */
	public void update() {
		populate();
	}

	/**
	 * Populates the List with the data from the Model.
	 * Can show a toast, if no data is available
	 */
	private void populate() {
		//fill
		items = new ArrayList<String>();
		//items = Model.getInstance().getNotificationKeywords();
		if(items.size() == 0) Toast.makeText(this.context, "No existing keywords found.", Toast.LENGTH_LONG).show();
	}

	/**
	 * Returns the keyword of a specific list position
	 * @param position: position of the item
	 * @return the keyword
	 */
	public String getItem(int position) {
		return items.get(position);
	}

	/**
	 * returns just the Id of an list item (it's the position itself)
	 * @param position - position of the item
	 * @return position of the item
	 */
	public long getItemId(int position) {
		return position;
	}

	/**
	 * @return the size of the list
	 */
	public int getCount() {
		return items.size();
	}
}