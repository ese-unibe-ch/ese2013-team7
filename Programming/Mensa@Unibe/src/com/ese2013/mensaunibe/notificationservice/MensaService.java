package com.ese2013.mensaunibe.notificationservice;


import java.util.ArrayList;

import com.ese2013.mensaunibe.R;
import com.ese2013.mensaunibe.mensa.MensaActivity;
import com.ese2013.mensaunibe.model.data.PreferenceRequest;
import com.ese2013.mensaunibe.notification.NotificationHolder;
import com.ese2013.mensaunibe.notification.NotificationResultActivity;
import com.ese2013.mensaunibe.notification.WordNotificationUtil;

import android.annotation.SuppressLint;
import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationCompat.Builder;

public class MensaService extends IntentService {
	
	private MensaActivity mensaActivity;

	public MensaService() {
		super("Mensa@Unibe");
	}
	
 @Override
	public void onStart(Intent intent, int startId) {
	      super.onStart(intent, startId);
	   }


	@Override
	protected void onHandleIntent(Intent intent) {
		// TODO Auto-generated method stub
		
		//here we can add a server Query. 
		PreferenceRequest pr = new PreferenceRequest();
		WordNotificationUtil wu = new WordNotificationUtil();
		
		//for testing
		NotificationHolder test =new NotificationHolder(1, "poulet");
		NotificationHolder test1 =new NotificationHolder(2, "schwein");
		//ArrayList<NotificationHolder> result = wu.compareToKeywords( pr.readNotificationKeywords() );
		ArrayList<NotificationHolder> result = new ArrayList<NotificationHolder>();
		result.add(test);
		result.add(test1);
		if(result.size()>0) this.sendNotification(this, result);
	}
	
	 @SuppressLint("NewApi")
	private void sendNotification(Context context, ArrayList<NotificationHolder> keywordResultList) {
	      Intent notificationIntent = new Intent(context, NotificationResultActivity.class);
	      
	      notificationIntent.putParcelableArrayListExtra("keywordResultList", keywordResultList);
	      PendingIntent contentIntent = PendingIntent.getActivity(context, 0, notificationIntent, 0);

	      NotificationManager notificationMgr =
	               (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
	      Builder notification = new NotificationCompat.Builder(this)
	            	        .setContentTitle("Todays MensaMenu contain some of your favorite ingredients")
	            	        .setContentText("ingredients: "+getMatchedKeywords(keywordResultList))
	            	        .setSmallIcon(R.drawable.ic_launcher)
	            	        .setContentIntent(contentIntent);
	      				
	            
	      notificationMgr.notify(0, notification.build());
	   }
	 //retruns a String with all keywords
	 private String getMatchedKeywords(ArrayList<NotificationHolder> keywordResultList){
		String matchedKeywords = "";
		 for(int i=0; i <keywordResultList.size();i++){
			 if(matchedKeywords.contains(keywordResultList.get(i).getKeyword())==false)
			matchedKeywords += keywordResultList.get(i).getKeyword();
			 if(keywordResultList.size()>1)matchedKeywords += ", ";
			
		}
		return matchedKeywords;
	 }
	
}
