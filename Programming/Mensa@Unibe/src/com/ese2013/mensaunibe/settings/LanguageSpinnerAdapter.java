package com.ese2013.mensaunibe.settings;

import java.util.ArrayList;

import com.ese2013.mensaunibe.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

public class LanguageSpinnerAdapter extends BaseAdapter implements SpinnerAdapter {
	
	private ArrayList<LanguageItem> items;
	private Context context;
	
	public LanguageSpinnerAdapter(Context context, ArrayList<LanguageItem> items) {
		assert context != null;
		this.context = context;
		populate(items);
	}

	private void populate(ArrayList<LanguageItem> items) {
		assert items != null;
		this.items = items;
	}
	
	@Override
	public int getCount() {
		return items.size();
	}

	@Override
	public LanguageItem getItem(int position) {
		return items.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	
	@Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = vi.inflate(android.R.layout.simple_spinner_dropdown_item, null);
        }
        ((TextView) convertView).setText(items.get(position).toString());
        return convertView;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView = (TextView) View.inflate(context, android.R.layout.simple_spinner_item, null);    	
        textView.setText(items.get(position).toString());
        return textView;
    }

    
	/*@Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = vi.inflate(android.R.layout.simple_spinner_dropdown_item, null);
        }
        
        TextView textView = (TextView) View.inflate(context, android.R.layout.simple_spinner_dropdown_item, null);
		LanguageItem item = items.get(position);
        textView.setText(item.toString());
        
        return convertView;

    }

	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
	    if( convertView == null ){
	        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(android.R.layout.simple_spinner_item, parent, false);
	    }

		TextView textView = (TextView) View.inflate(context, android.R.layout.simple_spinner_item, null);
		LanguageItem item = items.get(position);
        textView.setText(item.toString());
        return textView;
	}*/
}