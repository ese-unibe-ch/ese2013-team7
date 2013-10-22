package com.ese2013.mensaunibe;

import java.util.ArrayList;

import com.ese2013.mensaunibe.model.Model;
import com.ese2013.mensaunibe.model.menu.DailyMenu;
import com.ese2013.mensaunibe.model.menu.Menuplan;

import android.content.Context;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.StyleSpan;
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
		
		TextView textView=(TextView) view.findViewById(android.R.id.text1);

		ListItem item = items.get(position);
		
		if(item.isSection()) {
			ListSectionItem si = (ListSectionItem) item;
            view = inflater.inflate(R.layout.menu_title, null);
            view.setLongClickable(false);
            final TextView sectionView =
                (TextView) view.findViewById(R.id.menu_title);
            sectionView.setText(si.toString());
		} else {
			DailyMenu dm = (DailyMenu) item;
			textView.setTextColor(Color.BLACK);
			SpannableString menuString = new SpannableString(dm.getTitle() + "\n"+dm.getMenu());
			menuString.setSpan(new StyleSpan(android.graphics.Typeface.BOLD_ITALIC), 0, dm.getTitle().length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
			textView.setText(menuString);
		}
		return view;
	}
	
	private void populate() {
		//fill
		mMenu = Model.getInstance().getTodaysOrClosestDayMenu(mMensaId);
		//later use toText()
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
