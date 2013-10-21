package com.ese2013.mensaunibe.model;

import java.util.ArrayList;
import java.util.Calendar;

import android.util.Log;

import com.ese2013.mensaunibe.model.mensa.*;
import com.ese2013.mensaunibe.model.menu.DailyMenu;
import com.ese2013.mensaunibe.model.menu.Menuplan;

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

	/*If today's menu is not available, (for example is weekend) 
	 * then checks each day, till the first day with menu is found. max for one more week */
	public Menuplan getTodaysOrClosestDayMenu(int mensaId) {
		for(Mensa m : mensas) {
			if(m.getId() == mensaId) {
				Calendar calendar = Calendar.getInstance();
				Menuplan menu;
				int checkForOneWeek = 1;
				do{
					menu = m.getDailyMenu( new MenuDate( calendar.getTime() ) );
					calendar.add(Calendar.DATE, 1);//increment Date always with one day
					checkForOneWeek++;
				}while (menu == null && checkForOneWeek <= 7);

				if(menu == null) menu = new Menuplan();
				//Log.e("Model", "menuplan:"+menu.toString());
				return menu;
			}
		}
		return null;
	}

	public Mensa getMensaById(int mensaId) {
		for(Mensa m : mensas) {
			if(m.getId() == mensaId) return m;
		}
		return null;
	}

	public ArrayList<Menuplan> getComingDaysMenu(int mensaId) {
		ArrayList <Menuplan> menuPlan = new ArrayList<Menuplan>();
		for(Mensa m : mensas) {
			if(m.getId() == mensaId) {
				Calendar calendar = Calendar.getInstance();
				Menuplan menu;
				int checkForOneWeek = 1;
				do{
					calendar.add(Calendar.DATE, 1);//increment Date always with one day
					menu = m.getDailyMenu( new MenuDate( calendar.getTime() ) );
					checkForOneWeek++;
					if(menu != null) menuPlan.add(menu);
				}while (checkForOneWeek <= 7);

				return menuPlan;
			}
		}
		return null;
	}

}
