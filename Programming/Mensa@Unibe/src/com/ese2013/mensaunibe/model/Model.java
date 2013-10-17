package com.ese2013.mensaunibe.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import com.ese2013.mensaunibe.model.mensa.*;
import com.ese2013.mensaunibe.model.menu.Menuplan;

public class Model {

	private static Model instance = null;
	private ArrayList<Mensa> mensas;
	private HashMap<String, Integer> mensaMap;
	public Model() {
		instance = this;
		mensas = createMensas();
		//loadAllData
	}
	
	private ArrayList<Mensa> createMensas() {
		MensaData md = new MensaData();
		ArrayList<Mensa> mensas = md.getMensaList();
		mensaMap = md.getMensaHashMap();
		return mensas;
	}
	
	public ArrayList<Mensa> getMensaList() {
		return mensas;
	}
	
	public HashMap<String, Integer> getMensaHashMap() {
		return mensaMap;
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
}
