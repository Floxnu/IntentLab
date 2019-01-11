package com.example.floxnu.powerreciever;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class CustomReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String intentAction = intent.getAction();

        String message = "Unknown intent action";

        if(intentAction != null){
            switch (intentAction){
                case Intent.ACTION_POWER_CONNECTED:
                    message = "Power Connected";
                    break;
                case Intent.ACTION_POWER_DISCONNECTED:
                    message = "Power Disconnected";
                    break;
                case MainActivity.ACTION_CUSTOM_BROADCAST:

                    int r = intent.getIntExtra("random", 0);

                    r = r*r;
                    message = "Local Custom Broadcast. Square of random number: " + r;

                    break;
                case Intent.ACTION_HEADSET_PLUG:
                    message = "Headset Status Changed";
            }
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        }
    }
}
