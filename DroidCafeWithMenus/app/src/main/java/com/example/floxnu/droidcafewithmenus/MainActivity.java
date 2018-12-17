package com.example.floxnu.droidcafewithmenus;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    String mOrderMessage;
    public static final String EXTRA_MESSAGE =
            "com.example.android.droidcafe.extra.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();


        switch (id){
            case R.id.action_order:
                Intent intent = new Intent(MainActivity.this, OrderActivity.class);
                intent.putExtra(EXTRA_MESSAGE, mOrderMessage);
                startActivity(intent);
                return true;
            case R.id.action_contact:
                makeToast(getString(R.string.action_contact_message));
                return true;
            case R.id.action_favourites:
                makeToast(getString(R.string.action_favorites_message));
                return true;
            case R.id.action_status:
                makeToast(getString(R.string.action_status_message));
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void makeToast(String message){
        mOrderMessage = message;
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void froyoClick(View view) {
        makeToast(getString(R.string.froyo_order_message));
    }

    public void iceCreamClick(View view) {
        makeToast(getString(R.string.ice_cream_order_message));
    }

    public void donutClick(View view) {
        makeToast(getString(R.string.donut_order_message));
    }

    public void fabClick(View view) {
        Intent intent = new Intent(this, OrderActivity.class);
        intent.putExtra(EXTRA_MESSAGE, mOrderMessage);
        startActivity(intent);

    }
}
