package com.ese2013.mensaunibe;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.ese2013.mensaunibe.model.Model;
import com.ese2013.mensaunibe.model.api.MyLocation;
import com.ese2013.mensaunibe.model.mensa.Mensa;


public class MensaListAdapter extends BaseAdapter {
	private Context context;
	private int resource;
	private LayoutInflater inflater;

	private ArrayList<ListItem> items;
	private ArrayList<Mensa> mensas;
	private MyLocation mLocation;
	private boolean locationReady = false;

	public MensaListAdapter(Context context, int resource) {
		super();
		this.context = context;
		this.resource = resource;
		if(mLocation == null){
			mLocation = MyLocation.getInstance();
			mLocation.setAdapter(this);
		}
		populate();
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		if(view == null) inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		view = inflater.inflate(this.resource, parent, false);

		//TextView textView=(TextView) view.findViewById(android.R.id.text1);
		TextView textView=(TextView) view.findViewById(R.id.mensa_list_row);
		Button direction = (Button) view.findViewById(R.id.direction);
		ListItem item = items.get(position);

		if(item.isSection()) {
			ListSectionItem si = (ListSectionItem)item;
			view = inflater.inflate(R.layout.mensa_list_header, null);
			view.setLongClickable(false);
			final TextView sectionView =
					(TextView) view.findViewById(R.id.mensa_list_header_title);
			sectionView.setText(si.toString());
		} else {
			final Mensa mensa = (Mensa)item;
			/*YOUR CHOICE OF COLOR*/
			textView.setTextColor(Color.BLACK);
			textView.setText( mensa.getName() );

			ToggleButton favorite = (ToggleButton) view.findViewById(R.id.tgl_favorite);
			
			if(mensa.isFavorite()) favorite.setChecked(true);
			else favorite.setChecked(false);
			favorite.setOnCheckedChangeListener(new FavoriteOnClickListener(mensa,favorite,this));
			if(locationReady){	
				
				direction.setVisibility(View.VISIBLE);
				direction.setText(mensa.getDistance(mLocation));
				Log.v("Distance of " +mensa.getName(), mensa.getDistance(mLocation));
				direction.setOnClickListener(new View.OnClickListener() {
				     @Override
				     public void onClick(View v) {
				     Intent intent = new Intent(context, MapActivity.class);
				     intent.putExtra("int_value", mensa.getId());
				     context.startActivity(intent);
				     }
				 });
			}
		}
		return view;
	}

	public void update() {
		populate();
	}

	public void locationReady(boolean b){
		if(b){
			locationReady = true;
			populate();
		}else locationReady = false;
	}

	private boolean hasFavoriteMensas() {
		for(Mensa m : mensas) {
			if(m.isFavorite()) return true;
		}
		return false;
	}

	private void populate() {
		//fill
		mensas = Model.getInstance().getMensaList();
		items = new ArrayList<ListItem>();
		if(hasFavoriteMensas()) {
			items.add(new ListSectionItem( context.getString(R.string.mensa_list_favorites) ) );
			for(Mensa m : mensas) {
				if(m.isFavorite()) items.add(m);
			}
		}

		items.add(new ListSectionItem( context.getString(R.string.mensa_list_header) ) );
		for(Mensa m2 : mensas) {
			if(!m2.isFavorite()) items.add(m2);
		}

		if(mensas.size() == 0) Toast.makeText(this.context, "No data available. Please refresh.", Toast.LENGTH_LONG).show();
	}

	public Mensa getItem(int position) {
		if( items.get(position).isSection() ) return null;
		return (Mensa) items.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public int getCount() {
		return items.size();
	}
}