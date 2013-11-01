package com.ese2013.mensaunibe;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import com.ese2013.mensaunibe.MenuListAdapter.ViewHolder;
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
		if(view == null) {
			inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			ViewHolder viewHolder = new ViewHolder();
			view = inflater.inflate(this.resource, parent, false);
			viewHolder.user = (TextView) view.findViewById(R.id.rating_user);
			viewHolder.text = (TextView) view.findViewById(R.id.rating_text);
			viewHolder.rating = (RatingBar) view.findViewById(R.id.rating_stars);
			view.setTag(viewHolder);
		}
		
		ViewHolder holder = (ViewHolder) view.getTag();
		clearHolder(holder);
		
		view.setClickable(false);
		view.setFocusable(false);

		Rating rating = items.get(position);
		
		holder.user.setText(rating.getNickname());
		holder.user.setVisibility(View.VISIBLE);
		holder.text.setText(rating.getText());
		holder.text.setVisibility(View.VISIBLE);
		holder.rating.setRating(rating.getRating());
		holder.rating.setVisibility(View.VISIBLE);
		
		return view;
	}

	private void populate() {
		//fill
		items = new ArrayList<Rating>();
		//RatingData rd = new RatingData(this, this.mMenu);
		//rd.execute();
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
	
	static class ViewHolder {
		public TextView user;
		public TextView text;
		public RatingBar rating;
	}
	
	private void clearHolder(ViewHolder holder) {
		holder.rating.setRating(0);
		holder.rating.setVisibility(View.GONE);
		holder.user.setText("");
		holder.user.setVisibility(View.GONE);
		holder.text.setText("");
		holder.text.setVisibility(View.GONE);
	}
}