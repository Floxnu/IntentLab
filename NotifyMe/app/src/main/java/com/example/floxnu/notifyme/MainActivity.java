package com.example.floxnu.notifyme;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private static final String PRIMARY_CHANNEL_ID = "primary_notification_channel";
    private static final int NOTIFICATION_ID = 0;
    public static final String UPDATE_NOTIFICATION = BuildConfig.APPLICATION_ID + ".UPDATE_NOTIFICATION";
    public static final String DISMISS_NOTIFICATION = BuildConfig.APPLICATION_ID + ".DISMISS_NOTIFICATION";

    private NotificationReceiver mReceiver = new NotificationReceiver();
    private NotificationDismissedReciever mDismisReceiver = new NotificationDismissedReciever();

    private NotificationManager mNotificationManager;

    public static MainActivity  instance;

    Button notify;
    Button update;
    Button cancel;


    private NotificationCompat.Builder getNotificationBuilder(){
        Intent intent = new Intent(this, MainActivity.class);

        PendingIntent pending = PendingIntent.getActivity(
                this,
                NOTIFICATION_ID,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT
        );

        NotificationCompat.Builder notifyBuilder  = new NotificationCompat.Builder(this, PRIMARY_CHANNEL_ID);

        notifyBuilder.setContentTitle("This is a TEST NOTIFICATION").
                setContentText("Here is some text to fill out the box!").
                setSmallIcon(R.drawable.ic_stat_test)
                .setContentIntent(pending)
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setDefaults(NotificationCompat.DEFAULT_ALL);

        return notifyBuilder;
    }

    public void createNotificationChannel(){
        mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel(PRIMARY_CHANNEL_ID, "CICCC Not", NotificationManager.IMPORTANCE_HIGH);
            channel.enableLights(true);
            channel.setLightColor(Color.MAGENTA);
            channel.enableVibration(true);
            channel.setDescription("Notification Description");
            mNotificationManager.createNotificationChannel(channel);
        }

    }

    public void setButtonStates(boolean notifyBool, boolean updateBool, boolean cancelBool){
        notify.setEnabled(notifyBool);
        update.setEnabled(updateBool);
        cancel.setEnabled(cancelBool);
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(mReceiver);
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        update = findViewById(R.id.updateBtn);
        cancel = findViewById(R.id.cancelBtn);
        notify = findViewById(R.id.notifyButton);
        instance = this;

        setButtonStates(true, false, false);

        createNotificationChannel();

        IntentFilter filter = new IntentFilter();
        filter.addAction(UPDATE_NOTIFICATION);
        filter.addAction(DISMISS_NOTIFICATION);
        registerReceiver(mReceiver, filter);
        registerReceiver(mDismisReceiver, filter);

    }

    public void sendNotification(View view) {
        sendNotification();
        setButtonStates(false, true,true);
    }

    public void sendNotification(){
        Intent updateIntent = new Intent(UPDATE_NOTIFICATION);
        PendingIntent updatePendingIntent = PendingIntent.getBroadcast(this, NOTIFICATION_ID, updateIntent, PendingIntent.FLAG_ONE_SHOT);

        Intent dismissedIntent = new Intent(DISMISS_NOTIFICATION);
        PendingIntent pendingDismissedIntent = PendingIntent.getBroadcast(this, NOTIFICATION_ID, dismissedIntent, PendingIntent.FLAG_ONE_SHOT);

        NotificationCompat.Builder builder = getNotificationBuilder();


        builder.addAction(R.drawable.ic_update, "Update", updatePendingIntent).setDeleteIntent(pendingDismissedIntent);

        mNotificationManager.notify(NOTIFICATION_ID, builder.build());

    }

    public void updateNotification(View view) {
        Bitmap image = BitmapFactory.decodeResource(getResources(), R.drawable.mascot_1);
        NotificationCompat.Builder builder = getNotificationBuilder();

        Intent dismissedIntent = new Intent(this, NotificationDismissedReciever.class);
        PendingIntent pendingDismissedIntent = PendingIntent.getBroadcast(this, NOTIFICATION_ID, dismissedIntent, PendingIntent.FLAG_ONE_SHOT);

        builder.setStyle(new NotificationCompat.InboxStyle().setBigContentTitle("Some Big Content").addLine("This is another line.").addLine("More Lines").addLine("Even more Lines").setSummaryText("Some Summary text for this notification")).setDeleteIntent(pendingDismissedIntent);

        mNotificationManager.notify(NOTIFICATION_ID, builder.build());
        setButtonStates(false, false, true);

    }

    public void cancelNotification(View view) {
        mNotificationManager.cancel(NOTIFICATION_ID);
        setButtonStates(true, false, false);
    }

    public class NotificationReceiver extends BroadcastReceiver{

        public NotificationReceiver(){

        }

        @Override
        public void onReceive(Context context, Intent intent) {
            updateNotification(null);
        }

    }
}
