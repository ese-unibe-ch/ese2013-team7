package com.ese2013.mensaunibe.model;

import java.util.ArrayList;
import java.util.Date;

import com.ese2013.mensaunibe.model.mensa.*;
import com.ese2013.mensaunibe.model.menu.Menuplan;

public class Model {

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

	public Menuplan getTodaysMenu(int mensaId) {
		for(Mensa m : mensas) {
			if(m.getId() == mensaId) {
				Menuplan menu = m.getDailyMenu( new MenuDate( new Date() ) );
				//
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
}
