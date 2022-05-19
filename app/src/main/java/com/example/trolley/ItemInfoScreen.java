package com.example.trolley;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
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
        DecimalFormat df = new DecimalFormat("#.00");
        String priceString;
        int index;
        // Item name
        TextView wooliesName = findViewById(R.id.moreInfoNameWoolies);
        TextView colesName = findViewById(R.id.moreInfoNameColes);
        wooliesName.setText(item.getName());
        colesName.setText(item.getName());

        // Item image
        ImageView image = findViewById(R.id.moreInfoImage);

        // Coles and woolies containers
        View coles = findViewById(R.id.moreInfoColesContainer);
        View woolies = findViewById(R.id.moreInfoWooliesContainer);

        if (item.getIsAtColes()) {
            // Coles price
            TextView colesPriceDollar = findViewById(R.id.moreInfoDollarColes);
            TextView colesPriceCent = findViewById(R.id.moreInfoPriceCentColes);
            TextView colesWasPrice = findViewById(R.id.moreInfoWasPriceColes);


            priceString = df.format(item.getColesPrice());
            index = priceString.indexOf(".");
            colesPriceDollar.setText("$"+ priceString.substring(0, index));
            colesPriceCent.setText(priceString.substring(index));
            colesWasPrice.setText("Was " + item.getColesWasPrice());

            image.setImageBitmap(item.getColesImage());

            coles.setOnClickListener(colesListListener());
        } else {
            ((ViewGroup) coles.getParent()).removeView(coles);
        }

        if (item.getIsAtWoolworths()) {
            // Woolies price
            TextView wooliesPriceDollar = findViewById(R.id.moreInfoDollarWoolies);
            TextView wooliesPriceCent = findViewById(R.id.moreInfoPriceCentWoolies);
            TextView wooliesWasPrice = findViewById(R.id.moreInfoWasPriceWoolies);
            priceString = df.format(item.getWoolworthsPrice());
            index = priceString.indexOf(".");
            wooliesPriceDollar.setText("$"+ priceString.substring(0, index));
            wooliesPriceCent.setText(priceString.substring(index));
            wooliesWasPrice.setText("Was " + item.getWoolworthsWasPrice());

            image.setImageBitmap(item.getWooliesImage());

            woolies.setOnClickListener(wooliesListListener());
        } else {
            ((ViewGroup) woolies.getParent()).removeView(woolies);
        }
    }

    private View.OnClickListener wooliesListListener() {
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.listItemsWoolies.add(MainActivity.moreInfoItem);
            }
        };
        return listener;
    }

    private View.OnClickListener colesListListener() {
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.listItemsColes.add(MainActivity.moreInfoItem);
            }
        };
        return listener;
    }
}