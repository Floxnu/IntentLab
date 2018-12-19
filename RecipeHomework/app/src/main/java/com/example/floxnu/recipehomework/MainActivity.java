package com.example.floxnu.recipehomework;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.floxnu.recipehomework.Recipes.Cake;
import com.example.floxnu.recipehomework.Recipes.IceCream;
import com.example.floxnu.recipehomework.Recipes.Pancakes;
import com.example.floxnu.recipehomework.Recipes.Recipe;
import com.example.floxnu.recipehomework.Recipes.Sandwich;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    LinkedList<Recipe> recipeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recipeList = new LinkedList<>();
        recipeList.add(new Cake(this));
        recipeList.add(new IceCream(this));
        recipeList.add(new Pancakes(this));
        recipeList.add(new Sandwich(this));

        mRecyclerView = findViewById(R.id.recycler);
         mRecyclerView.setAdapter(new RecipeAdapter(recipeList, this, mRecyclerView));
         mRecyclerView.setLayoutManager(new LinearLayoutManager(this));


    }
}
