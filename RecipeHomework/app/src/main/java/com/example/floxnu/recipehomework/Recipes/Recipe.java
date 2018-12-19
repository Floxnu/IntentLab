package com.example.floxnu.recipehomework.Recipes;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v4.content.res.ResourcesCompat;

import com.example.floxnu.recipehomework.R;

public abstract class Recipe {
    String recipeName;
    String recipeDescription;
    Drawable recipeImage;
    String recipeText;

    public Recipe(int indexOfRecipe, Activity current){

        recipeName = (current.getResources().getStringArray(R.array.recipe_names))[indexOfRecipe];
        recipeDescription = (current.getResources().getStringArray(R.array.recipe_description))[indexOfRecipe];
        recipeText = (current.getResources().getStringArray(R.array.recipe_text))[indexOfRecipe];
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public String getRecipeDescription() {
        return recipeDescription;
    }

    public void setRecipeDescription(String recipeDescription) {
        this.recipeDescription = recipeDescription;
    }

    public Drawable getRecipeImage() {
        return recipeImage;
    }

    public void setRecipeImage(Drawable recipeImage) {
        this.recipeImage = recipeImage;
    }

    public String getRecipeText() {
        return recipeText;
    }

    public void setRecipeText(String recipeText) {
        this.recipeText = recipeText;
    }
}
