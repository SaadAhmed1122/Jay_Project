package com.example.stockmanagment;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import java.security.PublicKey;

public class Home_Activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    public Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_);

        toolbar =(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_Close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.idItem1){
            Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
        } else if(id == R.id.idItem2){
            Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
        } else if(id == R.id.idItem3){
            Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
        } else if(id == R.id.item4){
            Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
        } else if(id == R.id.item5) {
            Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
        }
        DrawerLayout drawer43 = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer43.closeDrawer(GravityCompat.START);
        return true;
    }
}
