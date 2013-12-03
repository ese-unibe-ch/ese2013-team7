package com.ese2013.mensaunibe.notification;

import java.util.ArrayList;

import com.ese2013.mensaunibe.R;

import com.ese2013.mensaunibe.menu.MenuActivity;
import com.ese2013.mensaunibe.menu.MenuListAdapter.TitleListener;
import com.ese2013.mensaunibe.model.Model;
import com.ese2013.mensaunibe.model.mensa.Mensa;
import com.ese2013.mensaunibe.model.menu.DailyMenu;
import com.ese2013.mensaunibe.model.menu.MenuDate;
import com.ese2013.mensaunibe.model.menu.Menuplan;
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
import android.widget.Toast;


public class NotificationResultAdapter extends BaseAdapter {
	TitleListener mCallback;
	private Context context;
	private int resource;
	private LayoutInflater inflater;
	private ArrayList<NotificationHolder> keywordResultList;
	private ArrayList<ListItem> items;
	private int mMensaId;
	private String keyword;

	public NotificationResultAdapter(Context context, int resource, ArrayList<NotificationHolder> result ) {
		super();
		this.context = context;
		this.resource = resource;
		keywordResultList = result;
	}



	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		if(view == null) {
			inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(this.resource, parent, false);
			ViewHolder viewHolder = new ViewHolder();
			viewHolder.mensa = (TextView) view.findViewById(R.id.notification_result_mensa);
			viewHolder.keyword = (TextView) view.findViewById(R.id.result_keyword);
			view.setTag(viewHolder);

		}
		ViewHolder holder = (ViewHolder) view.getTag();
		//list is not consistent, if we don't clear the holder
		clearHolder(holder);
		
		ListItem item = items.get(position);
			NotificationHolder nh= (NotificationHolder) item;
			
			holder.mensa.setLongClickable(false);
			holder.mensa.setText(Model.getInstance().getMensaById(nh.getMensaId()).getName());
			holder.mensa.setVisibility(View.VISIBLE);
	
			
			holder.keyword.setText(nh.getKeyword());
			holder.keyword.setVisibility(View.VISIBLE);
			view.setOnClickListener( new NotificationResultOnClickListener(nh.getMensaId()) );
		
		return view;
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
	
	
	
	private void populate( ArrayList<String> keywords ) {
		//fill
		items = new ArrayList<ListItem>();
		for(NotificationHolder n:keywordResultList){
			items.add((ListItem) n);
		}
		if(items.size() == 0) Toast.makeText(this.context, context.getString(R.string.notification_no_keywords), Toast.LENGTH_LONG).show();
		
	}


	public long getItemId(int position) {
		return position;
	}

	public int getCount() {
		return items.size();
	}

	@Override
	public NotificationHolder getItem(int position) {
		return (NotificationHolder) items.get(position);
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
	private class  NotificationResultOnClickListener implements OnClickListener {
		private int mMensaId;
		public  NotificationResultOnClickListener(int mMensaId) {
			this.mMensaId = mMensaId;
		}
		
		@Override
		public void onClick(View view) {
			Intent intent = new Intent();
			intent.setClass(context, MenuActivity.class);
			intent.putExtra("int_value", mMensaId);
			context.startActivity(intent);
		}
	}
}