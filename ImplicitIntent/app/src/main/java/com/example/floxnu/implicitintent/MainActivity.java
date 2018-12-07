package com.example.floxnu.implicitintent;


import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText mWebsiteEditText;
    EditText mLocationEditText;
    EditText mShareEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mWebsiteEditText = findViewById(R.id.web_text);
        mLocationEditText = findViewById(R.id.address_text);
        mShareEditText = findViewById(R.id.text_text);
    }

    public void openWebsite(View view) {

        String url = mWebsiteEditText.getText().toString();
        Uri website = Uri.parse(url);

        Intent intent = new Intent(Intent.ACTION_VIEW, website);

        if(intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        } else{
            Log.d(this.getClass().getSimpleName(), "Can't handle request.");
        }
    }

    public void openLocation(View view) {
        String loc = mLocationEditText.getText().toString();

        Uri addressUri = Uri.parse("geo:0,0?q=" + loc);

        Intent intent = new Intent(Intent.ACTION_VIEW, addressUri);

        if(intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        } else{
            Log.d(this.getClass().getSimpleName(), "Can't handle request.");
        }
    }

    public void shareText(View view) {

        String txt = mShareEditText.getText().toString();
        String mimeType = "text/plain";
        ShareCompat.IntentBuilder
                .from(this)
                .setType(mimeType   )
                .setChooserTitle("Share this text with:")
                .setText(txt)
                .startChooser();
    }

    public void takePhoto(View view) {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if(cameraIntent.resolveActivity(getPackageManager()) != null){
            startActivity(cameraIntent);
        }
    }
}
