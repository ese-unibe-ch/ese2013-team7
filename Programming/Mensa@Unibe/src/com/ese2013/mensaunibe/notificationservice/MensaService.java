package com.ese2013.mensaunibe.notificationservice;


import java.util.ArrayList;

import com.ese2013.mensaunibe.R;
import com.ese2013.mensaunibe.mensa.MensaActivity;
import com.ese2013.mensaunibe.model.data.PreferenceRequest;
import com.ese2013.mensaunibe.notification.NotificationHolder;
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
		ArrayList<NotificationHolder> result = wu.compareToKeywords( pr.readNotificationKeywords() );
		
		this.sendNotification(this, "matches: "+result.size());
	}
	
	 @SuppressLint("NewApi")
	private void sendNotification(Context context, String menuName) {
	      Intent notificationIntent = new Intent(context, MensaActivity.class);
	      PendingIntent contentIntent = PendingIntent.getActivity(context, 0, notificationIntent, 0);

	      NotificationManager notificationMgr =
	               (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
	      Builder notification = new NotificationCompat.Builder(this)
	            	        .setContentTitle("Today in your Mensa: "+ menuName)
	            	        .setContentText("Don't know yet")
	            	        .setSmallIcon(R.drawable.ic_launcher);
	               
	            
	      notificationMgr.notify(0, notification.build());
	   }
	
}
