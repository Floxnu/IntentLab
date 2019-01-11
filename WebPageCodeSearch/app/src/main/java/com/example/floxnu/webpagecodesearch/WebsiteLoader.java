package com.example.floxnu.webpagecodesearch;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class WebsiteLoader extends AsyncTaskLoader<String> {

    String mURL;

    public WebsiteLoader(@NonNull Context context, String url) {
        super(context);
        mURL = url;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Nullable
    @Override
    public String loadInBackground() {

        HttpURLConnection httpURLConnection = null;
        HttpsURLConnection httpsURLConnection = null;
        BufferedReader reader = null;
        String pageSourceCode = null;

        try {

            Uri builtUri = Uri.parse(mURL);

            URL url = new URL(builtUri.toString());

            InputStream inStream;

            if(mURL.startsWith("https")){
                httpsURLConnection = (HttpsURLConnection) url.openConnection();
                httpsURLConnection.setRequestMethod("GET");
                httpsURLConnection.connect();
                inStream = httpsURLConnection.getInputStream();
            } else {
                httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.connect();
                inStream = httpURLConnection.getInputStream();
            }


            reader = new BufferedReader(new InputStreamReader(inStream));
            StringBuilder builder = new StringBuilder();

            String line;
            while((line = reader.readLine()) != null){
                builder.append(line);
            }

            if(builder.length() == 0){
                return null;
            }

            pageSourceCode = builder.toString();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }

            if(httpsURLConnection != null) {
                httpsURLConnection.disconnect();
            }

            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return pageSourceCode;
    }

}
