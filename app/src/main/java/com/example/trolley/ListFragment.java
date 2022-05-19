package com.example.trolley;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ListFragment extends Fragment {
//    Item[] items = new Item[];

    private static RecyclerView recyclerColes;
    private static RecyclerView recyclerWoolies;

    public ListFragment() {
        // Do nothing
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_fragment, container, false);
        recyclerColes = view.findViewById(R.id.listRecyclerColes);
        recyclerWoolies = view.findViewById(R.id.listRecyclerWoolies);

        setLists();
        setAdapter();
        return view;
    }

    private void setAdapter() {
        ListAdapter colesAdapter = new ListAdapter(MainActivity.listItemsColes.toArray(new Item[0]));
        ListAdapter wooliesAdapter = new ListAdapter(MainActivity.listItemsWoolies.toArray(new Item[0]));
        RecyclerView.LayoutManager layoutManagerColes = new LinearLayoutManager(requireActivity().
                getApplicationContext()) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        RecyclerView.LayoutManager layoutManagerWoolies = new LinearLayoutManager(requireActivity().
                getApplicationContext()) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };

        recyclerColes.setLayoutManager(layoutManagerColes);
        recyclerWoolies.setLayoutManager(layoutManagerWoolies);
        recyclerColes.setItemAnimator(new DefaultItemAnimator());
        recyclerWoolies.setItemAnimator(new DefaultItemAnimator());
        recyclerColes.setAdapter(colesAdapter);
        recyclerWoolies.setAdapter(wooliesAdapter);
    }

    private void setLists() {
        // Do nothing
    }
}