package com.ese2013.mensaunibe;

import java.util.ArrayList;

import com.ese2013.mensaunibe.model.mensa.Mensa;
import com.ese2013.mensaunibe.model.Model;


/*import android.content.Intent;
import android.view.View.OnClickListener;*/
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.ToggleButton;


public class MensaListAdapter extends BaseAdapter {
	private Context context;
	private int resource;
	private LayoutInflater inflater;
	
	private ArrayList<ListItem> items;
	private ArrayList<Mensa> mensas;
	
	public MensaListAdapter(Context context, int resource) {
		super();
		this.context = context;
		this.resource = resource;
		this.items = new ArrayList<ListItem>();
		populate();
	}
	
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		if(view == null) inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		view = inflater.inflate(this.resource, parent, false);
		
		//TextView textView=(TextView) view.findViewById(android.R.id.text1);
		TextView textView=(TextView) view.findViewById(R.id.mensa_list_row);
		ListItem item = items.get(position);
		
		
		
		if(item.isSection()) {
			ListSectionItem si = (ListSectionItem)item;
            view = inflater.inflate(R.layout.mensa_list_header, null);
            view.setLongClickable(false);
            final TextView sectionView =
                (TextView) view.findViewById(R.id.mensa_list_header_title);
            sectionView.setText(si.toString());
		} else {
			Mensa mensa = (Mensa)item;
			/*YOUR CHOICE OF COLOR*/
			textView.setTextColor(Color.BLACK);
			textView.setText( mensa.getName() );

			ToggleButton favorite = (ToggleButton) view.findViewById(R.id.tgl_favorite);
			Log.e("MensaListAdapter", "favorite1: "+favorite);
			favorite.setOnClickListener(new FavoriteOnClickListener(mensa, favorite));
		}
		return view;
	}
	
	private void populate() {
		//fill
		mensas = Model.getInstance().getMensaList();
		mensas.get(0).setFavorite(true);
		items.add(new ListSectionItem( context.getString(R.string.mensa_list_favorites) ) );
		for(Mensa m : mensas) {
			if(m.isFavorite()) items.add(m);
		}
		
		items.add(new ListSectionItem( context.getString(R.string.mensa_list_header) ) );
		for(Mensa m2 : mensas) {
			if(!m2.isFavorite()) items.add(m2);
		}
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
