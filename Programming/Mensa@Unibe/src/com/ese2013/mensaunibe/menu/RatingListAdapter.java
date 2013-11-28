package com.ese2013.mensaunibe.menu;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import com.ese2013.mensaunibe.R;
import com.ese2013.mensaunibe.R.id;
import com.ese2013.mensaunibe.model.menu.Rating;

/**
 * @author group7
 * @author Andreas Hohler
 */

public class RatingListAdapter extends BaseAdapter {
	private Context context;
	private int resource;
	private LayoutInflater inflater;

	private ArrayList<Rating> items = new ArrayList<Rating>();

	private float avgStars;

	public RatingListAdapter(Context context, int resource) {
		super();
		this.context = context;
		this.resource = resource;
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
			viewHolder.avg_rating = (RatingBar) view.findViewById(R.id.rating_avg);
			viewHolder.avg_rating_text = (TextView) view.findViewById(R.id.rating_avg_text);
			view.setTag(viewHolder);
		}
		
		
		ViewHolder holder = (ViewHolder) view.getTag();
		clearHolder(holder);
		
		view.setClickable(false);
		view.setFocusable(false);

		Rating rating = items.get(position);
		
		if(rating.isAvg()) {
			holder.avg_rating_text.setVisibility(View.VISIBLE);
			holder.avg_rating.setRating(this.avgStars);
			holder.avg_rating.setVisibility(View.VISIBLE);
		} else {
			holder.user.setText(rating.getNickname());
			holder.user.setVisibility(View.VISIBLE);
			holder.text.setText(rating.getText());
			holder.text.setVisibility(View.VISIBLE);
			holder.rating.setRating(rating.getRating());
			holder.rating.setVisibility(View.VISIBLE);
		}
		return view;
	}

	/**
	 * populates the Ratinng list with ratings
	 * @param r: List of Ratings
	 * @param avgStars: average rating
	 */
	public void populate(ArrayList<Rating> r, float avgStars) {
		assert avgStars <= 5;
		//fill
		items = new ArrayList<Rating>();
		Rating avg = new Rating("Average Rating", "", 0);
		avg.setAvg(true);
		items.add( avg );
		this.avgStars = avgStars; 
		for(Rating ra : r) {
			items.add(ra);
		}
		//items = r;
	}

	public Rating getItem(int position) {
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
		public TextView avg_rating_text;
		public RatingBar avg_rating;
	}
	
	/**
	 * Clears all the views and hide them
	 * @param ViewHolder that holds all views
	 */
	private void clearHolder(ViewHolder holder) {
		holder.rating.setRating(0);
		holder.rating.setVisibility(View.GONE);
		holder.user.setText("");
		holder.user.setVisibility(View.GONE);
		holder.text.setText("");
		holder.text.setVisibility(View.GONE);
		holder.avg_rating.setVisibility(View.GONE);
		holder.avg_rating_text.setVisibility(View.GONE);
	}
}