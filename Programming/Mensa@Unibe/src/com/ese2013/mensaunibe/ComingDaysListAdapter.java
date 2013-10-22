package com.ese2013.mensaunibe;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ese2013.mensaunibe.model.Model;
import com.ese2013.mensaunibe.model.menu.DailyMenu;
import com.ese2013.mensaunibe.model.menu.Menuplan;

public class ComingDaysListAdapter extends BaseAdapter{

	private Context context;
	private int resource;
	private LayoutInflater inflater;
	
	private ArrayList<ListItem> items;
	private ArrayList<Menuplan> mMenus;
	private int mMensaId;
	
	public ComingDaysListAdapter(Context context, int resource, int mensaId) {
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
		mMenus = Model.getInstance().getComingDaysMenu(mMensaId);
		for(Menuplan m : mMenus) {
		items.add(new ListSectionItem(m.getDate().toText()));
			for (DailyMenu dm : m){
				items.add(dm);
			}
		}
	}
	
	public long getItemId(int position) {
		return position;
	}
	
	public int getCount() {
		return items.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
