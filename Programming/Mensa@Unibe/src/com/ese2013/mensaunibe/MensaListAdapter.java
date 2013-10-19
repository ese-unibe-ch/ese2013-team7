package com.ese2013.mensaunibe;

import java.util.ArrayList;

import com.ese2013.mensaunibe.model.mensa.Mensa;
import com.ese2013.mensaunibe.model.Model;


/*import android.content.Intent;
import android.view.View.OnClickListener;*/
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


public class MensaListAdapter extends BaseAdapter {
	private Context context;
	private int resource;
	private LayoutInflater inflater;
	
	private ArrayList<Mensa> mensas;
	
	public MensaListAdapter(Context context, int resource) {
		super();
		this.context = context;
		this.resource = resource;
		populate();
	}
	
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		if(view == null) inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		view = inflater.inflate(this.resource, parent, false);
        
		TextView textView=(TextView) view.findViewById(android.R.id.text1);

		/*YOUR CHOICE OF COLOR*/
		textView.setTextColor(Color.BLACK);
		Mensa mensa = mensas.get(position);
		textView.setText( mensa.getName() );
		//setTextViewListener(textView, position);
		return view;
	}
	
	/*public void setTextViewListener(View view, final int position){
        view.setOnClickListener(new OnClickListener() {
        	public void onClick(View viewIn) {
        	Intent intent = new Intent();
        	intent.setClass(viewIn.getContext(), MenuList.class);
        	intent.putExtra("int_value", mensas.get(position).getId());
        	context.startActivity(intent);
            }
        });
	}*/
	
	private void populate() {
		//fill
		this.mensas = Model.getInstance().getMensaList();
	}
	
	public Mensa getItem(int position) {
		return mensas.get(position);
	}
	
	public long getItemId(int position) {
		return position;
	}
	
	public int getCount() {
		return mensas.size();
	}


}
