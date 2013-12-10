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
import com.ese2013.mensaunibe.model.menu.Rating;

/**
 * @author group7
 * @author Andreas Hohler
 */

public class RatingListAdapter extends BaseAdapter {
	private Context context;
	private int resource;
	private LayoutInflater inflater;
	private View baseView;

	private ArrayList<Rating> items = new ArrayList<Rating>();

	public RatingListAdapter(Context context, int resource) {
		super();
		this.context = context;
		this.resource = resource;
	}
	
	public void setBaseView(View view) {
		this.baseView = view;
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

		Rating rating = items.get(position);

		holder.user.setText(rating.getDate());
		holder.user.setVisibility(View.VISIBLE);
		if(rating.getText().length() > 1) {
			holder.text.setText(rating.getText());
			holder.text.setVisibility(View.VISIBLE);
		}
		holder.rating.setRating(rating.getRating());
		holder.rating.setVisibility(View.VISIBLE);
		return view;
	}

	/**
	 * populates the Ratinng list with ratings
	 * @param r: List of Ratings
	 * @param avgStars: average rating
	 */
	public void populate(ArrayList<Rating> r, float avgStars) {
		assert avgStars <= 5 && avgStars >= 0;
		//fill
		items = new ArrayList<Rating>();
		updateAvg(avgStars);
		for(Rating ra : r) {
			items.add(ra);
		}
	}
	
	private void updateAvg(float avg) {
		TextView avg_rating_text = (TextView) baseView.findViewById(R.id.rating_avg_text);
		RatingBar avg_rating = (RatingBar) baseView.findViewById(R.id.rating_avg);
		avg_rating_text.setVisibility(View.VISIBLE);
		avg_rating.setRating(avg);
		avg_rating.setVisibility(View.VISIBLE);
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
	}
}