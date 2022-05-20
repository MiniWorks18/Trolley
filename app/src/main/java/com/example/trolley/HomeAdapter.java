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
    private volatile Item[] items;
    private volatile Context context;

    public HomeAdapter(Item[] items, Context context){
        this.items = items;
        this.context = context;
    }

    public class HomeHolder extends RecyclerView.ViewHolder {
        private volatile TextView name;
        private volatile ImageView image;
        private volatile View storeColor;
        private volatile TextView dollarPrice;
        private volatile TextView centPrice;
        private volatile TextView cupPrice;
        private volatile Button btn;
        private volatile ConstraintLayout container;
        private volatile TextView wasPrice;


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
            wasPrice = view.findViewById(R.id.homeWasPrice);
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



        DecimalFormat df = new DecimalFormat("0.00");
        String priceString;

        while (!items[position].isColesDone() && !items[position].isWoolworthsDone()){

        }
        Item item = items[position];
        if (item.isColesCheaper()) { // Should show coles version
            // Image
            holder.image.setImageBitmap(item.getColesImage());

            // Store color
            holder.storeColor.setBackgroundResource(R.drawable.layout_bg_store_indicator_coles);

            // On special or not
            if (item.isColesOnSpecial()) {
                // TODO There are weird issues that cause coles items to be label on special even though this is commented out
                // TODO There is also a strange behaviour where "was" prices are wrong and sometimes less than the listed price
//                holder.container.setBackgroundResource(R.drawable.layout_bg_special);
//                holder.wasPrice.setText("Was $"+df.format(item.getColesWasPrice()));
            }

            // Price
            priceString = df.format(item.getColesPrice());
//            int index = priceString.indexOf(".");
//            holder.dollarPrice.setText(priceString.substring(0, index));
//            holder.centPrice.setText(priceString.substring(index));

            // Cup price
            if (item.isWoolworthsHasCupPrice()) {
                holder.cupPrice.setText(item.getWoolworthsCupPrice());
            }
        } else { // Should show woolworths version
            // Image
            holder.image.setImageBitmap(item.getWooliesImage());

            // Store color
            holder.storeColor.setBackgroundResource(R.drawable.layout_bg_store_indicator_woolworths);

            // On special or not
            if (item.isWooliesOnSpecial()) {
                holder.container.setBackgroundResource(R.drawable.layout_bg_special);
                holder.wasPrice.setText("Was $"+df.format(item.getWoolworthsWasPrice()));
            }

            // Price
            priceString = df.format(item.getWoolworthsPrice());

            // Cup price
            if (item.isWoolworthsHasCupPrice()) {
                holder.cupPrice.setText(item.getWoolworthsCupPrice());
            }
        }

        // Display woolies item for coles
        if (item.getIsAtWoolworths()) {
            holder.image.setImageBitmap(item.getWooliesImage());
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
