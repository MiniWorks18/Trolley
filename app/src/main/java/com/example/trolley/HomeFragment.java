package com.example.trolley;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class HomeFragment extends Fragment implements View.OnClickListener {

//    public static Item[] searchedItems = new Item[1];
//    Item item = new Item("Steve");

    private static RecyclerView recyclerView;
    private static Button addToListBtn;

    public HomeFragment(){
        // require a empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the view
        View view = inflater.inflate(R.layout.home_fragment, container, false);
        recyclerView = view.findViewById(R.id.recyclerViewer);
        addToListBtn = view.findViewById(R.id.addToListBtn);
//        addToListBtn.setOnClickListener(this);

        setItemData();
        setAdapter();
        // Inflate the layout for this fragment
        return view;
    }

    // Adapter used to dynamically load item data into home view
    public void setAdapter() {
        HomeAdapter adapter = new HomeAdapter(MainActivity.searchedItems);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(requireActivity().getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    // Reload displayed data
    public static void updateAdapter() {
        HomeAdapter adapter = new HomeAdapter(MainActivity.searchedItems);
        recyclerView.setAdapter(adapter);
    }

    // Initiate data collection
    private void setItemData() {
        Fetch fetch = new Fetch();
        fetch.threadSearch("vegetables", true, true);
    }

    @Override
    public void onClick(View view) {
        Log.i("Home", "hello there");
    }
}