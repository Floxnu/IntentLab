package com.example.floxnu.droidcafe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class OrderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        Intent intent = getIntent();

        String message = "Order: " + intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        TextView tv = findViewById(R.id.order_vieew);
        tv.setText(message);
    }
}
