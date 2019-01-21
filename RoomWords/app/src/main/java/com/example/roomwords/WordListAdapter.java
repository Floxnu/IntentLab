package com.example.roomwords;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordViewHolder> {

    private LayoutInflater mInflater;
    private List<Word> mWords;


    WordListAdapter(Context context){
        mInflater = LayoutInflater.from(context);

    }

    public void setWords(List<Word> words) {
        this.mWords = words;
    }

    @NonNull
    @Override
    public WordViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, viewGroup, false);
        return new WordViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull WordViewHolder wordViewHolder, int i) {
        if(mWords != null){
            Word current = mWords.get(i);
            wordViewHolder.wordItemView.setText(current.getWord());
        }

    }

    @Override
    public int getItemCount() {
        if(mWords != null){
            return mWords.size();
        }
        return 0;
    }



    class WordViewHolder extends RecyclerView.ViewHolder{

        public TextView wordItemView;

        private WordViewHolder(View itemView){
            super(itemView);
            wordItemView = itemView.findViewById(R.id.wordTextView);
        }

    }

}
