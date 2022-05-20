package com.example.trolley;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.MenuItem;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    public static Item[] searchedItems = new Item[0];
    public static ArrayList<Item> listItemsColes = new ArrayList<>();
    public static ArrayList<Item> listItemsWoolies = new ArrayList<>();
    public static ArrayList<Item> favouriteItems = new ArrayList<>();
    public static Item moreInfoItem = new Item("");

    BottomNavigationView bottomNavigationView;
    HomeFragment homeFragment = new HomeFragment();
    ListFragment listFragment = new ListFragment();
    FavouritesFragment favouritesFragment = new FavouritesFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setItemData();
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

    public void moreInfoScreen(Item item) {
        Intent switchActivityIntent = new Intent(this, ItemInfoScreen.class);
        startActivity(switchActivityIntent);
    }

    // Initiate data collection
    private void setItemData() {
        Fetch fetch = new Fetch();
        fetch.threadSearch("tuna", true, true);
    }

}