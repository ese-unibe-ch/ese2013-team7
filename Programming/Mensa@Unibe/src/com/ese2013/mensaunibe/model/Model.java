package com.ese2013.mensaunibe.model;

import java.util.ArrayList;
import java.util.Calendar;

import android.content.Context;

import com.ese2013.mensaunibe.RatingListAdapter;
import com.ese2013.mensaunibe.model.mensa.Mensa;
import com.ese2013.mensaunibe.model.mensa.MensaData;
import com.ese2013.mensaunibe.model.menu.Menuplan;
import com.ese2013.mensaunibe.model.menu.RatingData;

/**
 * @author group7
 * @author Andreas Hohler
 * @author Sandor Torok
 */

public class Model {

	private static final String TAG ="Model";
	private static Model instance = null;
	private ArrayList<Mensa> mensas;
	public Model() {
		instance = this;
		mensas = createMensas();
	}
	
	/**
	 * only called by instance creation, creates all the data
	 * @return an array with all mensas and it's data
	 */
	private ArrayList<Mensa> createMensas() {
		MensaData md = new MensaData();
		ArrayList<Mensa> mensas = md.getMensaList();
		return mensas;
	}
	
	/**
	 * returns a list of all mensas
	 * @return ArrayList of Mensa
	 */
	public ArrayList<Mensa> getMensaList() {
		return mensas;
	}
	
	/**
	 * Singleton pattern, returns the instance of this class
	 * @return Model instance
	 */
	public static Model getInstance() {
		if(instance == null) instance = new Model();
		return instance;
	}
	
	/**
	 * forces a reload of all mens data
	 * @return true if okay, other else false
	 */
	public boolean forceReload() {
		MensaData md = new MensaData();
		ArrayList<Mensa> m = md.getMensaList(true);
		if(m.size() == 0) return false;
		mensas = m;
		return true;
	}

	/**
	 * returns a Mensa object by the mensa id
	 * @param mensaId
	 * @return Mensa object
	 */
	public Mensa getMensaById(int mensaId) {
		assert mensaId != 0;
		for(Mensa m : mensas) {
			if(m.getId() == mensaId) return m;
		}
		return null;
	}
	
	/**
	 * load the rating of a specific menu
	 * @param context
	 * @param adapter RatingListAdapter
	 * @param menu menu string
	 * @param mensaId
	 * @param type: LOAD or SAVE
	 */
	public void loadMenuRating(Context context, RatingListAdapter adapter, String menu, int mensaId, int type) {
		assert context != null && adapter != null && menu.length() > 2 && type != 0;
		RatingData rd = new RatingData(context, menu, mensaId, type);
		rd.setAdapter(adapter);
		rd.execute();
	}

	/**
	 * checks if there are menus for the actual week
	 * @param mensaId
	 * @return ArrayList of Menuplans (one plan = one day)
	 */
	//check for one week
	public ArrayList<Menuplan> getComingDaysMenu(int mensaId) {
		assert mensaId != 0;
		ArrayList <Menuplan> menuPlan = new ArrayList<Menuplan>();
		for(Mensa m : mensas) {
			if(m.getId() == mensaId) {
				Calendar calendar = Calendar.getInstance();
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

	/**
	 * saves the inputted rating of a menu
	 * @param context
	 * @param menu menu string
	 * @param mensaId
	 * @param username Nickname (Google-Email as Hash)
	 * @param comment Comment of user
	 * @param rating Rating (1-5)
	 */
	public void saveRating(Context context, String menu, int mensaId, String username, String comment, int rating) {
		assert context != null && rating != 0 && username.length() > 0;
		RatingData rd = new RatingData(context, menu, mensaId, RatingData.TYPE_SAVE);
		rd.setPostData(username, comment, rating);
		rd.execute();
	}
}
