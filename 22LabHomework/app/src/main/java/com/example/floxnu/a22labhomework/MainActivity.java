package com.example.floxnu.a22labhomework;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView mCountText;
    EditText mEditText;

    int currentCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCountText = findViewById(R.id.count_text);
        mEditText = findViewById(R.id.edit_text);

        currentCount = 0;

        if(savedInstanceState  != null){
            currentCount = savedInstanceState.getInt("count");
            mEditText.setText(savedInstanceState.getString("text"));
        }
        setCountText(currentCount);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString("text", mEditText.getText().toString());
        outState.putInt("count", currentCount);
    }

    public void setCountText(int i){
        mCountText.setText(Integer.toString(i));
    }

    public void increaseCount(View view) {
        currentCount++;
        setCountText(currentCount);
    }
}
