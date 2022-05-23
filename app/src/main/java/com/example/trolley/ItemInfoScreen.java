package com.example.trolley;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;

public class ItemInfoScreen extends AppCompatActivity {
    public Item item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_info_screen);
        item = MainActivity.moreInfoItem;
        injectFields();
    }

    private void injectFields() {
        DecimalFormat df = new DecimalFormat("0.00");
        String priceString;
        int index;
        // Item name
        TextView wooliesName = findViewById(R.id.moreInfoNameWoolies);
        TextView colesName = findViewById(R.id.moreInfoNameColes);
        wooliesName.setText(item.getName());
        colesName.setText(item.getName());

        // Item image
        ImageView image = findViewById(R.id.moreInfoImage);

        // Favourite btn
        Button favouriteBtn = findViewById(R.id.moreInfoFavouriteBtn);
        if (MainActivity.favouriteItems.contains(item)) {
            favouriteBtn.setBackgroundResource(R.drawable.ic_favourites_btn);
        }
        favouriteBtn.setOnClickListener(favouriteBtnListener());

        // Coles and woolies containers
        View coles = findViewById(R.id.moreInfoColesContainer);
        View woolies = findViewById(R.id.moreInfoWooliesContainer);

        // Fill coles info if at coles, else delete coles view
        if (item.getIsAtColes()) {
            // Coles price
            TextView colesPriceDollar = findViewById(R.id.moreInfoDollarColes);
            TextView colesPriceCent = findViewById(R.id.moreInfoPriceCentColes);
            TextView colesWasPrice = findViewById(R.id.moreInfoWasPriceColes);
            Button addToList = findViewById(R.id.colesAddToList);


            // Already added to list
            if (item.isOnColesList()) {
                addToList.setBackgroundResource(R.drawable.ic_list_btn_tick);
            }
            priceString = df.format(item.getColesPrice());
            index = priceString.indexOf(".");
            colesPriceDollar.setText("$"+ priceString.substring(0, index));
            colesPriceCent.setText(priceString.substring(index));
            if (item.isColesOnSpecial()) {
                colesWasPrice.setText("Was $" + df.format(item.getColesWasPrice()));
            }

            image.setImageBitmap(item.getColesImage());

            coles.setOnClickListener(colesListListener());
            addToList.setOnClickListener(colesListListener());
        } else {
            ((ViewGroup) coles.getParent()).removeView(coles);
        }

        // Fill woolies info if at woolies, else delete woolies view
        if (item.getIsAtWoolworths()) {
            // Woolies price
            TextView wooliesPriceDollar = findViewById(R.id.moreInfoDollarWoolies);
            TextView wooliesPriceCent = findViewById(R.id.moreInfoPriceCentWoolies);
            TextView wooliesWasPrice = findViewById(R.id.moreInfoWasPriceWoolies);
            Button addToList = findViewById(R.id.wooliesAddToList);

            // Already added to list
            if (item.isOnColesList()) {
                addToList.setBackgroundResource(R.drawable.ic_list_btn_tick);
            }
            priceString = df.format(item.getWoolworthsPrice());
            index = priceString.indexOf(".");
            wooliesPriceDollar.setText("$"+ priceString.substring(0, index));
            wooliesPriceCent.setText(priceString.substring(index));
            if (item.isWooliesOnSpecial()) {
                wooliesWasPrice.setText("Was $" + df.format(item.getWoolworthsWasPrice()));
            }

            image.setImageBitmap(item.getWooliesImage());

            woolies.setOnClickListener(wooliesListListener());
            addToList.setOnClickListener(wooliesListListener());
        } else {
            ((ViewGroup) woolies.getParent()).removeView(woolies);
        }
    }

    private View.OnClickListener favouriteBtnListener() {
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item.setFavourite(true);
                MainActivity.favouriteItems.add(item);
                findViewById(R.id.moreInfoFavouriteBtn)
                        .setBackgroundResource(R.drawable.ic_favourites_btn);
            }
        };
        return listener;
    }

    private View.OnClickListener wooliesListListener() {
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item.setOnWooliesList(true);
                MainActivity.listItemsWoolies.add(item);
                findViewById(R.id.wooliesAddToList)
                        .setBackgroundResource(R.drawable.ic_list_btn_tick);
            }
        };
        return listener;
    }

    private View.OnClickListener colesListListener() {
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item.setOnColesList(true);
                MainActivity.listItemsColes.add(item);
                findViewById(R.id.colesAddToList)
                        .setBackgroundResource(R.drawable.ic_list_btn_tick);
            }
        };
        return listener;
    }
}