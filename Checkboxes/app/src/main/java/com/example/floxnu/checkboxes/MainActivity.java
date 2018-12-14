package com.example.floxnu.checkboxes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    CheckBox never,gonna,give,you,up;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        never = findViewById(R.id.never_box);
        gonna = findViewById(R.id.gonna_boc);
        give = findViewById(R.id.give_box);
        you = findViewById(R.id.you_box);
        up = findViewById(R.id.up_box);

    }


    public void showToast(View view) {
        String outputString = "";

        if(never.isChecked()){
            outputString += "Never ";
        }  if(gonna.isChecked()){
            outputString += "Gonna ";
        } if(give.isChecked()){
            outputString += "Give ";
        } if(you.isChecked()){
            outputString += "You ";
        } if(up.isChecked()){
            outputString += "Up";
        }

        Toast.makeText(this, outputString, Toast.LENGTH_SHORT).show();

    }
}
