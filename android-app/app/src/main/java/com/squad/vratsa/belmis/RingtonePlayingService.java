package com.squad.vratsa.belmis;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;


public class RingtonePlayingService extends Service{
    MediaPlayer media_song;

    @Nullable
    @Override
    public IBinder onBind(Intent intent){
        Log.e("MyActivity", "In the Richard service");
        return null;
    };


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("LocalService", "Received start id " + startId + ": " + intent);



        String state = intent.getExtras().getString("extra");
        if(state.equals("alarm on")){
            startId = 1;
        }
        else {
            startId = 0;
        }




        media_song = MediaPlayer.create(this, R.raw.super_rington);
        media_song.start();


        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {

        // Tell the user we stopped.
        Toast.makeText(this, "on destroy called", Toast.LENGTH_SHORT).show();
    }


}
