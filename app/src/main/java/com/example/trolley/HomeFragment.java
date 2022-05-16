package com.example.trolley;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class HomeFragment extends Fragment {

//    public static Item[] searchedItems = new Item[1];
//    Item item = new Item("Steve");

    private static RecyclerView recyclerView;

    public HomeFragment(){
        // require a empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);
        recyclerView = view.findViewById(R.id.recyclerViewer);
//        setItemData();
        setAdapter();
//        searchedItems[0] = item;
        // Inflate the layout for this fragment
        return view;
    }

    public void setAdapter() {
        CustomAdapter adapter = new CustomAdapter(MainActivity.searchedItems);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(requireActivity().getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    public static void updateAdapter() {
        CustomAdapter adapter = new CustomAdapter(MainActivity.searchedItems);
        recyclerView.setAdapter(adapter);
    }

    private void setItemData() {
        Fetch fetch = new Fetch();

        fetch.threadSearch("vegetables", true, true);
    }
}