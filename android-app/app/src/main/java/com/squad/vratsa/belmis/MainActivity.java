package com.squad.vratsa.belmis;

import java.io.*;
import java.net.*;
import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;



import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;


import java.nio.charset.StandardCharsets;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    // mu stuff
    AlarmManager alarm_manager;
    TimePicker alarm_timepicker;
    TextView update_text;
    Context context;
    PendingIntent pending_intent;
    EditText minutes_input;

    private void set_alarm_text(String s){
        update_text.setText(s);
    }
    // HTTP POST request
//    private void sendPost() throws Exception {
//
//        //Your server URL
//        String url = "http://192.168.1.100:8000/";
//        URL obj = new URL(url);
//        //HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
//        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
//        //add reuqest header
//        con.setRequestMethod("POST");
//        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
//
//        //Request Parameters you want to send
////        String urlParameters = "sn=C02G8416DRJM&cn=&locale=&caller=&num=12345";
//
//        // Send post request
//        con.setDoOutput(true);// Should be part of code only for .Net web-services else no need for PHP
//        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
////        wr.writeBytes(urlParameters);
//        wr.flush();
//        wr.close();
//
//        int responseCode = con.getResponseCode();
//        Log.e("Response Code", String.valueOf(responseCode));
//        System.out.println("\nSending 'POST' request to URL : " + url);
////        System.out.println("Post parameters : " + urlParameters);
//        System.out.println("Response Code : " + responseCode);
//
//        BufferedReader in = new BufferedReader(
//                new InputStreamReader(con.getInputStream()));
//        String inputLine;
//        StringBuffer response = new StringBuffer();
//
//        while ((inputLine = in.readLine()) != null) {
//            response.append(inputLine);
//        }
//        in.close();
//
//        //print result
//        System.out.println(response.toString());
//
//    }
//
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        this.context = this;

        alarm_manager = (AlarmManager) getSystemService(ALARM_SERVICE);

        alarm_timepicker = (TimePicker) findViewById(R.id.timePicker);

        update_text = (TextView) findViewById(R.id.update_text);

        final Calendar calendar = Calendar.getInstance();

        Button alarm_on= (Button) findViewById(R.id.alarm_on);

        Button alarm_off = (Button) findViewById(R.id.alarm_off);

        final Intent my_intent = new Intent(this.context, Alarm_Receiver.class);


        Button make_me_coffee = (Button) findViewById(R.id.make_me_coffee);

        make_me_coffee.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                new SendRequest().execute("http://192.168.1.106:8000/api/coffee/make/282c7275-d24a-4373-9ca0-29693b1bd1e3/");
                // stop current alarm
                my_intent.putExtra("extra", "alalrm off");

                sendBroadcast(my_intent);

                alarm_manager.cancel(pending_intent);
                set_alarm_text("Alarm canceled");


                // alarm management
                minutes_input = (EditText) findViewById(R.id.minutes);
                alarm_timepicker.setCurrentMinute(alarm_timepicker.getCurrentMinute() + Integer.parseInt(minutes_input.getText().toString()));
                calendar.set(Calendar.HOUR_OF_DAY, alarm_timepicker.getCurrentHour());
                calendar.set(Calendar.MINUTE, alarm_timepicker.getCurrentMinute());
                int hour = alarm_timepicker.getCurrentHour();
                int minute = alarm_timepicker.getCurrentMinute();


                String hour_string = String.valueOf(hour);
                String minute_string = String.valueOf(minute);

                if(hour > 12){
                    hour_string = String.valueOf(hour - 12);
                }
                if(minute < 10){
                    minute_string = String.valueOf("0"  + String.valueOf(minute));
                }

                my_intent.putExtra("extra", "alarm on");


                set_alarm_text("Alarm set to: " + hour_string + " - " + minute_string);

                pending_intent = PendingIntent.getBroadcast(MainActivity.this, 0, my_intent, PendingIntent.FLAG_UPDATE_CURRENT);


                alarm_manager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pending_intent);



            }
        });

        alarm_on.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                calendar.set(Calendar.HOUR_OF_DAY, alarm_timepicker.getCurrentHour());
                calendar.set(Calendar.MINUTE, alarm_timepicker.getCurrentMinute());
                int hour = alarm_timepicker.getCurrentHour();
                int minute = alarm_timepicker.getCurrentMinute();


                String hour_string = String.valueOf(hour);
                String minute_string = String.valueOf(minute);

                if(hour > 12){
                    hour_string = String.valueOf(hour - 12);
                }
                if(minute < 10){
                    minute_string = String.valueOf("0"  + String.valueOf(minute));
                }

                my_intent.putExtra("extra", "alarm on");


                set_alarm_text("Alarm set to: " + hour_string + " - " + minute_string);

                pending_intent = PendingIntent.getBroadcast(MainActivity.this, 0, my_intent, PendingIntent.FLAG_UPDATE_CURRENT);


                alarm_manager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pending_intent);
            }
        });

        alarm_off.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){


                my_intent.putExtra("extra", "alalrm off");

                sendBroadcast(my_intent);

                alarm_manager.cancel(pending_intent);
                set_alarm_text("Alarm canceled");


//                //////////
//                set_alarm_text("Alarm Off!");
//
//                alarm_manager.cancel(pending_intent);
//
//                my_intent.putExtra("extra", "alarm off");
//
//
//                sendBroadcast(my_intent);
            }
        });





       FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
