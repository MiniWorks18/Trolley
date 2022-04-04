package com.example.trolley;

import android.util.Log;

import com.android.volley.toolbox.HttpResponse;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Fetch {

    public void newFetchItem(String barcode) {
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\n\t\"IsSpecial\": false,\n\t\"Location\": \"/shop/search/products?searchTerm=frozen\",\n\t\"PageNumber\": 1,\n\t\"PageSize\": 24,\n\t\"SearchTerm\": \"frozen\",\n\t\"SortType\": \"TraderRelevance\"\n}");
        Request request = new Request.Builder()
                .url("https://www.woolworths.com.au/apis/ui/Search/products")
                .post(body)
                .addHeader("cookie", "akaalb_woolworths.com.au=~op%3Dwww_woolworths_com_au_ZoneAandC%3APROD-ZoneC%7C~rv%3D10~m%3DPROD-ZoneC%3A0%7C~os%3D43eb3391333cc20efbd7f812851447e6~id%3Dca7997d0f95fd2955be7c8ab2c06376c; _abck=4D008D2225FDB4B0E808F320484544EB~-1~YAAQn9%2F3SNiGD%2B1%2FAQAAR9%2Fr8gdFOxIbk2cOALniZKW75IvbwXXjvt%2Bstubp0FtUeTkadtunWSsyFLWJe0TmIQAguFcvVo%2BaL1RrKsx3AMYzr1XFj3C9ULafjLQrFKqGLuRVJaN6igR7INRHrzDH7XC2Iry4BPXomUHRt3qHbUpJcx%2FCfX7FMFBAGCVqf%2F%2B3EH%2B9LuAX7r%2B5f%2BS5UYaJpNyzc8dlXr4%2FGGE94SHdG86ACCs3RoTJ2ildGXhBk4k4eaJiHKhxomhNzOE7AxQKHxmfjLIn1UHhZlnwxZbDMdZOx9cQ2Rn3txkDXG2HWJMiAA9Lj5Gyala2coIq56gpP4vCUT1vc6EL%2BOgnhOeuXut9Ki8h5%2FhjJU0cOqzDq5EQVixlm6eqNc3XHuhFcHE%3D~-1~-1~-1; INGRESSCOOKIE=1648595781.231.59.751502%7C5eeab2ea8cdefed6dbef0b52cd0ff0b3; w-rctx=eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJuYmYiOjE2NDkwNDgwMTgsImV4cCI6MTY0OTA1MTYxOCwiaWF0IjoxNjQ5MDQ4MDE4LCJpc3MiOiJXb29sd29ydGhzIiwiYXVkIjoid3d3Lndvb2x3b3J0aHMuY29tLmF1Iiwic2lkIjoiMCIsInVpZCI6IjE5NDg4MWFjLTY1ZTgtNDVkYi1hZTUzLTc1NDAyNWFhM2QxYiIsIm1haWQiOiIwIiwiYXV0IjoiU2hvcHBlciIsImF1YiI6IjAiLCJhdWJhIjoiMCIsIm1mYSI6IjEifQ.CYFwh6uV2P7v8SGUC14tTf9lMc-fdX_0qZnhgfB5hpRzrYPI2tAS4IM3yw8DQB9aNq0aQcYoopcAaRj2LNSw6v4ql2VaUeeJWYs9FSJ63-0kHoGeNMkk_38T67m5_LZ4GbgHJW0AILhROkI1nYPXLAujEc6HfEfbmWQtjlpLXNcjm2B1sC04ZPJPPARNM6IuRTzEZKE5NP6uwJUUI-X2RXSgXAyxLTXU_JOSBPuL1-olFLdWp45F1K43YXhMphkNQmKFTSDK3ntpv0iQYNdZD0qLaZ-0yN-toRzLu2lnE92nOVusnu8qe1IFqIGAJQ-wPcPxPaXIs6_UpQv0TvZ5GQ; wow-auth-token=eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJuYmYiOjE2NDkwNDgwMTgsImV4cCI6MTY0OTA1MTYxOCwiaWF0IjoxNjQ5MDQ4MDE4LCJpc3MiOiJXb29sd29ydGhzIiwiYXVkIjoid3d3Lndvb2x3b3J0aHMuY29tLmF1Iiwic2lkIjoiMCIsInVpZCI6IjE5NDg4MWFjLTY1ZTgtNDVkYi1hZTUzLTc1NDAyNWFhM2QxYiIsIm1haWQiOiIwIiwiYXV0IjoiU2hvcHBlciIsImF1YiI6IjAiLCJhdWJhIjoiMCIsIm1mYSI6IjEifQ.CYFwh6uV2P7v8SGUC14tTf9lMc-fdX_0qZnhgfB5hpRzrYPI2tAS4IM3yw8DQB9aNq0aQcYoopcAaRj2LNSw6v4ql2VaUeeJWYs9FSJ63-0kHoGeNMkk_38T67m5_LZ4GbgHJW0AILhROkI1nYPXLAujEc6HfEfbmWQtjlpLXNcjm2B1sC04ZPJPPARNM6IuRTzEZKE5NP6uwJUUI-X2RXSgXAyxLTXU_JOSBPuL1-olFLdWp45F1K43YXhMphkNQmKFTSDK3ntpv0iQYNdZD0qLaZ-0yN-toRzLu2lnE92nOVusnu8qe1IFqIGAJQ-wPcPxPaXIs6_UpQv0TvZ5GQ; prodwow-auth-token=eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJuYmYiOjE2NDkwNDgwMTgsImV4cCI6MTY0OTA1MTYxOCwiaWF0IjoxNjQ5MDQ4MDE4LCJpc3MiOiJXb29sd29ydGhzIiwiYXVkIjoid3d3Lndvb2x3b3J0aHMuY29tLmF1Iiwic2lkIjoiMCIsInVpZCI6IjE5NDg4MWFjLTY1ZTgtNDVkYi1hZTUzLTc1NDAyNWFhM2QxYiIsIm1haWQiOiIwIiwiYXV0IjoiU2hvcHBlciIsImF1YiI6IjAiLCJhdWJhIjoiMCIsIm1mYSI6IjEifQ.CYFwh6uV2P7v8SGUC14tTf9lMc-fdX_0qZnhgfB5hpRzrYPI2tAS4IM3yw8DQB9aNq0aQcYoopcAaRj2LNSw6v4ql2VaUeeJWYs9FSJ63-0kHoGeNMkk_38T67m5_LZ4GbgHJW0AILhROkI1nYPXLAujEc6HfEfbmWQtjlpLXNcjm2B1sC04ZPJPPARNM6IuRTzEZKE5NP6uwJUUI-X2RXSgXAyxLTXU_JOSBPuL1-olFLdWp45F1K43YXhMphkNQmKFTSDK3ntpv0iQYNdZD0qLaZ-0yN-toRzLu2lnE92nOVusnu8qe1IFqIGAJQ-wPcPxPaXIs6_UpQv0TvZ5GQ; dtCookie=v_4_srv_5_sn_3FD4A4D8AB14F0EAC249BD397DE6F604_perc_100000_ol_0_mul_1_app-3Af908d76079915f06_1_rcs-3Acss_0")
                .addHeader("content-type", "application/json")
                .addHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/99.0.4844.88 Safari/537.36")
                .build();

        try {
            okhttp3.Response response = client.newCall(request).execute();
            Log.i("Response", response.body().toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void fetchItem(String searchTerm) {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.woolworths.com.au/apis/ui/Search/products/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();

        ItemService itemService = retrofit.create(ItemService.class);
        WoolworthsPostBodyModel modal = new WoolworthsPostBodyModel(searchTerm);
        Call<WoolworthsResponseModel> callSync = itemService.getItems(modal, "application/json");
        //TODO Need to figure out what's going wrong, passing a WoolworthsPostBodyModel as the body and expecting the response to be given in JSON format, then we can extract the details


        callSync.enqueue(new Callback<WoolworthsResponseModel>() {
            @Override
            public void onResponse(Call<WoolworthsResponseModel> call, Response<WoolworthsResponseModel> response) {
                if (response.isSuccessful()) {
                    response.body();
                } else  {
                    Log.i("Error ResponseErrorBody", response.errorBody().toString());
                    Log.i("Error Response Raw", response.raw().toString());
                }
            }

            @Override
            public void onFailure(Call<WoolworthsResponseModel> call, Throwable t) {
                Log.i("Error", t.toString());
            }
        });
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
