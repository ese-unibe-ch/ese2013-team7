package com.ese2013.mensaunibe.model;

import java.util.ArrayList;
import java.util.Calendar;

import android.content.Context;

import com.ese2013.mensaunibe.RatingListAdapter;
import com.ese2013.mensaunibe.model.mensa.Mensa;
import com.ese2013.mensaunibe.model.mensa.MensaData;
import com.ese2013.mensaunibe.model.menu.Menuplan;
import com.ese2013.mensaunibe.model.menu.Rating;
import com.ese2013.mensaunibe.model.menu.RatingData;
import com.memetix.mst.language.Language;

public class Model {

	private static final String TAG ="Model";
	private static Model instance = null;
	private ArrayList<Mensa> mensas;
	public Model() {
		instance = this;
		mensas = createMensas();
	}
	
	private ArrayList<Mensa> createMensas() {
		MensaData md = new MensaData();
		ArrayList<Mensa> mensas = md.getMensaList();
		return mensas;
	}
	
	public ArrayList<Mensa> getMensaList() {
		return mensas;
	}
	
	public static Model getInstance() {
		if(instance == null) instance = new Model();
		return instance;
	}
	
	public boolean forceReload() {
		MensaData md = new MensaData();
		ArrayList<Mensa> m = md.getMensaList(true);
		if(m.size() == 0) return false;
		mensas = m;
		return true;
	}

	public Mensa getMensaById(int mensaId) {
		assert mensaId != 0;
		for(Mensa m : mensas) {
			if(m.getId() == mensaId) return m;
		}
		return null;
	}
	
	public void loadMenuRating(Context context, RatingListAdapter adapter, String menu, int mensaId, int type) {
		assert context != null && adapter != null && menu.length() > 2 && type != 0;
		RatingData rd = new RatingData(context, menu, mensaId, type);
		rd.setAdapter(adapter);
		rd.execute();
	}

	//check for one week
	public ArrayList<Menuplan> getComingDaysMenu(int mensaId) {
		assert mensaId != 0;
		ArrayList <Menuplan> menuPlan = new ArrayList<Menuplan>();
		for(Mensa m : mensas) {
			if(m.getId() == mensaId) {
				Calendar calendar = Calendar.getInstance();
				//calendar.add(Calendar.DATE, -5); //for testing
				Menuplan menu;
				int checkForOneWeek = 1;
				do{
					menu = m.getDailyMenu( new MenuDate( calendar.getTime() ) );
					calendar.add(Calendar.DATE, 1);//increment Date always with one day
					checkForOneWeek++;
					if(menu != null) menuPlan.add(menu);
				}while (checkForOneWeek <= 8);

				return menuPlan;
			}
		}
		return null;
	}

	public void saveRating(Context context, String menu, int mensaId, String username, String comment, int rating) {
		assert context != null && rating != 0 && username.length() > 0;
		RatingData rd = new RatingData(context, menu, mensaId, RatingData.TYPE_SAVE);
		rd.setPostData(username, comment, rating);
		rd.execute();
	}
}
