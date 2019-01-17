package com.example.floxnu.standup;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

public class AlarmReceiver extends BroadcastReceiver {

    NotificationManager mNotManager;

    public void deliverNotification(Context context){
        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(
                context, MainActivity.NOTIFICATION_ID, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(
                context, MainActivity.CHANNEL_NAME).
                setSmallIcon(R.drawable.ic_walk).setContentTitle("Stand UP").
                setContentText("Come on, come on").setContentIntent(pendingIntent).
                setPriority(NotificationCompat.PRIORITY_HIGH).
                setDefaults(NotificationCompat.DEFAULT_ALL);

        mNotManager.notify(MainActivity.NOTIFICATION_ID, builder.build());

    }


    @Override
    public void onReceive(Context context, Intent intent) {
        mNotManager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
        deliverNotification(context);

    }
}
