package com.example.trolley;

import android.util.JsonWriter;

import com.google.gson.JsonArray;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Fetch {

    public void fetchItem(double barcode) {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.woolworths.com.au/apis/ui/Search/products/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();

        ItemService itemService = retrofit.create(ItemService.class);
        WoolworthsPostBodyModel modal = new WoolworthsPostBodyModel("apples");
        Call<JsonArray> callSync = itemService.getItems(modal);
        //TODO Need to figure out what's going wrong, passing a WoolworthsPostBodyModel as the body and expecting the response to be given in JSON format, then we can extract the details

//        try {
//            Response<JsonArray> response = callSync.execute();
//        }
    }

    public ArrayList<Item> searchTerm(String term) {
     ArrayList<Item> list = new ArrayList<>();
//     URL url = new URL("");

        //     Testing values
        Item apples = new Item("apples", 116969, 20.00);
        list.add(apples);
        return list;
    }

    public GitUser gitUser() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();

        UserService service = retrofit.create(UserService.class);
        Call<GitUser> callSync = service.getUser("eugenp");

        GitUser user;
        try {
            Response<GitUser> response = callSync.execute();
            user = response.body();
        } catch (Exception ex) {
            user = new GitUser();
        };
        return user;

    }





}
