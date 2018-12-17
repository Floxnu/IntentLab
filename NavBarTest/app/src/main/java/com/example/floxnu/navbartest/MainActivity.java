package com.example.floxnu.navbartest;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.open,R.string.close);
        if(drawer != null){
            drawer.addDrawerListener(toggle);
        }
        toggle.syncState();

        NavigationView navView = findViewById(R.id.nav_view);
        navView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        DrawerLayout drawer = findViewById(R.id.drawer);

        switch (menuItem.getItemId()){
            case R.id.tools_:
                drawer.closeDrawer(GravityCompat.START);
                toast("Tools");
                return true;
            case R.id.gallery_:
                drawer.closeDrawer(GravityCompat.START);
                toast("Gallery");
                return true;
            case R.id.import_:
                drawer.closeDrawer(GravityCompat.START);
                toast("Import");
                return true;
            case R.id.slide_:
                drawer.closeDrawer(GravityCompat.START);
                toast("Slideshow");
                return true;
            case R.id.other_1:
            case R.id.other_2:
                drawer.closeDrawer(GravityCompat.START);
                toast("Other");
                return true;
        }
        return false;
    }

    public void toast(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
