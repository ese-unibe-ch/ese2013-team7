package com.ese2013.mensaunibe.notification;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ese2013.mensaunibe.R;
import com.ese2013.mensaunibe.menu.MenuListAdapter.TitleListener;
import com.ese2013.mensaunibe.model.Model;

/**
 * @author group7
 * @author Marc Dojtschinov
 * @author Andreas Hohler
 */

public class NotificationResultAdapter extends BaseAdapter {
	TitleListener mCallback;
	private Context context;
	private int resource;
	private LayoutInflater inflater;
	private ArrayList<NotificationHolder> keywordResultList;
	private ArrayList<Integer> mensaIds;
	private HashMap<Integer, ArrayList<String>> map;
	
	public NotificationResultAdapter(Context context, int resource, ArrayList<NotificationHolder> result ) {
		assert result != null;
		this.context = context;
		this.resource = resource;
		keywordResultList = result;
		sortList();
	}
	
	@SuppressLint("UseSparseArrays")
	private void sortList() {
		map = new HashMap<Integer, ArrayList<String>>();
		HashSet<Integer> tempIds = new HashSet<Integer>();
		
		for(NotificationHolder nf : keywordResultList) {
			int i = nf.getMensaId();
			tempIds.add(i);
			if(!map.containsKey(i)) map.put(i,  new ArrayList<String>());
			map.get(i).add( nf.getKeyword() );
		}
		mensaIds = new ArrayList<Integer>(tempIds);
	}


	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		if(view == null) {
			inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(this.resource, parent, false);
			ViewHolder viewHolder = new ViewHolder();
			viewHolder.mensa = (TextView) view.findViewById(R.id.notification_mensa);
			viewHolder.keyword = (TextView) view.findViewById(R.id.notification_keywords);
			view.setTag(viewHolder);
		}
		
		ViewHolder holder = (ViewHolder) view.getTag();
		clearHolder(holder);
		
		int mensaId = mensaIds.get(position);
		ArrayList<String> keywords = map.get(mensaId);
		
		holder.mensa.setLongClickable(false);
		holder.mensa.setText(Model.getInstance().getMensaById(mensaId).getName());
		holder.mensa.setVisibility(View.VISIBLE);
		
		String text = "";
		int fid = 0;
		for(String k : keywords) {
			text+=k;
			fid++;
			if(keywords.size()>1 && fid<keywords.size()) text+=", ";
		}
		holder.keyword.setText(text);
		holder.keyword.setVisibility(View.VISIBLE);
		
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
	
	@Override
	public long getItemId(int position) {
		return position;
	}
	@Override
	public int getCount() {
		return mensaIds.size();
	}

	@Override
	public Integer getItem(int position) {
		return mensaIds.get(position);
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