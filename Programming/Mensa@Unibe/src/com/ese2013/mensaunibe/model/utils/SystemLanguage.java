package com.ese2013.mensaunibe.model.utils;

import java.util.Locale;

import android.content.Context;
import android.content.res.Configuration;

import com.ese2013.mensaunibe.model.data.PreferenceRequest;

public class SystemLanguage {
	public static Context context;
	
	public static void autoLanguage() {
    	PreferenceRequest pr = new PreferenceRequest();
    	String lang = pr.readLanguage("en");
    	setLanguage(lang);
    }
    
	public static String getLanguage() {
		PreferenceRequest pr = new PreferenceRequest();
		return pr.readLanguage("en");
	}
    public static void changeLanguage(String lang) {
    	PreferenceRequest pr = new PreferenceRequest();
    	pr.writeLanguage(lang);
    	setLanguage(lang);
    }
    	
    private static void setLanguage(String lang) {
    	assert context != null;
		Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        context.getResources().updateConfiguration(config, context.getResources().getDisplayMetrics());
	}
}
