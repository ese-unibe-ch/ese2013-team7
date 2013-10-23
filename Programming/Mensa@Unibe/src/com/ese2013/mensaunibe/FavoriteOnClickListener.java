package com.ese2013.mensaunibe;

import com.ese2013.mensaunibe.model.mensa.Mensa;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;
import android.widget.ToggleButton;

public class FavoriteOnClickListener implements OnClickListener{
	private Mensa mensa;
	private ToggleButton favorite;
	private MensaListAdapter mensaAdapter;
	
	
	public FavoriteOnClickListener(Mensa m, ToggleButton b, MensaListAdapter adapter){
		this.favorite = b;
		this.mensa = m;
		this.mensaAdapter = adapter;
	}
	
	@Override
	public void onClick(View v) {
		if(favorite.isChecked()) {
			mensa.setFavorite(false);
		} else {
			mensa.setFavorite(true);
		}
		mensaAdapter.notifyDataSetChanged();
		/*if(favorite.isChecked()){
		favorite.setChecked(false);
		mensa.setFavorite(false);
		}
		else{
			favorite.setChecked(true);
			mensa.setFavorite(true);
		}*/
		Toast.makeText(App.getAppContext(), "onclick togglebutton ischecked:"+favorite.isChecked(), Toast.LENGTH_SHORT).show();
	}

}
