package com.ese2013.mensaunibe.notificationservice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MensaAlarmReceiver extends BroadcastReceiver {
		
	@Override
	  public void onReceive(Context context, Intent intent) {
	      context.startService(new Intent(context, MensaService.class));
	   }
}
