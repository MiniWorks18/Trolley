package com.example.trolley;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
        private final ImageView image;


        public ViewHolder(View view) {
            super(view);
            textViewer = view.findViewById(R.id.itemName);
            image = view.findViewById(R.id.itemImage);
        }
        public TextView getTextViewer() {
            return textViewer;
        }
        public ImageView getImage() {return image;}
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
        if (items[position].isColesCheaper()) {
            holder.image.setImageBitmap(items[position].getColesImage());
        } else {
            holder.image.setImageBitmap(items[position].getWooliesImage());
        }

        holder.textViewer.setText(name);

        // TODO Fill in appropriate data, try to make items look like they're from coles/woolies
    }

    @Override
    public int getItemCount() {
        return items.length;
    }


}
