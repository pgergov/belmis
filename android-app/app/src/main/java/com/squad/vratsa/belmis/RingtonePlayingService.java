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
    private int startId;
    private boolean isRunning;

    @Nullable
    @Override
    public IBinder onBind(Intent intent){
        Log.e("MyActivity", "In the Richard service");
        return null;
    };


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String state = intent.getExtras().getString("extra");
        Log.i("LocalService", "Received start id " + startId + ": " + intent + ": " + state);
        this.isRunning = true;


        assert state != null;
        switch (state) {
            case "alarm off":
                startId = 0;
                this.startId = 0;
                break;
            case "alarm on":
                startId = 1;
                this.startId = 1;

                break;
            default:
                startId = 0;
                this.startId = 0;
                break;
        }

        if(startId==0 && this.isRunning){
            media_song.stop();
            media_song.reset();
            this.isRunning = false;
        }

        if (startId == 1){
            media_song = MediaPlayer.create(this, R.raw.super_rington);
            media_song.start();
            this.isRunning = true;
        }





        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {

        // Tell the user we stopped.
        Toast.makeText(this, "on destroy called", Toast.LENGTH_SHORT).show();
        this.isRunning = false;
    }


}
