package com.example.floxnu.shoppinglistthing;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ListItemsActivity extends AppCompatActivity {

    String outputText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_items);
    }

    public void listItemClick(View view) {
        String buttonText = ((Button)view).getText().toString();
        if(buttonText != null){
            outputText = buttonText;
        }
        Intent replyIntent = new Intent();
        replyIntent.putExtra("Item", outputText);
        setResult(RESULT_OK, replyIntent);
        finish();
    }
}
