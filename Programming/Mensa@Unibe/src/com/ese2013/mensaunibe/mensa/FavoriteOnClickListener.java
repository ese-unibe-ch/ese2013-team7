package com.ese2013.mensaunibe.mensa;

import com.ese2013.mensaunibe.App;
import com.ese2013.mensaunibe.R;
import com.ese2013.mensaunibe.model.mensa.Mensa;

import android.content.Context;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ToggleButton;

/**
 * @author group7
 * @author Andreas Hohler
 * @see MensaListAdapter
 */

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
		Context context = App.getAppContext();
		String mensaName = mensa.getName() + " ";
		if(!favorite.isChecked()) {
			mensa.setFavorite(false);
			Toast.makeText(context,
					mensaName + context.getString(R.string.removed_from_favorites),
					Toast.LENGTH_SHORT).show();
		} else {
			mensa.setFavorite(true);
			Toast.makeText(context,
					mensaName + context.getString(R.string.added_to_favorites),
					Toast.LENGTH_SHORT).show();
		}
		mensaAdapter.update();
		mensaAdapter.notifyDataSetChanged();
	}
}
