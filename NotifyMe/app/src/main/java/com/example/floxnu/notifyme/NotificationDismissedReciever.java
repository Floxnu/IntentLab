package com.example.floxnu.notifyme;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class NotificationDismissedReciever extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(MainActivity.DISMISS_NOTIFICATION)) {
            ((MainActivity) context).setButtonStates(true, false, false);
        }
//        MainActivity.instance.setButtonStates(true, false, false);
    }
}
