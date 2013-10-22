package com.ese2013.mensaunibe;

import java.util.ArrayList;

import com.ese2013.mensaunibe.model.Model;
import com.ese2013.mensaunibe.model.menu.DailyMenu;
import com.ese2013.mensaunibe.model.menu.Menuplan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CurrentDayListAdapter extends BaseAdapter{
	private Context context;
	private int resource;
	private LayoutInflater inflater;
	
	private ArrayList<ListItem> items;
	private Menuplan mMenu;
	private int mMensaId;
	
	public CurrentDayListAdapter(Context context, int resource, int mensaId) {
		super();
		this.context = context;
		this.resource = resource;
		this.items = new ArrayList<ListItem>();
		mMensaId = mensaId;
		populate();
	}
	
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		if(view == null) inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		view = inflater.inflate(this.resource, parent, false);
		
		TextView textView=(TextView) view.findViewById(R.id.menu_text);
		TextView titleView=(TextView) view.findViewById(R.id.menu_title);

		ListItem item = items.get(position);
		
		if(item.isSection()) {
			ListSectionItem si = (ListSectionItem) item;
            view.setLongClickable(false);
            final TextView dateView =
                (TextView) view.findViewById(R.id.menu_date);
            dateView.setText(si.toString());
            dateView.setVisibility(View.VISIBLE);
		} else {
			DailyMenu dm = (DailyMenu) item;
			titleView.setText(dm.getTitle());
			titleView.setVisibility(View.VISIBLE);
			textView.setText(dm.getMenu());
			textView.setVisibility(View.VISIBLE);
		}
		return view;
	}
	
	private void populate() {
		//fill
		mMenu = Model.getInstance().getTodaysOrClosestDayMenu(mMensaId);
		items.add(new ListSectionItem(mMenu.getDate().toText()));
		for (DailyMenu dm : mMenu){
			items.add(dm);
		}
	}
	
	public long getItemId(int position) {
		return position;
	}
	
	public int getCount() {
		return items.size();
	}

	@Override
	public Menuplan getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}
}
