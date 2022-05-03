package com.example.trolley;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private Item item = new Item("steven");
    public static Item[] searchedItems = new Item[1];

    private static RecyclerView recyclerView;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerViewer);

        setItemData();
        setAdapter();
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
        searchedItems[0] = item;
        Fetch fetch = new Fetch();

        fetch.searchThread("Frozen");
//        items = new Item[1];
//        items[0] = new Item("Corn chips");
    }

}