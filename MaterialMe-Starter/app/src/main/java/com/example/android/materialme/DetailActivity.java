package com.example.android.materialme;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView image = findViewById(R.id.sports_imageDetail);
        TextView title = findViewById(R.id.titleDetail);
        TextView detailsText = findViewById(R.id.subTitleDetail);

        title.setText(getIntent().getStringExtra("title"));
        Glide.with(this).load(getIntent().getIntExtra("image", 0)).into(image);
        detailsText.setText(getIntent().getStringExtra("details"));
    }
}
