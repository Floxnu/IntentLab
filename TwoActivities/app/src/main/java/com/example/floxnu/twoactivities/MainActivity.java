package com.example.floxnu.twoactivities;

import android.content.Intent;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    public static final String EXTRA_MESSAGE = "com.android.example.twoactivities.extra.MESSAGE";
    public static final int TEXT_REQUEST= 1;
    private TextView mReplyHeaderView;

    private TextView mReplyTextView;

    private EditText mMessageEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mReplyHeaderView = findViewById(R.id.text_header_reply);
        mReplyTextView = findViewById(R.id.reply_message);

        Log.d(LOG_TAG, "---------");
        Log.d(LOG_TAG, "onCreate");

        mMessageEditText = findViewById(R.id.editText_main);

        if(savedInstanceState != null){
            boolean isVisible =
                    savedInstanceState.getBoolean("reply_visible");
            if (isVisible){
                mReplyHeaderView.setVisibility(View.VISIBLE);
                mReplyTextView.setVisibility(View.VISIBLE);
                mReplyTextView.setText(savedInstanceState.getString("reply_text"));
            }


        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);

        if(mReplyHeaderView.getVisibility() == View.VISIBLE){
            outState.putBoolean("reply_visibile", true);
            outState.putString("reply_text", mReplyTextView.getText().toString());
        }

    }

    public void launchSecondActivity(View view) {
        Log.d(LOG_TAG, "ButtonClicked");
        Intent intent = new Intent(this, SecondActivity.class);

        String message = mMessageEditText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);

        startActivityForResult(intent, TEXT_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == TEXT_REQUEST) {
            if (resultCode == RESULT_OK) {
                String reply = data.getStringExtra(SecondActivity.EXTRA_REPLY);
                mReplyHeaderView.setVisibility(View.VISIBLE);
                mReplyTextView.setVisibility(View.VISIBLE);
                mReplyTextView.setText(reply);
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(LOG_TAG, "onStart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(LOG_TAG, "onPause");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(LOG_TAG, "onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(LOG_TAG, "onResume");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(LOG_TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(LOG_TAG, "onDestroy");
    }
}
