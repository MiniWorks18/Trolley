package com.example.trolley;

import android.util.Log;

import com.android.volley.toolbox.HttpResponse;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Fetch {

    public void newFetchItem(String barcode) {
//        OkHttpClient client = new OkHttpClient();
//
//        MediaType mediaType = MediaType.parse("application/json");
//        RequestBody body = RequestBody.create(mediaType, "{\n\t\"IsSpecial\": false,\n\t\"Location\": \"/shop/search/products?searchTerm=frozen\",\n\t\"PageNumber\": 1,\n\t\"PageSize\": 24,\n\t\"SearchTerm\": \"frozen\",\n\t\"SortType\": \"TraderRelevance\"\n}");
//        Request request = new Request.Builder()
//                .url("https://www.woolworths.com.au/apis/ui/Search/products")
//                .post(body)
//                .addHeader("content-type", "application/json")
//                .addHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/99.0.4844.88 Safari/537.36")
//                .build();


        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://www.woolworths.com.au/apis/ui/product/detail/205300?isMobile=false")
                .get()
                .build();

        try {
            okhttp3.Response response = client.newCall(request).execute();
            Log.i("Response", response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void fetchItem(String searchTerm) {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://www.woolworths.com.au/apis/ui/product/205300")
                .get()
                .addHeader("cookie", "akaalb_woolworths.com.au=~op%3Dwww_woolworths_com_au_ZoneAandC%3APROD-ZoneC%7C~rv%3D10~m%3DPROD-ZoneC%3A0%7C~os%3D43eb3391333cc20efbd7f812851447e6~id%3Dca7997d0f95fd2955be7c8ab2c06376c; _abck=4D008D2225FDB4B0E808F320484544EB~-1~YAAQn9%2F3SAnfbPt%2FAQAARhHJBgf0J%2FQD9mqhSENp8pfgOgo2Z5VkiukTSTwmHx%2FYH6EGRDJg9j88qjdJ8e7pCpm96sIOHqq7Uf6st3%2FAnGQB9slqQc9N1X8t6PdtPaiLj88CJYxWIFuyn44rXxhiaqH4aTOr1VeEDboftGNgbg5PQDEfn4T4HhhQD%2F1h8WIcJMGsJBW0KUOuKUNeT9oPII78ph3O36aq0LkekimwZRkGvqkEmB2KU8NRjC0dXcurQ4a7tjySLPLtBw79FKL3L1dN7vX6cdeaWDoXUHKshEycl4mEfI0whXbBjw5ONdrAdYLttePWo2NgvpVtpRPdbTgnfFCKAKOUbr6XKBTcrcCgHxo7847MviQvBD7Vs9pQ5076cUdmlZLj%2F04tvC4%3D~-1~-1~-1; INGRESSCOOKIE=1648595781.231.59.751502%7C5eeab2ea8cdefed6dbef0b52cd0ff0b3; w-rctx=eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJuYmYiOjE2NDkzNzk4MjQsImV4cCI6MTY0OTM4MzQyNCwiaWF0IjoxNjQ5Mzc5ODI0LCJpc3MiOiJXb29sd29ydGhzIiwiYXVkIjoid3d3Lndvb2x3b3J0aHMuY29tLmF1Iiwic2lkIjoiMCIsInVpZCI6IjU2MmQ0ZTQ1LWE3MGUtNDM0OS1hZDlkLWUwZWJkMzA3MWRkNCIsIm1haWQiOiIwIiwiYXV0IjoiU2hvcHBlciIsImF1YiI6IjAiLCJhdWJhIjoiMCIsIm1mYSI6IjEifQ.D33O2NKIFhIQ52KNVg_WmliSLB-bj7isK7gsSXb4rAl8vVHFwKVA8NtaO_DxFsg2yCgEs4Tf0aWhWatf4xkL63vdicmZDtJeeuEMBIkraIleq522v4QqkPaN9TjrZXExDWOVlrduSUpwohTWWFANCF0uCXVuToCbpJhoMGX3Lr2eX27W8VTGTmTc5bH-hMtM3ZFp_7lwyDRg5tpzEe17lxcsn1BIAt4VRtT4LsVvjExxg8IMZOkzBIk_3QCUAp-b579fukVDiRMC8kbFmM50Asc4KYiAFQ0YZKcgAgUxIq8CPdDztLoS-rJQU-V-LitNSFHrxJbq_vafrD4B7G2WSw; wow-auth-token=eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJuYmYiOjE2NDkzNzk4MjQsImV4cCI6MTY0OTM4MzQyNCwiaWF0IjoxNjQ5Mzc5ODI0LCJpc3MiOiJXb29sd29ydGhzIiwiYXVkIjoid3d3Lndvb2x3b3J0aHMuY29tLmF1Iiwic2lkIjoiMCIsInVpZCI6IjU2MmQ0ZTQ1LWE3MGUtNDM0OS1hZDlkLWUwZWJkMzA3MWRkNCIsIm1haWQiOiIwIiwiYXV0IjoiU2hvcHBlciIsImF1YiI6IjAiLCJhdWJhIjoiMCIsIm1mYSI6IjEifQ.D33O2NKIFhIQ52KNVg_WmliSLB-bj7isK7gsSXb4rAl8vVHFwKVA8NtaO_DxFsg2yCgEs4Tf0aWhWatf4xkL63vdicmZDtJeeuEMBIkraIleq522v4QqkPaN9TjrZXExDWOVlrduSUpwohTWWFANCF0uCXVuToCbpJhoMGX3Lr2eX27W8VTGTmTc5bH-hMtM3ZFp_7lwyDRg5tpzEe17lxcsn1BIAt4VRtT4LsVvjExxg8IMZOkzBIk_3QCUAp-b579fukVDiRMC8kbFmM50Asc4KYiAFQ0YZKcgAgUxIq8CPdDztLoS-rJQU-V-LitNSFHrxJbq_vafrD4B7G2WSw; prodwow-auth-token=eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJuYmYiOjE2NDkzNzk4MjQsImV4cCI6MTY0OTM4MzQyNCwiaWF0IjoxNjQ5Mzc5ODI0LCJpc3MiOiJXb29sd29ydGhzIiwiYXVkIjoid3d3Lndvb2x3b3J0aHMuY29tLmF1Iiwic2lkIjoiMCIsInVpZCI6IjU2MmQ0ZTQ1LWE3MGUtNDM0OS1hZDlkLWUwZWJkMzA3MWRkNCIsIm1haWQiOiIwIiwiYXV0IjoiU2hvcHBlciIsImF1YiI6IjAiLCJhdWJhIjoiMCIsIm1mYSI6IjEifQ.D33O2NKIFhIQ52KNVg_WmliSLB-bj7isK7gsSXb4rAl8vVHFwKVA8NtaO_DxFsg2yCgEs4Tf0aWhWatf4xkL63vdicmZDtJeeuEMBIkraIleq522v4QqkPaN9TjrZXExDWOVlrduSUpwohTWWFANCF0uCXVuToCbpJhoMGX3Lr2eX27W8VTGTmTc5bH-hMtM3ZFp_7lwyDRg5tpzEe17lxcsn1BIAt4VRtT4LsVvjExxg8IMZOkzBIk_3QCUAp-b579fukVDiRMC8kbFmM50Asc4KYiAFQ0YZKcgAgUxIq8CPdDztLoS-rJQU-V-LitNSFHrxJbq_vafrD4B7G2WSw; dtCookie=v_4_srv_5_sn_3FD4A4D8AB14F0EAC249BD397DE6F604_perc_100000_ol_0_mul_1_app-3Af908d76079915f06_1_rcs-3Acss_0")
                .build();

        try {
            okhttp3.Response response = client.newCall(request).execute();
            Log.i("Response", response.body().string());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Item fetchWooliesItemByCode(long code) {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://www.woolworths.com.au/apis/ui/product/"+code)
                .get()
                .addHeader("cookie", "akaalb_woolworths.com.au=~op%3Dwww_woolworths_com_au_ZoneAandC%3APROD-ZoneC%7C~rv%3D10~m%3DPROD-ZoneC%3A0%7C~os%3D43eb3391333cc20efbd7f812851447e6~id%3Dca7997d0f95fd2955be7c8ab2c06376c; _abck=4D008D2225FDB4B0E808F320484544EB~-1~YAAQn9%2F3SAnfbPt%2FAQAARhHJBgf0J%2FQD9mqhSENp8pfgOgo2Z5VkiukTSTwmHx%2FYH6EGRDJg9j88qjdJ8e7pCpm96sIOHqq7Uf6st3%2FAnGQB9slqQc9N1X8t6PdtPaiLj88CJYxWIFuyn44rXxhiaqH4aTOr1VeEDboftGNgbg5PQDEfn4T4HhhQD%2F1h8WIcJMGsJBW0KUOuKUNeT9oPII78ph3O36aq0LkekimwZRkGvqkEmB2KU8NRjC0dXcurQ4a7tjySLPLtBw79FKL3L1dN7vX6cdeaWDoXUHKshEycl4mEfI0whXbBjw5ONdrAdYLttePWo2NgvpVtpRPdbTgnfFCKAKOUbr6XKBTcrcCgHxo7847MviQvBD7Vs9pQ5076cUdmlZLj%2F04tvC4%3D~-1~-1~-1; INGRESSCOOKIE=1648595781.231.59.751502%7C5eeab2ea8cdefed6dbef0b52cd0ff0b3; w-rctx=eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJuYmYiOjE2NDkzNzk4MjQsImV4cCI6MTY0OTM4MzQyNCwiaWF0IjoxNjQ5Mzc5ODI0LCJpc3MiOiJXb29sd29ydGhzIiwiYXVkIjoid3d3Lndvb2x3b3J0aHMuY29tLmF1Iiwic2lkIjoiMCIsInVpZCI6IjU2MmQ0ZTQ1LWE3MGUtNDM0OS1hZDlkLWUwZWJkMzA3MWRkNCIsIm1haWQiOiIwIiwiYXV0IjoiU2hvcHBlciIsImF1YiI6IjAiLCJhdWJhIjoiMCIsIm1mYSI6IjEifQ.D33O2NKIFhIQ52KNVg_WmliSLB-bj7isK7gsSXb4rAl8vVHFwKVA8NtaO_DxFsg2yCgEs4Tf0aWhWatf4xkL63vdicmZDtJeeuEMBIkraIleq522v4QqkPaN9TjrZXExDWOVlrduSUpwohTWWFANCF0uCXVuToCbpJhoMGX3Lr2eX27W8VTGTmTc5bH-hMtM3ZFp_7lwyDRg5tpzEe17lxcsn1BIAt4VRtT4LsVvjExxg8IMZOkzBIk_3QCUAp-b579fukVDiRMC8kbFmM50Asc4KYiAFQ0YZKcgAgUxIq8CPdDztLoS-rJQU-V-LitNSFHrxJbq_vafrD4B7G2WSw; wow-auth-token=eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJuYmYiOjE2NDkzNzk4MjQsImV4cCI6MTY0OTM4MzQyNCwiaWF0IjoxNjQ5Mzc5ODI0LCJpc3MiOiJXb29sd29ydGhzIiwiYXVkIjoid3d3Lndvb2x3b3J0aHMuY29tLmF1Iiwic2lkIjoiMCIsInVpZCI6IjU2MmQ0ZTQ1LWE3MGUtNDM0OS1hZDlkLWUwZWJkMzA3MWRkNCIsIm1haWQiOiIwIiwiYXV0IjoiU2hvcHBlciIsImF1YiI6IjAiLCJhdWJhIjoiMCIsIm1mYSI6IjEifQ.D33O2NKIFhIQ52KNVg_WmliSLB-bj7isK7gsSXb4rAl8vVHFwKVA8NtaO_DxFsg2yCgEs4Tf0aWhWatf4xkL63vdicmZDtJeeuEMBIkraIleq522v4QqkPaN9TjrZXExDWOVlrduSUpwohTWWFANCF0uCXVuToCbpJhoMGX3Lr2eX27W8VTGTmTc5bH-hMtM3ZFp_7lwyDRg5tpzEe17lxcsn1BIAt4VRtT4LsVvjExxg8IMZOkzBIk_3QCUAp-b579fukVDiRMC8kbFmM50Asc4KYiAFQ0YZKcgAgUxIq8CPdDztLoS-rJQU-V-LitNSFHrxJbq_vafrD4B7G2WSw; prodwow-auth-token=eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJuYmYiOjE2NDkzNzk4MjQsImV4cCI6MTY0OTM4MzQyNCwiaWF0IjoxNjQ5Mzc5ODI0LCJpc3MiOiJXb29sd29ydGhzIiwiYXVkIjoid3d3Lndvb2x3b3J0aHMuY29tLmF1Iiwic2lkIjoiMCIsInVpZCI6IjU2MmQ0ZTQ1LWE3MGUtNDM0OS1hZDlkLWUwZWJkMzA3MWRkNCIsIm1haWQiOiIwIiwiYXV0IjoiU2hvcHBlciIsImF1YiI6IjAiLCJhdWJhIjoiMCIsIm1mYSI6IjEifQ.D33O2NKIFhIQ52KNVg_WmliSLB-bj7isK7gsSXb4rAl8vVHFwKVA8NtaO_DxFsg2yCgEs4Tf0aWhWatf4xkL63vdicmZDtJeeuEMBIkraIleq522v4QqkPaN9TjrZXExDWOVlrduSUpwohTWWFANCF0uCXVuToCbpJhoMGX3Lr2eX27W8VTGTmTc5bH-hMtM3ZFp_7lwyDRg5tpzEe17lxcsn1BIAt4VRtT4LsVvjExxg8IMZOkzBIk_3QCUAp-b579fukVDiRMC8kbFmM50Asc4KYiAFQ0YZKcgAgUxIq8CPdDztLoS-rJQU-V-LitNSFHrxJbq_vafrD4B7G2WSw; dtCookie=v_4_srv_5_sn_3FD4A4D8AB14F0EAC249BD397DE6F604_perc_100000_ol_0_mul_1_app-3Af908d76079915f06_1_rcs-3Acss_0")
                .build();

        Item item = new Item("test", 20, 30);
        try {
            okhttp3.Response response = client.newCall(request).execute();
            assert response.body() != null;
            String textResponse = response.body().string();
            Log.d("Response", textResponse);

            // Parse string response into item object
            JSONObject obj = new JSONObject(textResponse);
            long barcode = Long.parseLong(obj.getString("Barcode"));
            String displayName = obj.getString("DisplayName");
            double price = Double.parseDouble(obj.getString("Price"));
            item.setBarcode(barcode);
            item.setName(displayName);
            item.setPrice(price);
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return item;
    }

    public void searchTerm(String term) {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.woolworths.com.au/apis/ui/Search/products/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();

        ItemService itemService = retrofit.create(ItemService.class);
        WoolworthsPostBodyModel modal = new WoolworthsPostBodyModel(term);
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
