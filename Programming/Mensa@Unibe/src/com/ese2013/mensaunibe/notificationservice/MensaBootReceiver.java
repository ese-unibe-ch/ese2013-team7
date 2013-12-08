package com.ese2013.mensaunibe.notificationservice;

import java.util.Calendar;

import com.ese2013.mensaunibe.model.utils.AppUtils;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class MensaBootReceiver extends BroadcastReceiver {

	
	@Override
	   public void onReceive(Context context, Intent intent) {
	      AlarmManager alarmMgr = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
	      PendingIntent pendingIntent =
	               PendingIntent.getBroadcast(context, 0, new Intent(context, MensaAlarmReceiver.class), 0);
	      
	      //set start time of the service
	      Calendar startTime = Calendar.getInstance();
	      startTime.set(Calendar.HOUR_OF_DAY, 10);
	      startTime.set(Calendar.MINUTE, 30);
	      startTime.set(Calendar.SECOND, 0);
	      // use inexact repeating which is easier on battery (system can phase events and not wake at exact times)
	      alarmMgr.setInexactRepeating(AlarmManager.RTC_WAKEUP, startTime.getTimeInMillis(),
	               AppUtils.ALARM_INTERVAL, pendingIntent);
	   }
}


