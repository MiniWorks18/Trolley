package com.example.trolley;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;

public class FavouritesAdapter  extends RecyclerView.Adapter<FavouritesAdapter.ListHolder> {
    private Item[] items;
    DecimalFormat df = new DecimalFormat("0.00");
    String priceString;

    public FavouritesAdapter(Item[] items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_favourites_layout, parent, false);
        return new ListHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ListHolder holder, int position) {
        Item item = items[position];
        if (item.isOnWooliesList() || item.isOnColesList()) {
            holder.addToListBtn.setBackgroundResource(R.drawable.ic_list_btn_tick);
        }

        holder.container.setOnClickListener(addToList(item));
        holder.addToListBtn.setOnClickListener(addToList(item));

        if (item.isFavourite()) {
            if (item.isColesCheaper()) { // Show coles version
                holder.name.setText(item.getName());
                priceString = "$"+df.format(item.getColesPrice());
                if (item.isColesOnSpecial()) {
                    priceString = priceString+" Was $"+df.format(item.getColesWasPrice());
                    holder.container.setBackgroundResource(R.drawable.layout_bg_special_coles);
                } else {
                    holder.container.setBackgroundResource(R.drawable.layout_bg_list_store_indicator_coles);
                }
                holder.price.setText(priceString);
                holder.image.setImageBitmap(item.getColesImage());
                holder.storeColor.setBackgroundResource(R.drawable.layout_bg_border_coles);
            } else { // Show woolies version
                holder.name.setText(item.getName());

                priceString = "$"+df.format(item.getWoolworthsPrice());
                if (item.isWooliesOnSpecial()) {
                    priceString = priceString+" Was $"+df.format(item.getWoolworthsWasPrice());
                    holder.container.setBackgroundResource(R.drawable.layout_bg_special_woolies);
                } else {
                    holder.container.setBackgroundResource(R.drawable.layout_bg_list_store_indicator_woolies);
                }
                holder.price.setText(priceString);
                holder.image.setImageBitmap(item.getWooliesImage());
                holder.storeColor.setBackgroundResource(R.drawable.layout_bg_border_woolies);
            }

            // Display woolies item for coles
            if (item.getIsAtWoolworths()) {
                holder.image.setImageBitmap(item.getWooliesImage());
            }
        }

    }

    private View.OnClickListener addToList(Item item) {
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.findViewById(R.id.favouritesAddToListBtn)
                        .setBackgroundResource(R.drawable.ic_list_btn_tick);
                if (item.isColesCheaper()) {
                    item.setOnColesList(true);
                    MainActivity.listItemsColes.add(item);
                } else {
                    item.setOnWooliesList(true);
                    MainActivity.listItemsWoolies.add(item);
                }
            }
        };
        return listener;
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
        private final Button addToListBtn;

        public ListHolder(View view) {
            super(view);
            this.name = view.findViewById(R.id.itemListName);
            this.image = view.findViewById(R.id.itemListImage);
            this.price = view.findViewById(R.id.itemListPrice);
            this.storeColor = view.findViewById(R.id.itemListBorder);
            this.container = view.findViewById(R.id.listItemContainer);
            this.addToListBtn = view.findViewById(R.id.favouritesAddToListBtn);
        }
    }
}