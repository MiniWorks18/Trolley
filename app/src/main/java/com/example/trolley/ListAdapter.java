package com.example.trolley;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class ListAdapter  extends RecyclerView.Adapter<ListAdapter.ListHolder> {
    private Item[] items;

    public ListAdapter(Item[] items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_layout, parent, false);
        return new ListHolder(itemView);
    }

    // TODO make a secon adapter, one for coles, one for woolies. Otherwise all list items are being added once to coles and once again to woolies lists
    @Override
    public void onBindViewHolder(@NonNull ListHolder holder, int position) {
        if (items[position].isOnColesList()) { // Should buy from coles
            holder.name.setText(items[position].getName());
            holder.price.setText(String.valueOf(items[position].getColesPrice()));
            holder.image.setImageBitmap(items[position].getColesImage());
            holder.storeColor.setBackgroundResource(R.drawable.layout_bg_border_coles);
            holder.container.setBackgroundResource(R.drawable.layout_bg_list_store_indicator_coles);
        }
        if (items[position].isOnWooliesList()) { // Should buy from woolies
            holder.name.setText(items[position].getName());
            holder.price.setText(String.valueOf(items[position].getWoolworthsPrice()));
            holder.image.setImageBitmap(items[position].getWooliesImage());
            holder.storeColor.setBackgroundResource(R.drawable.layout_bg_border_woolies);
            holder.container.setBackgroundResource(R.drawable.layout_bg_list_store_indicator_woolies);
        }

    }

    @Override
    public int getItemCount() {
        return items.length;
    }

    public class ListHolder extends RecyclerView.ViewHolder {
        private final TextView name;
        private final ImageView image;
        private final TextView price;
        private final View storeColor;
        private final ConstraintLayout container;

        public ListHolder(View view) {
            super(view);
            this.name = view.findViewById(R.id.itemListName);
            this.image = view.findViewById(R.id.itemListImage);
            this.price = view.findViewById(R.id.itemListPrice);
            this.storeColor = view.findViewById(R.id.itemListBorder);
            this.container = view.findViewById(R.id.listItemContainer);
        }
    }
}
