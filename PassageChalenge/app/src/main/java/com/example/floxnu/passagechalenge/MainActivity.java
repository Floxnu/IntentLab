package com.example.floxnu.passagechalenge;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_STRING_ID = "EXTRA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void toPassage(View view) {

        Intent intent = new Intent(this, SecondActivity.class);
        int id = 0;

        switch (view.getId()){
            case R.id.warhammer_button:
                id = R.string.warhammer;
                break;
            case R.id.lorem_button:
                id = R.string.lorem_ipsum;
                break;
            case R.id.fire_button:
                id = R.string.passage_fire;
                    break;
        }

        intent.putExtra(EXTRA_STRING_ID, id);

        startActivity(intent);

    }
}
