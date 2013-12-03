package com.ese2013.mensaunibe.mensa;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.ese2013.mensaunibe.R;
import com.ese2013.mensaunibe.map.MapActivityOneMensa;
import com.ese2013.mensaunibe.map.MyLocation;
import com.ese2013.mensaunibe.model.Model;
import com.ese2013.mensaunibe.model.mensa.Mensa;
import com.ese2013.mensaunibe.model.utils.ListItem;
import com.ese2013.mensaunibe.model.utils.ListSectionItem;

/**
 * @author group17
 * @author Andreas Hohler
 * @author Sandor Torok
 */

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
		TextView direction = (TextView) view.findViewById(R.id.direction);
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
				
				direction.setText(mensa.getDistance(mLocation));
				Log.v("Distance of " +mensa.getName(), mensa.getDistance(mLocation));
				direction.setOnClickListener(new View.OnClickListener() {
				     @Override
				     public void onClick(View v) {
				     Intent intent = new Intent(context, MapActivityOneMensa.class);
				     intent.putExtra("int_value", mensa.getId());
				     context.startActivity(intent);
				     }
				 });
			}
		}
		return view;
	}

	/**
	 * Is the public method to repopulate the whole List.
	 */
	public void update() {
		populate();
	}

	/**
	 * Decides to populate or not. Is dependent on the location
	 * @param b - true for location is ready, or false for not
	 */
	public void locationReady(boolean b){
		if(b){
			locationReady = true;
			populate();
		}else locationReady = false;
	}

	/**
	 * Checks, if there are any favorite mensas in order to display this section or not
	 * @return true, if there are favorite mensas, or false if not
	 */
	private boolean hasFavoriteMensas() {
		for(Mensa m : mensas) {
			if(m.isFavorite()) return true;
		}
		return false;
	}
	
	/**
	 * Populates the List with the data from the Model.
	 * Can show a toast, if no data is available
	 */
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

		if(mensas.size() == 0) Toast.makeText(this.context, context.getString(R.string.mensa_no_data_av), Toast.LENGTH_LONG).show();
	}

	/**
	 * Returns the Mensa-object of a specific list position
	 * or null, if it's a section
	 * @param position: position of the item
	 * @return the Mensa object
	 */
	public Mensa getItem(int position) {
		if( items.get(position).isSection() ) return null;
		return (Mensa) items.get(position);
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