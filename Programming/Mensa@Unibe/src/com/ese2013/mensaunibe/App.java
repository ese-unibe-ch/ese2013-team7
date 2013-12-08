package com.ese2013.mensaunibe;

import com.ese2013.mensaunibe.model.utils.SystemLanguage;
import com.ese2013.mensaunibe.notificationservice.MensaService;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

/**
 * @author group7
 * @author Jan Binzegger
 */

public class App extends Application{

    private static Context context;

    public void onCreate(){
        super.onCreate();
        App.context = getApplicationContext();
        SystemLanguage.context = context;
        SystemLanguage.autoLanguage();
    }
    
    /**
     * Returns the context of the whole Application for Filesystem-Access, etc.
     * @return the Context of the App itself.
     */
    public static Context getAppContext() {
        return App.context;
    }
    @Override
    public void onTerminate(){
    	Intent intent =new Intent(this, MensaService.class);
		   startService(intent);
    }
    	
}