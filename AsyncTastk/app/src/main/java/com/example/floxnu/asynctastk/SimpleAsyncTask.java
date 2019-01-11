package com.example.floxnu.asynctastk;

import android.os.AsyncTask;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.Random;

public class SimpleAsyncTask extends AsyncTask<Void, Integer, String> {

    private WeakReference<TextView> mTextView;
    private WeakReference<ProgressBar> mProgressBar;

    public SimpleAsyncTask(TextView tv, ProgressBar pb){
        mTextView = new WeakReference<>(tv);
        mProgressBar = new WeakReference<>(pb);
    }

    @Override
    protected String doInBackground(Void... voids) {
        Random r = new Random();

        int n = r.nextInt(11);

        int t = n * 200;

        try{
            for(int i = 0; i<10; i++){
                Thread.sleep(t/10);
                publishProgress(i*10);
            }
        } catch (InterruptedException e){
            e.printStackTrace();
        }


        return "Awake at last after sleeping for " + t + " milliseconds!";
    }

    @Override
    protected void onPostExecute(String s) {
        mTextView.get().setText(s);
        mProgressBar.get().setProgress(100);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        mProgressBar.get().setProgress(values[0]);
    }
}
