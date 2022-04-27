package com.example.trolley;

import android.util.Log;

import com.android.volley.toolbox.HttpResponse;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.Header;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HeaderElement;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpEntity;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.methods.CloseableHttpResponse;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.methods.HttpGet;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.methods.HttpPost;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.entity.StringEntity;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.client.CloseableHttpClient;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.client.HttpClients;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.util.EntityUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

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

        Item item = new Item("test", 20);
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
            item.setWoolworthsPrice(price);
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return item;
    }

    public Item searchWoolworths(String term) {
        Item item = new Item();
        HttpPost request = new HttpPost("https://www.woolworths.com.au/apis/ui/Search/products");
        CloseableHttpClient client = HttpClients.createDefault();
        String json = "{\"IsSpecial\":false,\"Location\":\"/shop/search/products?searchTerm="+term+"\",\"PageNumber\":1,\"PageSize\":24,\"SearchTerm\":\""+term+"\",\"SortType\":\"TraderRelevance\"}";

        try {
            StringEntity entity = new StringEntity(json);
            request.setEntity(entity);
            request.setHeader("accept", "*/*");
            request.setHeader("content-type", "application/json");
            request.setHeader("host", "www.woolworths.com.au");
            request.setHeader("user-agent", "insomnia/2022.2.1");
            request.setHeader("cookie", "akaalb_woolworths.com.au=~op=www_woolworths_com_au_ZoneAandC:PROD-ZoneC|~rv=10~m=PROD-ZoneC:0|~os=43eb3391333cc20efbd7f812851447e6~id=ca7997d0f95fd2955be7c8ab2c06376c; INGRESSCOOKIE=1648595781.231.59.751502|5eeab2ea8cdefed6dbef0b52cd0ff0b3; w-rctx=eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJuYmYiOjE2NTEwNDAyMjEsImV4cCI6MTY1MTA0MzgyMSwiaWF0IjoxNjUxMDQwMjIxLCJpc3MiOiJXb29sd29ydGhzIiwiYXVkIjoid3d3Lndvb2x3b3J0aHMuY29tLmF1Iiwic2lkIjoiMCIsInVpZCI6IjJmODkwYjg2LWVkOWYtNGZiNC05ZDJlLTc4YjRlZTE0YWU3MiIsIm1haWQiOiIwIiwiYXV0IjoiU2hvcHBlciIsImF1YiI6IjAiLCJhdWJhIjoiMCIsIm1mYSI6IjEifQ.ICYXaMOysxFrBa9XdI0qNomKCYmJoXtAsx8n_tc8OA9ACpZt4aex0uc_L_PMr51DVQ9AImKAfQxJCV-FgB1Tv9OJPhmKSGV3shlOSAYnFcBCS_01RnwWPIM1OeGKVTiznUtHqdW7t-j_3jwJXH-Vyktq-wn5k0jVdjvS6GOLmtEkj0vXD9Z2qeNaqlfhrkx0hmWX1aRoRDfRc7CVUzdPEYf4yjqPLebfEyDksTAQhQtdMUQVJPnlnzHllp5DAi2C9B-qkMc3acKMlDpUrB_Ykn3pRRkkXef5Dk-71zKjUHgCUQAs470moES_frA4IGoUbn8anvcAINfFQcCP7qx6fg; prodwow-auth-token=eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJuYmYiOjE2NTEwNDAyMjEsImV4cCI6MTY1MTA0MzgyMSwiaWF0IjoxNjUxMDQwMjIxLCJpc3MiOiJXb29sd29ydGhzIiwiYXVkIjoid3d3Lndvb2x3b3J0aHMuY29tLmF1Iiwic2lkIjoiMCIsInVpZCI6IjJmODkwYjg2LWVkOWYtNGZiNC05ZDJlLTc4YjRlZTE0YWU3MiIsIm1haWQiOiIwIiwiYXV0IjoiU2hvcHBlciIsImF1YiI6IjAiLCJhdWJhIjoiMCIsIm1mYSI6IjEifQ.ICYXaMOysxFrBa9XdI0qNomKCYmJoXtAsx8n_tc8OA9ACpZt4aex0uc_L_PMr51DVQ9AImKAfQxJCV-FgB1Tv9OJPhmKSGV3shlOSAYnFcBCS_01RnwWPIM1OeGKVTiznUtHqdW7t-j_3jwJXH-Vyktq-wn5k0jVdjvS6GOLmtEkj0vXD9Z2qeNaqlfhrkx0hmWX1aRoRDfRc7CVUzdPEYf4yjqPLebfEyDksTAQhQtdMUQVJPnlnzHllp5DAi2C9B-qkMc3acKMlDpUrB_Ykn3pRRkkXef5Dk-71zKjUHgCUQAs470moES_frA4IGoUbn8anvcAINfFQcCP7qx6fg; wow-auth-token=eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJuYmYiOjE2NTEwNDAyMjEsImV4cCI6MTY1MTA0MzgyMSwiaWF0IjoxNjUxMDQwMjIxLCJpc3MiOiJXb29sd29ydGhzIiwiYXVkIjoid3d3Lndvb2x3b3J0aHMuY29tLmF1Iiwic2lkIjoiMCIsInVpZCI6IjJmODkwYjg2LWVkOWYtNGZiNC05ZDJlLTc4YjRlZTE0YWU3MiIsIm1haWQiOiIwIiwiYXV0IjoiU2hvcHBlciIsImF1YiI6IjAiLCJhdWJhIjoiMCIsIm1mYSI6IjEifQ.ICYXaMOysxFrBa9XdI0qNomKCYmJoXtAsx8n_tc8OA9ACpZt4aex0uc_L_PMr51DVQ9AImKAfQxJCV-FgB1Tv9OJPhmKSGV3shlOSAYnFcBCS_01RnwWPIM1OeGKVTiznUtHqdW7t-j_3jwJXH-Vyktq-wn5k0jVdjvS6GOLmtEkj0vXD9Z2qeNaqlfhrkx0hmWX1aRoRDfRc7CVUzdPEYf4yjqPLebfEyDksTAQhQtdMUQVJPnlnzHllp5DAi2C9B-qkMc3acKMlDpUrB_Ykn3pRRkkXef5Dk-71zKjUHgCUQAs470moES_frA4IGoUbn8anvcAINfFQcCP7qx6fg; dtCookie=v_4_srv_5_sn_3FD4A4D8AB14F0EAC249BD397DE6F604_perc_100000_ol_0_mul_1_app-3Af908d76079915f06_1_rcs-3Acss_0; bm_sv=D312E699BF21D77834BC1912EB98A25D~pLTyONWpj1gACt3NqNT3gxB0ewDwyN3jH6qg+BBJkWFgybMOyc6P8jcS1cjfnT2qYYQ+bRFVWLLHh+ZqxZfmX9UQYAUcQeUYzisO7/vdJHpqrBnwdNHbrn9NjBVeC9Bus92f2/IpvdJYki5s+zs2unKXC+lwW6iCNwZQVOdsemA=; bm_sz=A8EAB9A8B11EEA476511CA03C1307305~YAAQZ4/+PH2pRFOAAQAAxcVoaQ+XtPmozrBiF+BfAB8zg1Bjz+FJ0Im4YloxEYFwTmCIVHGkOoRmNRooIbnnuH5/Ps1OeQ6wH0lnmeS/DGfN7Fq76L3uBA5lwL/wgUQcz6Hu4NxLxOX0hqWyqb4V7Ris1HCUikfxeuETIm4gCghzk/CjqQ5UmoFQcukNxA874eBkjp7bW29tvqwgZNGhRKnQ41oFw1iTo5WuCHWdE//al/x9/8ELfRzy0G2kFMPGdZtyUfsh7AGvgDIYK9VRUbhXk4AQg5q7GIdf6x1jdB2ouyv3R1bCk8Tt~3289142~3422259; _abck=4D008D2225FDB4B0E808F320484544EB~-1~YAAQn9/3SF6wcWmAAQAAJi7VaQc/K/226NZ+YSLPnNOjVuTqxRO625sofyDvo3/5/9XIbSmrmJn2SSh20BsF/0V8bSQM228bcsih2uzjOztIyolPROoNPedCCBmzzozb5T7MRDR1zx4VB8cMjYCXa7mIR9VUU6Yf5bv/2WHcgrEV1BTe5E2BMMdDzosUJqm9oJvcE6/yqmHRFy785xetXxcbSKbdLoy0K11th5BPgofkGB98vqiXxyRvNLXJQ4yGv25JPR+XQeg0KpmWGPu/MeZ3nBeaAYg0fBXe1VloMm8JSfj3oKAeWKsMJTWFhreE4ILyzDldTDF4ONiVLYlhj2RUW687BRO3/gaeNsYKRUTOV13/2MFH7+5HkIvy8cn3lrGF9VkJFWsaeru5BJw=~-1~-1~-1");
            CloseableHttpResponse response = client.execute(request);
            String rawData = EntityUtils.toString(response.getEntity());
            JSONObject data = new JSONObject(rawData);
            JSONArray products = (JSONArray) data.get("Products");
            JSONObject product1 = (JSONObject) products.get(0);
            JSONArray product1_1 = (JSONArray) product1.get("Products");
            JSONObject product1_2 = (JSONObject) product1_1.get(0);
            //TODO Clean this up and try avoid the stackoverflow as seen when debugging
            String name = product1_2.getString("DisplayName");
            item.setWoolworthsPrice(product1_2.getDouble("Price"));
            item.setName(product1_2.getString("DisplayName"));
            item.setBarcode(Long.parseLong(product1_2.getString("Barcode")));
            Log.i("Response", response.getEntity().getContent().toString());

        } catch (IOException | UnsupportedOperationException | JSONException | StackOverflowError e) {
            e.printStackTrace();
        }


        return item;
    }

    public Item fetchColesItemByBarcode(long code) {
        // Start HTTP instance
        HttpGet request = new HttpGet("https://shop.coles.com.au/online/COLRSSearchDisplay?storeId=20601&searchTerm="+code);
        CloseableHttpClient client = HttpClients.createDefault();
        Item item = new Item();
        try {
            // Fetch HTML document
            CloseableHttpResponse response = client.execute(request);

            // Transform HTML into JSONObject for processing
            Document dom = Jsoup.parse(EntityUtils.toString(response.getEntity()));
            Elements rawElement = dom.body().getElementsByAttributeValue("data-colrs-bind",
                    "colrsCatalogEntryListWidget_Data_2_3074457345618260154_3074457345618264559");
            Node stringData = rawElement.get(0).childNode(0);
            JSONObject data = new JSONObject(stringData.toString());

            // Update item information with JSONObject
            JSONArray products = (JSONArray) data.get("products");
            JSONObject product = (JSONObject) products.get(0);

            item.setName((String) product.get("n"));
            item.setColesPrice(Double.parseDouble((String) product.get("pr")));
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
