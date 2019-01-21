package com.example.roomwords;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

public class AddNewWordActivity extends AppCompatActivity {

    EditText wordText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_word);
        wordText = findViewById(R.id.addWordEd);


    }

    public void addWord(View view) {
        Intent intent = new Intent();
        if(TextUtils.isEmpty(wordText.getText())){
            setResult(RESULT_CANCELED, intent);
        } else {
            String word = wordText.getText().toString();
            intent.putExtra("REPLY", word);
            setResult(RESULT_OK, intent);
        }
        finish();
    }
}
