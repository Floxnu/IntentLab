package com.example.floxnu.recipehomework.Recipes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.floxnu.recipehomework.R;

public class RecipeActivity extends AppCompatActivity {

    TextView mRecipeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_layout);

        mRecipeText = findViewById(R.id.recipe_text);

        Intent intent = getIntent();

        if(intent!=null){
            int recipeIndex = intent.getIntExtra("index", 0);
            mRecipeText.setText((getResources().getStringArray(R.array.recipe_text)[recipeIndex]));
        }
    }
}
