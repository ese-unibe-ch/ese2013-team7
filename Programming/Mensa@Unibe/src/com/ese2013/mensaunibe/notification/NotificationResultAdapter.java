package com.ese2013.mensaunibe.notification;

import java.util.ArrayList;

import com.ese2013.mensaunibe.R;

import com.ese2013.mensaunibe.menu.MenuActivity;
import com.ese2013.mensaunibe.menu.MenuListAdapter.TitleListener;
import com.ese2013.mensaunibe.model.Model;
import com.ese2013.mensaunibe.model.menu.DailyMenu;
import com.ese2013.mensaunibe.model.menu.MenuDate;
import com.ese2013.mensaunibe.model.utils.AppUtils;
import com.ese2013.mensaunibe.model.utils.ListItem;
import com.ese2013.mensaunibe.model.utils.ListSectionItem;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.TextView;


public class NotificationResultAdapter extends BaseAdapter {
	TitleListener mCallback;
	private Context context;
	private int resource;
	private LayoutInflater inflater;
	ArrayList<NotificationHolder> keywordResultList;
	private ArrayList<ListItem> items;
	private int mMensaId;
	private String keyword;

	public NotificationResultAdapter(Context context, int resource, ArrayList<NotificationHolder> keywordResultList ) {
		super();
		this.context = context;
		this.resource = resource;
		keywordResultList = keywordResultList;
		attachListener();
		populate();
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
			viewHolder.mensa = (TextView) view.findViewById(R.id.menu_date);
			viewHolder.keyword = (TextView) view.findViewById(R.id.result_keyword);
			view.setTag(viewHolder);

		}
		ViewHolder holder = (ViewHolder) view.getTag();
		//list is not consistent, if we don't clear the holder
		clearHolder(holder);
		
		ListItem item = items.get(position);
		
			ListSectionItem si = (ListSectionItem) item;
			holder.mensa.setLongClickable(false);
			holder.mensa.setText(si.toString());
			holder.mensa.setVisibility(View.VISIBLE);
	
			NotificationHolder nh= (NotificationHolder) item;
			holder.keyword.setText(nh.getKeyword());
			holder.keyword.setVisibility(View.VISIBLE);
			view.setOnClickListener( new NotificationResultOnClickListener(nh.getMensaId()) );
		
		return view;
	}
	
	/**
	 * OnClickListener on Menu-Item.
	 * @author Andreas Hohler
	 */
	private class  NotificationResultOnClickListener implements OnClickListener {
		private int mMensaId;
		public  NotificationResultOnClickListener(int mMensaId) {
			this.mMensaId = mMensaId;
		}
		
		@Override
		public void onClick(View view) {
			Intent intent = new Intent();
			intent.setClass(context, MenuActivity.class);
			intent.putExtra("mMensaId", mMensaId);
			context.startActivity(intent);
		}
	}

	/**
	 * clears all views in a ViewHolder and hide them.
	 * @param ViewHolder
	 */
	private void clearHolder(ViewHolder holder) {
		holder.mensa.setText("");
		holder.mensa.setVisibility(View.GONE);
		holder.keyword.setText("");
		holder.keyword.setVisibility(View.GONE);
	}


	private void populate() {
		items = new ArrayList<ListItem>();
		for(int i=0; i > keywordResultList.size(); i++) {
			items.add(Model.getInstance().getMensaById(keywordResultList.get(i).getMensaId()));
		}
	}
	public long getItemId(int position) {
		return position;
	}

	public int getCount() {
		return items.size();
	}

	@Override
	public DailyMenu getItem(int position) {
		return null;
	}
	/**
	 * @author group7
	 * @author Sandor Torok
	 * Just holds the TextView variables (pattern)
	 */
	static class ViewHolder {
		public TextView mensa;
		public TextView keyword;
	}
}