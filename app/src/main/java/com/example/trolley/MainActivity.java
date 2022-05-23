package com.example.trolley;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Objects;


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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.app_bar_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setQueryHint("Search for items");

        searchView.setOnQueryTextListener(
                new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String s) {
                        Log.i("Search", "starting a search");
                        Fetch fetch = new Fetch();
                        fetch.threadSearch(s, true, true);
                        searchView.clearFocus();
                        return true;
                    }

                    @Override
                    public boolean onQueryTextChange(String s) {
                        Log.i("Search", "typing...");
                        return true;
                    }
                }
        );
        return true;
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

    // Initiate data collection
    private void setItemData() {
        Fetch fetch = new Fetch();
        fetch.threadSearch("vegan", true, true);
    }

}