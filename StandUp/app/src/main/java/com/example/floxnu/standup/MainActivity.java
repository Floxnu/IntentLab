package com.example.floxnu.standup;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    ToggleButton alarmBtn;

    public static final String CHANNEL_NAME = "some_channel";
    public static final int NOTIFICATION_ID = 0;


    AlarmManager alarmManager;

    public void createNotificationChannel(){
        mNotManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        if(Build.VERSION.SDK_INT >= 26){
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_NAME, "Alarm Channel", NotificationManager.IMPORTANCE_HIGH);
            channel.enableLights(true);
            channel.enableVibration(true);
            channel.setLightColor(Color.MAGENTA);
            channel.setDescription("Stand UP");
            mNotManager.createNotificationChannel(channel);
        }

    }

    NotificationManager mNotManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent notifyIntent = new Intent(this, AlarmReceiver.class);
        final PendingIntent notifyPendingIntent = PendingIntent.getBroadcast(this,
                NOTIFICATION_ID, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        alarmBtn = findViewById(R.id.alarmButton);

        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        alarmBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String message = isChecked?"Alarm On":"Alarm Off";
                if(isChecked){
                    long repeatInterval = AlarmManager.INTERVAL_FIFTEEN_MINUTES;

                    long triggerTime = SystemClock.elapsedRealtime() + repeatInterval;
                    alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,triggerTime, repeatInterval, notifyPendingIntent);
                } else{
                    mNotManager.cancelAll();
                    if(alarmManager != null){
                        alarmManager.cancel(notifyPendingIntent);
                    }
                }
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });

        createNotificationChannel();

    }

    public void nextAlarm(View view) {
        if(alarmManager != null){

            long alarmTime = alarmManager.getNextAlarmClock().getTriggerTime();
            Toast.makeText(this, String.valueOf(alarmTime), Toast.LENGTH_SHORT).show();
        }

    }
}
