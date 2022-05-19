package com.example.trolley;


import static java.security.AccessController.getContext;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
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

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeHolder> {
    private Item[] items;
    private Context context;

    public HomeAdapter(Item[] items, Context context){
        this.items = items;
        this.context = context;
    }

    public class HomeHolder extends RecyclerView.ViewHolder {
        private final TextView name;
        private final ImageView image;
        private final View storeColor;
        private final TextView dollarPrice;
        private final TextView centPrice;
        private final TextView cupPrice;
        private final Button btn;
        private final ConstraintLayout container;


        public HomeHolder(View view) {
            super(view);
            name = view.findViewById(R.id.itemName);
            image = view.findViewById(R.id.itemImage);
            storeColor = view.findViewById(R.id.storeColor);
            dollarPrice = view.findViewById(R.id.bestPriceDollars);
            centPrice = view.findViewById(R.id.bestPriceCents);
            cupPrice = view.findViewById(R.id.cupPrice);
            btn = view.findViewById(R.id.addToListBtn);
            container = view.findViewById(R.id.itemContainer);
        }
        public ImageView getImage() {return image;}
    }

    @NonNull
    @Override
    public HomeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_home_layout, parent, false);
        return new HomeHolder(itemView);
    }

    // Bind item information into the dynamic list using the adaptor
    @Override
    public void onBindViewHolder(@NonNull HomeHolder holder, final int position) {
        // Item name
        holder.name.setText(items[position].getName());

        holder.btn.setOnClickListener(addToList(items[position]));
        // TODO Need to make this listener trigger the activity switch to ItemInfoScreen
        holder.container.setOnClickListener(moreInfo(items[position]));



        DecimalFormat df = new DecimalFormat("#.00");
        String priceString;

        //TODO An attempt to stop -1 prices from leaking into the results. Need to investigate.
        while (!items[position].isColesDone() && !items[position].isWoolworthsDone()){

        }
        if (items[position].isColesCheaper()) { // Should show coles version
            // Image
            holder.image.setImageBitmap(items[position].getColesImage());

            // Store color
            holder.storeColor.setBackgroundResource(R.drawable.layout_bg_store_indicator_coles);


            // Price
            priceString = df.format(items[position].getColesPrice());
//            int index = priceString.indexOf(".");
//            holder.dollarPrice.setText(priceString.substring(0, index));
//            holder.centPrice.setText(priceString.substring(index));

            // Cup price
            if (items[position].isWoolworthsHasCupPrice()) {
                holder.cupPrice.setText(items[position].getWoolworthsCupPrice());
            }
        } else { // Should show woolworths version
            // Image
            holder.image.setImageBitmap(items[position].getWooliesImage());

            // Store color
            holder.storeColor.setBackgroundResource(R.drawable.layout_bg_store_indicator_woolworths);

            // Price
            priceString = df.format(items[position].getWoolworthsPrice());

            // Cup price
            if (items[position].isWoolworthsHasCupPrice()) {
                holder.cupPrice.setText(items[position].getWoolworthsCupPrice());
            }
        }

        if (items[position].getIsAtWoolworths()) {
            holder.image.setImageBitmap(items[position].getWooliesImage());
        }

        // TODO Something is going wrong here, the prices are getting blanked out
        int index = priceString.indexOf(".");
        holder.dollarPrice.setText(priceString.substring(0, index));
        holder.centPrice.setText(priceString.substring(index));

    }

    public View.OnClickListener addToList(Item item) {
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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


    private View.OnClickListener moreInfo(Item item) {
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("Item", "Clicked");
                Intent intent = new Intent(context, ItemInfoScreen.class);
                MainActivity.moreInfoItem = item;
                context.startActivity(intent);
            }
        };
        return listener;
    }

    @Override
    public int getItemCount() {
        return items.length;
    }


}
