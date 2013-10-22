package com.ese2013.mensaunibe;

import com.ese2013.mensaunibe.model.mensa.Mensa;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ToggleButton;

public class FavoriteOnClickListener implements OnClickListener{
	private Mensa mensa;
	private ToggleButton favorite;
	
	
	FavoriteOnClickListener(Mensa m, ToggleButton b){
		this.favorite = b;
		this.mensa = m;
	}
	
	@Override
	public void onClick(View v) {
		if(favorite.isChecked()){
		favorite.setChecked(false);
		mensa.setFavorite(false);
		}
		else{
			favorite.setChecked(true);
			mensa.setFavorite(true);
		}
		
	}

}
