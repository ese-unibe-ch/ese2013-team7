package com.ese2013.mensaunibe;

import java.util.ArrayList;

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
		String strStatus= "";
		if(favorite.isChecked()){
		favorite.setChecked(false);
		strStatus = "noFav";
		mensa.setFavorite(false);
		}
		else{
			favorite.setChecked(true);
			strStatus = "fav";
			mensa.setFavorite(true);
		}
		
	}

}
