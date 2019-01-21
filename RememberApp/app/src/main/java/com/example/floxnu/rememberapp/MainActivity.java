package com.example.floxnu.rememberapp;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView mAgeText;
    private int age;
    private SharedPreferences mPreference;
    private static final String sharedPrefFile = BuildConfig.APPLICATION_ID;
    private static final String AGE_KEY = "AGE_KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAgeText = findViewById(R.id.ageText);
        mPreference = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
        mAgeText.setText(String.valueOf(mPreference.getInt(AGE_KEY, 0)));


        age = Integer.parseInt(mAgeText.getText().toString());
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor editor = mPreference.edit();
        editor.putInt(AGE_KEY, age);
        editor.apply();

    }

    public void decreaseAmount(View view) {
        if(age>0){
            age--;
        }
        mAgeText.setText(String.valueOf(age));
    }

    public void increaseAmount(View view) {
        age++;
        mAgeText.setText(String.valueOf(age));
    }
}
