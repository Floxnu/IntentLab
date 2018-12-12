package com.example.floxnu.assignmenttwo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    Spinner movieSpiner;
    ImageView currentImage;
    TextView currentText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        currentImage = findViewById(R.id.imageView);
        currentText = findViewById(R.id.textView);

        movieSpiner = findViewById(R.id.spinner);

        movieSpiner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String currentName = parent.getItemAtPosition(position).toString();
                currentName = currentName.toLowerCase().replace(" ", "_");
                Log.d("MainActivity: ", "onItemSelected: " + currentName);
                int drawableId = getResources().getIdentifier(currentName, "drawable", getPackageName());
                int textId = getResources().getIdentifier(currentName, "raw", getPackageName());
                currentImage.setImageDrawable(getResources().getDrawable(drawableId));
                Scanner in = new Scanner(getResources().openRawResource(textId));
                String result = "";
                while (in.hasNext()){
                    result += in.nextLine();
                }
                in.close();
                currentText.setText(result);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        getResources().getIdentifier("the_godfather", "raw", getPackageName());


    }
}
