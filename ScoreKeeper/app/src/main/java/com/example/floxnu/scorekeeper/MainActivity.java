package com.example.floxnu.scorekeeper;

import android.graphics.drawable.StateListDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int team1Score;
    private int team2Score;

    private TextView team1ScoreView;
    private TextView team2ScoreView;

    ImageButton team1Down;
    ImageButton team2Down;
    ImageButton team1Up;
    ImageButton team2Up;


    static final String STATE_SCORE_1 = "Team 1 Score";
    static final String STATE_SCORE_2 = "Team 2 Score";

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        int nightMode = AppCompatDelegate.getDefaultNightMode();
        if(nightMode == AppCompatDelegate.MODE_NIGHT_YES){
            menu.findItem(R.id.night_mode).setTitle(R.string.day_mode);
        } else{
            menu.findItem(R.id.night_mode).setTitle(R.string.night_mode);
        }
        return true;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(STATE_SCORE_1, team1Score);
        outState.putInt(STATE_SCORE_2, team2Score);
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId()==R.id.night_mode) {
            // Get the night mode state of the app.
            int nightMode = AppCompatDelegate.getDefaultNightMode();
            //Set the theme mode for the restarted activity
            if (nightMode == AppCompatDelegate.MODE_NIGHT_YES) {
                AppCompatDelegate.setDefaultNightMode
                        (AppCompatDelegate.MODE_NIGHT_NO);
            } else {
                AppCompatDelegate.setDefaultNightMode
                        (AppCompatDelegate.MODE_NIGHT_YES);
            }
        }
        recreate();

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        team1ScoreView = findViewById(R.id.score1);
        team2ScoreView = findViewById(R.id.score2);

        team1Up = findViewById(R.id.increase1);
        team2Up = findViewById(R.id.increase2);
        team2Down = findViewById(R.id.decrease2);
        team1Down = findViewById(R.id.decrease1);

        StateListDrawable draw = new StateListDrawable();
        if (savedInstanceState != null) {
            team1Score = savedInstanceState.getInt(STATE_SCORE_1);
            team2Score = savedInstanceState.getInt(STATE_SCORE_2);

            //Set the score text views
            team1ScoreView.setText(String.valueOf(team1Score));
            team2ScoreView.setText(String.valueOf(team2Score));
        }
    }

    public void decreaseScore(View view) {
        int viewID = view.getId();
        switch (viewID){
            //If it was on Team 1
            case R.id.decrease1:
                //Decrement the score and update the TextView
                team1Score--;
                team1ScoreView.setText(String.valueOf(team1Score));
                break;
            //If it was Team 2
            case R.id.decrease2:
                //Decrement the score and update the TextView
                team2Score--;
                team2ScoreView.setText(String.valueOf(team2Score));
        }

    }

    public void increaseScore(View view) {
        int viewID = view.getId();
        switch (viewID){
            //If it was on Team 1
            case R.id.increase1:
                //Decrement the score and update the TextView
                team1Score++;
                team1ScoreView.setText(String.valueOf(team1Score));
                break;
            //If it was Team 2
            case R.id.increase2:
                //Decrement the score and update the TextView
                team2Score++;
                team2ScoreView.setText(String.valueOf(team2Score));
        }
    }
}
