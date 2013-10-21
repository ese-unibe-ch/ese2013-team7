package com.ese2013.mensaunibe.model.api;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.annotation.SuppressLint;
import android.content.Context;

import com.ese2013.mensaunibe.App;

@SuppressLint("SimpleDateFormat")
public class CacheHandler {
	
	private Context context;
	
	public CacheHandler(){
		context = App.getAppContext();
	}

	public boolean needNewCache(String type) 
	{
		File file = new File(context.getCacheDir(), type + ".txt");
		
		Date lastModDate = new Date(file.lastModified());
		Date today = new Date();
		
		int cacheCaledarWeek = Integer.parseInt(new SimpleDateFormat("w").format(lastModDate));
		int CaledarWeek = Integer.parseInt(new SimpleDateFormat("w").format(today));
		
		if (cacheCaledarWeek == CaledarWeek) {
			return false;
		}
		return true;
	}

	public void setNewCache(String textToCache, String type) 
	{
		CacheRequest.writeAllCachedText(context, type + ".txt", textToCache);
	}

	public String loadCache(String type) 
	{
		return CacheRequest.readAllCachedText(context, type + ".txt");
	}
}
