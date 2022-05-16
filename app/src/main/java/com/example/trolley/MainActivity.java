package com.example.trolley;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.MenuItem;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
//    private Item item = new Item("steven");
    public static Item[] searchedItems = new Item[0];

    private static RecyclerView recyclerView;
    BottomNavigationView bottomNavigationView;
    HomeFragment homeFragment = new HomeFragment();
    ListFragment listFragment = new ListFragment();
    FavouritesFragment favouritesFragment = new FavouritesFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        setContentView(R.layout.menu_lists);
//        recyclerView = findViewById(R.id.recyclerViewer);
        setContentView(R.layout.activity_main);

        // Open "home" fragment by default
        getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, homeFragment).commit();

        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener(this);
//        bottomNavigationView.setSelectedItemId(R.id.home1);

        setItemData();
//        setAdapter();
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

    public void setAdapter() {
        CustomAdapter adapter = new CustomAdapter(searchedItems);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    public static void updateAdapter() {
        CustomAdapter adapter = new CustomAdapter(searchedItems);
        recyclerView.setAdapter(adapter);
    }

    private void setItemData() {
        Fetch fetch = new Fetch();

        fetch.threadSearch("vegetables", true, true);
    }

}