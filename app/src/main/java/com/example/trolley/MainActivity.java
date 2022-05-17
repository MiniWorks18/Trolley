package com.example.trolley;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.MenuItem;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    public static Item[] searchedItems = new Item[0];
    public static ArrayList<Item> listItems = new ArrayList<>();

    BottomNavigationView bottomNavigationView;
    HomeFragment homeFragment = new HomeFragment();
    ListFragment listFragment = new ListFragment();
    FavouritesFragment favouritesFragment = new FavouritesFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Open "home" fragment by default
        getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, homeFragment).commit();

        // Bottom navigation setup
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home_btn:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, homeFragment).commit();
                return true;
            case R.id.list_btn:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, listFragment).commit();
                return true;
            case R.id.favourites_btn:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, favouritesFragment).commit();
                return true;
        }
        return false;
    }

}