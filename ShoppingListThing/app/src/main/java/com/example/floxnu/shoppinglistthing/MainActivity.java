package com.example.floxnu.shoppinglistthing;

import android.content.ClipData;
import android.content.Intent;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView[] ShoppingListText;
    LinearLayout mainLayout;
    ArrayList<String> itemsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainLayout = findViewById(R.id.main_layout);

        ShoppingListText = new TextView[10];
        itemsList = new ArrayList<>();

        if(savedInstanceState != null){
            ArrayList<String> saved = savedInstanceState.getStringArrayList("Items");
            if(saved != null){
                itemsList.addAll(saved);
            }
        }

        for (int i = 0; i <10; i++){

            ShoppingListText[i] = new TextView(this);
            mainLayout.addView(ShoppingListText[i]);
            ShoppingListText[i].setHint("List item " + i);
            ShoppingListText[i].setTextSize(25);
            ShoppingListText[i].setPadding(40,3,0,3);
        }
        updateText();
    }

    public void addItemClicked(View view) {
        Intent intent = new Intent(this, ListItemsActivity.class);

        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1){
            if(resultCode == RESULT_OK){
                String reply = data.getStringExtra("Item");
                itemsList.add(reply);
            }
        }
        updateText();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        if(!itemsList.isEmpty()){
            outState.putStringArrayList("Items", itemsList);
        }
    }

    public void updateText(){
        for(int i = 0; i < itemsList.size(); i++){
            ShoppingListText[i].setText(itemsList.get(i));
        }
    }
}
