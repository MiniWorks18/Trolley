package com.example.trolley;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class FavouritesFragment extends Fragment {

    private static RecyclerView recyclerView;

    private Button addToListBtn;

    public FavouritesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.favourites_fragment, container, false);
        recyclerView = view.findViewById(R.id.recyclerViewer);
        setItemData();
        setAdapter();
        return view;
    }

    private void setAdapter() {
        FavouritesAdapter adapter = new FavouritesAdapter(MainActivity.favouriteItems.toArray(new Item[0]));
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(requireActivity()
                .getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    private void setItemData() {

    }
}