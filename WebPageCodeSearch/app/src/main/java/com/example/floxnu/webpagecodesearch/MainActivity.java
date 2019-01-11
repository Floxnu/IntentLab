package com.example.floxnu.webpagecodesearch;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {

    EditText mURLText;
    TextView textDisplay;
    Spinner httpSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mURLText = findViewById(R.id.urlText);
        textDisplay = findViewById(R.id.pageText);
        httpSpinner = findViewById(R.id.spinner);

    }

    public void getSource(View view) {
        String queryString = null;

        int position = httpSpinner.getSelectedItemPosition();

        textDisplay.setText("Getting page...");

        if(position == 0){
            queryString = "http://";
        } else{
            queryString = "https://";
        }

        queryString += mURLText.getText().toString();

        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);

        if (inputManager != null ) {
            inputManager.hideSoftInputFromWindow(view.getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }

        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = null;
        if (connMgr != null) {
            networkInfo = connMgr.getActiveNetworkInfo();
        }

        if (networkInfo != null && networkInfo.isConnected()
                && queryString.length() != 0) {

            Bundle querryBundle = new Bundle();
            querryBundle.putString("queryString", queryString);
            getSupportLoaderManager().restartLoader(0, querryBundle, this);
        }
        else{

            if (queryString.length() == 0) {

                mURLText.setText("No Website entered");
            } else {

                mURLText.setText("No Network Found");
            }
        }
    }

    @NonNull
    @Override
    public Loader<String> onCreateLoader(int i, @Nullable Bundle bundle) {
        String queryString = "";

        if (bundle != null) {
            queryString = bundle.getString("queryString");
        }

        return new WebsiteLoader(this, queryString);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String s) {
        textDisplay.setText(s);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

    }
}
