package com.example.floxnu.droidcafe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    String mOrderMessage;
    public static final String EXTRA_MESSAGE =
            "com.example.android.droidcafe.extra.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
