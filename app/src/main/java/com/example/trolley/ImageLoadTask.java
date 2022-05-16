package com.example.trolley;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ImageLoadTask extends AsyncTask<Void, Void, Bitmap> {

    private String url;
    private Item item;
    private boolean colesImage;

    public ImageLoadTask(String url, Item item, boolean colesImage) {
        this.item = item;
        this.colesImage = colesImage;
        if (colesImage) {
            //TODO a lot of coles URLs are turning up null
            this.url = "https://shop.coles.com.au"+url;
        } else {
            this.url = url;
        }
    }

    @Override
    protected Bitmap doInBackground(Void... params) {
        if (url != null) {
            try {
                URL urlConnection = new URL(url);
                HttpURLConnection connection = (HttpURLConnection) urlConnection
                        .openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input = connection.getInputStream();
                Bitmap myBitmap = BitmapFactory.decodeStream(input);
                return myBitmap;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(Bitmap result) {
        super.onPostExecute(result);
        if (colesImage) {
            item.setColesImage(result);
        } else {
            item.setWooliesImage(result);
        }
    }

}
