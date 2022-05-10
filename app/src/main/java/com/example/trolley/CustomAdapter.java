package com.example.trolley;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    private Item[] items;

    public CustomAdapter(Item[] items){
        this.items = items;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView name;
        private final ImageView image;
        private final View storeColor;
        private final TextView dollarPrice;
        private final TextView centPrice;
        private final TextView cupPrice;


        public ViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.itemName);
            image = view.findViewById(R.id.itemImage);
            storeColor = view.findViewById(R.id.storeColor);
            dollarPrice = view.findViewById(R.id.bestPriceDollars);
            centPrice = view.findViewById(R.id.bestPriceCents);
            cupPrice = view.findViewById(R.id.cupPrice);
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

    // Bind item information into the dynamic list using the adaptor
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        // Item name
        holder.name.setText(items[position].getName());

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

    @Override
    public int getItemCount() {
        return items.length;
    }


}
