package com.ese2013.mensaunibe.model;

import java.util.ArrayList;
import java.util.Calendar;

import com.ese2013.mensaunibe.model.mensa.Mensa;
import com.ese2013.mensaunibe.model.mensa.MensaData;
import com.ese2013.mensaunibe.model.menu.Menuplan;
import com.memetix.mst.language.Language;

public class Model {

	private static final String TAG ="Model";
	private static Model instance = null;
	private ArrayList<Mensa> mensas;
	private Language language;
	public Model() {
		instance = this;
		mensas = createMensas();
		this.language = Language.GERMAN;
	}
	
	private ArrayList<Mensa> createMensas() {
		MensaData md = new MensaData();
		md.setLanguage(this.language);
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
		return this.forceReload(Language.GERMAN);
	}
	
	private boolean forceReload(Language l) {
		MensaData md = new MensaData();
		md.setLanguage(l);
		ArrayList<Mensa> m = md.getMensaList(true);
		if(m.size() == 0) return false;
		mensas = m;
		return true;
	}
	
	public boolean changeLanguage() {
		boolean success = false;
		if(this.language.compareTo(Language.ENGLISH) == 0) {
			success = forceReload(Language.GERMAN);
			if(success) this.language = Language.GERMAN;
		} else {
			success = forceReload(Language.ENGLISH);
			if(success) this.language = Language.ENGLISH;
		}
		return success;
	}

	public Mensa getMensaById(int mensaId) {
		for(Mensa m : mensas) {
			if(m.getId() == mensaId) return m;
		}
		return null;
	}

	//check for one week
	public ArrayList<Menuplan> getComingDaysMenu(int mensaId) {
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

	public Language getLanguage() {
		return this.language;
	}

}
