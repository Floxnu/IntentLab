package com.example.floxnu.droidcafe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class OrderActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        spinner = findViewById(R.id.phone_spinner);
        if(spinner!=null){
            spinner.setOnItemSelectedListener(this);
        }

        Intent intent = getIntent();

        String message = "Order: " + intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        TextView tv = findViewById(R.id.order_vieew);
        tv.setText(message);
    }

    public void displayToast(String message) {
        Toast.makeText(getApplicationContext(), message,
                Toast.LENGTH_SHORT).show();
    }

    public void radioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.sameday:
                if (checked)
                    displayToast("Same day messenger service");
                break;
            case R.id.nextday:
                if (checked)
                    displayToast("Next day ground delivery");
                break;
            case R.id.pickup:
                if (checked)
                    displayToast("Pick Up");
                break;
            default:
                break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String spinnerLabel = spinner.getItemAtPosition(position).toString();
        displayToast(spinnerLabel);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
