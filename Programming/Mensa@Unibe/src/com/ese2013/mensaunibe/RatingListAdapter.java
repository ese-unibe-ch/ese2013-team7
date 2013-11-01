package com.ese2013.mensaunibe;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ese2013.mensaunibe.model.menu.Rating;


public class RatingListAdapter extends BaseAdapter {
	private Context context;
	private int resource;
	private LayoutInflater inflater;

	private ArrayList<Rating> items;
	//private ArrayList<Rating> ratings;

	private int mMensaId;
	private String mMenu;

	public RatingListAdapter(Context context, int resource, int mensaId, String menu) {
		super();
		this.context = context;
		this.resource = resource;
		this.mMensaId = mensaId;
		this.mMenu = menu;
		populate();
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		if(view == null) inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		view = inflater.inflate(this.resource, parent, false);

		//TextView textView=(TextView) view.findViewById(android.R.id.text1);
		TextView textView=(TextView) view.findViewById(R.id.rating_list_row);
		//ListItem item = items.get(position);
		Rating rating = items.get(position);

		
		//Mensa mensa = (Mensa)item;
		/*YOUR CHOICE OF COLOR*/
		textView.setTextColor(Color.BLACK);
		textView.setText( rating.getNickname()+"\n"+rating.getText() );

		return view;
	}

	public void update() {
		populate();
	}


	private void populate() {
		//fill
		items = new ArrayList<Rating>();
		items.add( new Rating("Nickname", "Bewärtig und so", 1) );
	}

	public Rating getItem(int position) {
		//if( items.get(position).isSection() ) return null;
		//return (Mensa) items.get(position);
		return items.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public int getCount() {
		return items.size();
	}
}