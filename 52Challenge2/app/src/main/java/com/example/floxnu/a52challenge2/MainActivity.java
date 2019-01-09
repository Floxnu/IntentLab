package com.example.floxnu.a52challenge2;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Explode;
import android.transition.Fade;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView toAnimate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        setContentView(R.layout.activity_main);

       toAnimate = findViewById(R.id.cloud);

    }


    public void explode(View view) {
        getWindow().setExitTransition(new Explode());
        getWindow().setEnterTransition(new Explode());

        Intent intent = new Intent(this, this.getClass());
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation((Activity) this).toBundle());
    }

    public void fade(View view) {
        getWindow().setExitTransition(new Fade());
        getWindow().setEnterTransition(new Fade());

        Intent intent = new Intent(this, this.getClass());
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation((Activity) this).toBundle());
    }

    public void inPlaceAnim(View view) {
        final ObjectAnimator animator = ObjectAnimator.ofFloat(view, View.ROTATION, 0f, 360f);
        animator.setDuration(500);
        animator.setRepeatMode(ValueAnimator.RESTART);
        animator.setRepeatCount(0);

        animator.start();

    }

    public void sharedElement(View view) {
        getWindow().setSharedElementEnterTransition();
    }
}
