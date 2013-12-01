package com.ese2013.mensaunibe.notificationservice;

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
	      // use inexact repeating which is easier on battery (system can phase events and not wake at exact times)
	      alarmMgr.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, Constants.ALARM_TRIGGER_AT_TIME,
	               Constants.ALARM_INTERVAL, pendingIntent);
	   }
}
