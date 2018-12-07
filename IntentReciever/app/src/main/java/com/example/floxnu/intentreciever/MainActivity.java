package com.example.floxnu.intentreciever;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textToDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textToDisplay = findViewById(R.id.text_display);

        Intent intent = getIntent();
        Uri uri = intent.getData();

        if(uri != null){
            String uriString = "URI: " + uri.toString();
            textToDisplay.setText(uriString);
        }
    }
}
