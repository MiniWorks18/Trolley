package com.example.trolley;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;

public class ListAdapter  extends RecyclerView.Adapter<ListAdapter.ListHolder> {
    private Item[] items;
    DecimalFormat df = new DecimalFormat("0.00");
    String priceString;

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

    @Override
    public void onBindViewHolder(@NonNull ListHolder holder, int position) {
        Item item = items[position];
        if (item.isOnColesList()) { // Should buy from coles
            holder.name.setText(item.getName());
            priceString = "$"+df.format(item.getColesPrice());
            if (item.isColesOnSpecial()) {
                priceString = priceString+" Was $"+df.format(item.getColesWasPrice());
            }
            holder.price.setText(priceString);
            holder.image.setImageBitmap(item.getColesImage());
            holder.storeColor.setBackgroundResource(R.drawable.layout_bg_border_coles);
            holder.container.setBackgroundResource(R.drawable.layout_bg_list_store_indicator_coles);
        }
        if (item.isOnWooliesList()) { // Should buy from woolies
            holder.name.setText(item.getName());
            priceString = "$"+df.format(item.getWoolworthsPrice());
            if (item.isWooliesOnSpecial()) {
                priceString = priceString+" Was $"+df.format(item.getWoolworthsWasPrice());
            }
            holder.price.setText(priceString);
            holder.image.setImageBitmap(item.getWooliesImage());
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
