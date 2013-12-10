package com.ese2013.mensaunibe.menu;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ese2013.mensaunibe.R;
import com.ese2013.mensaunibe.model.Model;
import com.ese2013.mensaunibe.model.menu.DailyMenu;
import com.ese2013.mensaunibe.model.menu.MenuDate;
import com.ese2013.mensaunibe.model.menu.Menuplan;
import com.ese2013.mensaunibe.model.utils.AppUtils;
import com.ese2013.mensaunibe.model.utils.ListItem;
import com.ese2013.mensaunibe.model.utils.ListSectionItem;

/**
 * @author group7
 * @author Sandor Torok
 * @author Andreas Hohler
 */

public class MenuListAdapter extends BaseAdapter{

	TitleListener mCallback;
	private Context context;
	private int resource;
	private LayoutInflater inflater;
	private ArrayList<ListItem> items;
	private ArrayList<Menuplan> mMenus;
	private int mMensaId;
	private int mFirstOrAll;

	public MenuListAdapter(Context context, int resource, int mensaId, int firstOrAll) {
		super();
		this.context = context;
		this.resource = resource;
		mMensaId = mensaId;
		mFirstOrAll = firstOrAll;
		attachListener();
		populate(firstOrAll);
	}

	private void attachListener() {
		try {
			mCallback = (TitleListener) context;
		} catch (ClassCastException e) {
			throw new ClassCastException(context.toString()
					+ " must implement TitleListener");
		}

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
			viewHolder.item = (RelativeLayout) view.findViewById(R.id.menu_list_item);
			viewHolder.section_item = (LinearLayout) view.findViewById(R.id.menu_list_section_item);
			view.setTag(viewHolder);

		}
		ViewHolder holder = (ViewHolder) view.getTag();
		//list is not consistent, if we don't clear the holder
		clearHolder(holder);
		
		ListItem item = items.get(position);
		
		if(item.isSection()) {
			ListSectionItem si = (ListSectionItem) item;
			holder.date.setLongClickable(false);
			holder.date.setText(si.toString());
			holder.date.setVisibility(View.VISIBLE);
			holder.section_item.setVisibility(View.VISIBLE);
		} else {
			DailyMenu dm = (DailyMenu) item;
			holder.title.setText(dm.getTitle());
			holder.title.setVisibility(View.VISIBLE);
			holder.text.setText(dm.getMenu());
			holder.text.setVisibility(View.VISIBLE);
			holder.item.setVisibility(View.VISIBLE);
			
			view.setOnClickListener( new MenuOnClickListener( dm.getMenu() ) );
			view.setOnLongClickListener( new MenuOnLongClickListener () );
		}
		return view;
	}
	
	/**
	 * OnClickListener on Menu-Item.
	 * @author Andreas Hohler
	 */
	private class MenuOnLongClickListener implements OnLongClickListener {
		@Override
		public boolean onLongClick(View v) {
			Toast.makeText(v.getContext(), context.getString(R.string.menu_hint), Toast.LENGTH_SHORT).show();
	        return true;
		}
	}
	
	private class MenuOnClickListener implements OnClickListener {
		private String title;
		public MenuOnClickListener(String title) {
			this.title = title;
		}
		
		@Override
		public void onClick(View view) {
			Intent intent = new Intent();
			intent.setClass(context, RatingActivity.class);
			intent.putExtra("menu", this.title);
			intent.putExtra(AppUtils.MENSA_ID, mMensaId);
			context.startActivity(intent);
		}
	}

	/**
	 * clears all views in a ViewHolder and hide them.
	 * @param ViewHolder
	 */
	private void clearHolder(ViewHolder holder) {
		holder.date.setText("");
		holder.date.setVisibility(View.GONE);
		holder.title.setText("");
		holder.title.setVisibility(View.GONE);
		holder.text.setText("");
		holder.text.setVisibility(View.GONE);
		holder.item.setVisibility(View.GONE);
		holder.section_item.setVisibility(View.GONE);
	}

	/**
	 * populates the menu list.
	 * @param firstOrAll: if only one day should be displayed
	 */
	private void populate(int firstOrAll) {
		//fill
		this.items = new ArrayList<ListItem>();
		boolean firstrun = true;
		mMenus = Model.getInstance().getComingDaysMenu(mMensaId);
		for(Menuplan m : mMenus) {
			//skip first menuplan
			if(firstrun && firstOrAll == AppUtils.ALL_EXCEPT_FIRST){
				firstrun = false;
				continue;
			}
			//first item will be also the tab title
			if(items.isEmpty()){
				notifyListeners(m.getDate());
			}
			items.add(new ListSectionItem(m.getDate().toText(false)));
			for (DailyMenu dm : m){
				if(dm != null)items.add(dm);
			}

			if(firstOrAll == AppUtils.FIRST_MENU) break;//stop if we need just first menuplan
		}
	}

	private void notifyListeners(MenuDate date) {
		String tabTitle = "";
		if(AppUtils.FIRST_MENU != mFirstOrAll) tabTitle += context.getString(R.string.from)+" ";
		tabTitle +=date.toText(true);
		mCallback.updateTiteListener(tabTitle, mFirstOrAll);

	}

	public long getItemId(int position) {
		return position;
	}

	public int getCount() {
		return items.size();
	}

	@Override
	public DailyMenu getItem(int position) {
		if(!items.get(position).isSection()) {
			return (DailyMenu) items.get(position);
		}
		return null;
	}

	public interface TitleListener {
		public void updateTiteListener(String tabTitle, int firstMenu);
	}

	/**
	 * @author group7
	 * @author Sandor Torok
	 * Just holds the TextView variables (pattern)
	 */
	static class ViewHolder {
		public TextView date;
		public TextView title;
		public TextView text;
		public RelativeLayout item;
		public LinearLayout section_item;
	}

}
