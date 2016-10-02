package com.squad.vratsa.belmis;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.util.Log;
import android.content.Context;


/**
 * Created by ivo on 01.10.16.
 */

public class Alarm_Receiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent){
        Log.e("In the receiver!!", "Yep");


        String get_you_string = intent.getExtras().getString("extra");

        Intent service_intent = new Intent(context, RingtonePlayingService.class);

        service_intent.putExtra("extra", get_you_string);

        context.startService(service_intent);
    }

}
