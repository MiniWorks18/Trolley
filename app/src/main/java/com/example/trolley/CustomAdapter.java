package com.example.trolley;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    private Item[] items;

    public CustomAdapter(Item[] items){
        this.items = items;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textViewer;

        public ViewHolder(View view) {
            super(view);
            textViewer = view.findViewById(R.id.textView);
        }
        public TextView getTextViewer() {
            return textViewer;
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_items, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        String name = items[position].getName();
        holder.textViewer.setText(name);
    }

    @Override
    public int getItemCount() {
        return items.length;
    }


}
