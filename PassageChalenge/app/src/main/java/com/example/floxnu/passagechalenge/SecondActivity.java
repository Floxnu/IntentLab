package com.example.floxnu.passagechalenge;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        TextView textDisplay = findViewById(R.id.textView);
        Intent intent = getIntent();
        textDisplay.setText(intent.getIntExtra(MainActivity.EXTRA_STRING_ID, 0));
    }
}
