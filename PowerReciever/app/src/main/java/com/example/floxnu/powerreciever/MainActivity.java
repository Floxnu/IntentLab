package com.example.floxnu.powerreciever;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public static final String ACTION_CUSTOM_BROADCAST = BuildConfig.APPLICATION_ID + ".ACTION_CUSTOM_BROADCAST";
    private CustomReceiver mReceiver = new CustomReceiver();

    TextView mRandomIntText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRandomIntText = findViewById(R.id.rndmNumber);

        LocalBroadcastManager.getInstance(this).registerReceiver(mReceiver, new IntentFilter(ACTION_CUSTOM_BROADCAST));

        IntentFilter filter = new IntentFilter();

        filter.addAction(Intent.ACTION_POWER_DISCONNECTED);
        filter.addAction(Intent.ACTION_POWER_CONNECTED);
        filter.addAction(Intent.ACTION_HEADSET_PLUG);


        this.registerReceiver(mReceiver, filter);

    }

    public void sendCustomBroadcast(View view) {

        Random r = new Random();

        int randominr = r.nextInt(20) + 1;

        mRandomIntText.setText(String.valueOf(randominr));

        Intent customBroadcastIntent = new Intent(ACTION_CUSTOM_BROADCAST);
        customBroadcastIntent.putExtra("random", randominr);

        LocalBroadcastManager.getInstance(this).sendBroadcast(customBroadcastIntent);

    }

    @Override
    protected void onDestroy() {

        super.onDestroy();

        this.unregisterReceiver(mReceiver);
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mReceiver);

    }
}
