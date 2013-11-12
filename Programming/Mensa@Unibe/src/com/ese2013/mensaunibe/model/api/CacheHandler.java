package com.ese2013.mensaunibe.model.api;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.annotation.SuppressLint;
import android.content.Context;

import com.ese2013.mensaunibe.App;

/**
 * @author group7
 * @author Jan Binzegger
 */

@SuppressLint("SimpleDateFormat")
public class CacheHandler {
	
	private Context context;
	
	public CacheHandler(){
		context = App.getAppContext();
	}

	/**
	 * checks, if the cached data is outdated and has to be renewed
	 * @param type: Mensa or menu, is just the file name
	 * @return true, if new cache is needed, other else false
	 */
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

	/**
	 * saves the data to the cache
	 * @param textToCache: string to be cached [json format]
	 * @param type: mensa or menu, is actually the filename
	 */
	public void setNewCache(String textToCache, String type) 
	{
		CacheRequest.writeAllCachedText(context, type + ".txt", textToCache);
	}

	/**
	 * loads data out of the cache
	 * @param type: filename, mensa or menu
	 * @return a json string of data
	 */
	public String loadCache(String type) 
	{
		return CacheRequest.readAllCachedText(context, type + ".txt");
	}
}
