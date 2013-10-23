package com.ese2013.mensaunibe;

import com.ese2013.mensaunibe.model.mensa.Mensa;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ToggleButton;

public class FavoriteOnClickListener implements OnCheckedChangeListener{
	private Mensa mensa;
	private ToggleButton favorite;
	private MensaListAdapter mensaAdapter;
	
	
	public FavoriteOnClickListener(Mensa m, ToggleButton b, MensaListAdapter adapter){
		this.favorite = b;
		this.mensa = m;
		this.mensaAdapter = adapter;
	}


	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		if(!favorite.isChecked()) {
			mensa.setFavorite(false);
		} else {
			mensa.setFavorite(true);
		}
		mensaAdapter.update();
		mensaAdapter.notifyDataSetChanged();
	}
}
