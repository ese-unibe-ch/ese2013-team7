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
	
	static class ViewHolder {
		public TextView date;
		public TextView title;
		public TextView text;
	}
	
	public CurrentDayListAdapter(Context context, int resource, int mensaId) {
		super();
		this.context = context;
		this.resource = resource;
		this.items = new ArrayList<ListItem>();
		populate(mensaId);
	}
	
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		if(view == null) {
			inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(this.resource, parent, false);
			ViewHolder viewHolder = new ViewHolder();
			viewHolder.date = (TextView) view.findViewById(R.id.menu_date);
			viewHolder.title = (TextView) view.findViewById(R.id.menu_title);
			viewHolder.text = (TextView) view.findViewById(R.id.menu_text);
			view.setTag(viewHolder);

		}
		ViewHolder holder = (ViewHolder) view.getTag();
		ListItem item = items.get(position);

		if(item.isSection()) {
			ListSectionItem si = (ListSectionItem) item;
			holder.date.setLongClickable(false);
			holder.date.setText(si.toString());
			holder.date.setVisibility(View.VISIBLE);
		} else {
			DailyMenu dm = (DailyMenu) item;
			holder.title.setText(dm.getTitle());
			holder.title.setVisibility(View.VISIBLE);
			holder.text.setText(dm.getMenu());
			holder.text.setVisibility(View.VISIBLE);

		}
		return view;
	}
	
	private void populate(int mensaId) {
		//fill
		mMenu = Model.getInstance().getTodaysOrClosestDayMenu(mensaId);
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
