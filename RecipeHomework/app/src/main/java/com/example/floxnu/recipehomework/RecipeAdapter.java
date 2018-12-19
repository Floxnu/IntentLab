package com.example.floxnu.recipehomework;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.floxnu.recipehomework.Recipes.Recipe;
import com.example.floxnu.recipehomework.Recipes.RecipeActivity;

import java.util.LinkedList;
import java.util.zip.Inflater;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeItemHolder> {

    LinkedList<Recipe> recipes;
    LayoutInflater mInflater;
    View.OnClickListener clickListener;

    public RecipeAdapter(LinkedList<Recipe> recipes_, final Context context, final RecyclerView parentRexycler){
        mInflater = LayoutInflater.from(context);
        recipes = recipes_;

        clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int activityIndex = parentRexycler.getChildAdapterPosition(v);

                Intent intent = new Intent(context, RecipeActivity.class);
                intent.putExtra("index", activityIndex);

                context.startActivity(intent);
            }
        };
    }

    @NonNull
    @Override
    public RecipeItemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = mInflater.inflate(R.layout.list_item, viewGroup, false);
        view.setOnClickListener(clickListener);

        return new RecipeItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeItemHolder recipeItemHolder, int i) {
        recipeItemHolder.mRecipeName.setText(recipes.get(i).getRecipeName());
        recipeItemHolder.mRecipeDescription.setText(recipes.get(i).getRecipeDescription());
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }


    public class RecipeItemHolder extends RecyclerView.ViewHolder{
        TextView mRecipeName;
        TextView mRecipeDescription;

        public RecipeItemHolder(@NonNull View itemView) {
            super(itemView);
            mRecipeName = itemView.findViewById(R.id.recipe_name);
            mRecipeDescription = itemView.findViewById(R.id.recipe_description);
        }
    }
}
