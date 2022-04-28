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
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
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

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Map;

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

    // Old method, may not use
    public Item fetchWooliesItemByCode(long code) {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://www.woolworths.com.au/apis/ui/product/"+code)
                .get()
                .addHeader("cookie", "akaalb_woolworths.com.au=~op%3Dwww_woolworths_com_au_ZoneAandC%3APROD-ZoneC%7C~rv%3D10~m%3DPROD-ZoneC%3A0%7C~os%3D43eb3391333cc20efbd7f812851447e6~id%3Dca7997d0f95fd2955be7c8ab2c06376c; _abck=4D008D2225FDB4B0E808F320484544EB~-1~YAAQn9%2F3SAnfbPt%2FAQAARhHJBgf0J%2FQD9mqhSENp8pfgOgo2Z5VkiukTSTwmHx%2FYH6EGRDJg9j88qjdJ8e7pCpm96sIOHqq7Uf6st3%2FAnGQB9slqQc9N1X8t6PdtPaiLj88CJYxWIFuyn44rXxhiaqH4aTOr1VeEDboftGNgbg5PQDEfn4T4HhhQD%2F1h8WIcJMGsJBW0KUOuKUNeT9oPII78ph3O36aq0LkekimwZRkGvqkEmB2KU8NRjC0dXcurQ4a7tjySLPLtBw79FKL3L1dN7vX6cdeaWDoXUHKshEycl4mEfI0whXbBjw5ONdrAdYLttePWo2NgvpVtpRPdbTgnfFCKAKOUbr6XKBTcrcCgHxo7847MviQvBD7Vs9pQ5076cUdmlZLj%2F04tvC4%3D~-1~-1~-1; INGRESSCOOKIE=1648595781.231.59.751502%7C5eeab2ea8cdefed6dbef0b52cd0ff0b3; w-rctx=eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJuYmYiOjE2NDkzNzk4MjQsImV4cCI6MTY0OTM4MzQyNCwiaWF0IjoxNjQ5Mzc5ODI0LCJpc3MiOiJXb29sd29ydGhzIiwiYXVkIjoid3d3Lndvb2x3b3J0aHMuY29tLmF1Iiwic2lkIjoiMCIsInVpZCI6IjU2MmQ0ZTQ1LWE3MGUtNDM0OS1hZDlkLWUwZWJkMzA3MWRkNCIsIm1haWQiOiIwIiwiYXV0IjoiU2hvcHBlciIsImF1YiI6IjAiLCJhdWJhIjoiMCIsIm1mYSI6IjEifQ.D33O2NKIFhIQ52KNVg_WmliSLB-bj7isK7gsSXb4rAl8vVHFwKVA8NtaO_DxFsg2yCgEs4Tf0aWhWatf4xkL63vdicmZDtJeeuEMBIkraIleq522v4QqkPaN9TjrZXExDWOVlrduSUpwohTWWFANCF0uCXVuToCbpJhoMGX3Lr2eX27W8VTGTmTc5bH-hMtM3ZFp_7lwyDRg5tpzEe17lxcsn1BIAt4VRtT4LsVvjExxg8IMZOkzBIk_3QCUAp-b579fukVDiRMC8kbFmM50Asc4KYiAFQ0YZKcgAgUxIq8CPdDztLoS-rJQU-V-LitNSFHrxJbq_vafrD4B7G2WSw; wow-auth-token=eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJuYmYiOjE2NDkzNzk4MjQsImV4cCI6MTY0OTM4MzQyNCwiaWF0IjoxNjQ5Mzc5ODI0LCJpc3MiOiJXb29sd29ydGhzIiwiYXVkIjoid3d3Lndvb2x3b3J0aHMuY29tLmF1Iiwic2lkIjoiMCIsInVpZCI6IjU2MmQ0ZTQ1LWE3MGUtNDM0OS1hZDlkLWUwZWJkMzA3MWRkNCIsIm1haWQiOiIwIiwiYXV0IjoiU2hvcHBlciIsImF1YiI6IjAiLCJhdWJhIjoiMCIsIm1mYSI6IjEifQ.D33O2NKIFhIQ52KNVg_WmliSLB-bj7isK7gsSXb4rAl8vVHFwKVA8NtaO_DxFsg2yCgEs4Tf0aWhWatf4xkL63vdicmZDtJeeuEMBIkraIleq522v4QqkPaN9TjrZXExDWOVlrduSUpwohTWWFANCF0uCXVuToCbpJhoMGX3Lr2eX27W8VTGTmTc5bH-hMtM3ZFp_7lwyDRg5tpzEe17lxcsn1BIAt4VRtT4LsVvjExxg8IMZOkzBIk_3QCUAp-b579fukVDiRMC8kbFmM50Asc4KYiAFQ0YZKcgAgUxIq8CPdDztLoS-rJQU-V-LitNSFHrxJbq_vafrD4B7G2WSw; prodwow-auth-token=eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJuYmYiOjE2NDkzNzk4MjQsImV4cCI6MTY0OTM4MzQyNCwiaWF0IjoxNjQ5Mzc5ODI0LCJpc3MiOiJXb29sd29ydGhzIiwiYXVkIjoid3d3Lndvb2x3b3J0aHMuY29tLmF1Iiwic2lkIjoiMCIsInVpZCI6IjU2MmQ0ZTQ1LWE3MGUtNDM0OS1hZDlkLWUwZWJkMzA3MWRkNCIsIm1haWQiOiIwIiwiYXV0IjoiU2hvcHBlciIsImF1YiI6IjAiLCJhdWJhIjoiMCIsIm1mYSI6IjEifQ.D33O2NKIFhIQ52KNVg_WmliSLB-bj7isK7gsSXb4rAl8vVHFwKVA8NtaO_DxFsg2yCgEs4Tf0aWhWatf4xkL63vdicmZDtJeeuEMBIkraIleq522v4QqkPaN9TjrZXExDWOVlrduSUpwohTWWFANCF0uCXVuToCbpJhoMGX3Lr2eX27W8VTGTmTc5bH-hMtM3ZFp_7lwyDRg5tpzEe17lxcsn1BIAt4VRtT4LsVvjExxg8IMZOkzBIk_3QCUAp-b579fukVDiRMC8kbFmM50Asc4KYiAFQ0YZKcgAgUxIq8CPdDztLoS-rJQU-V-LitNSFHrxJbq_vafrD4B7G2WSw; dtCookie=v_4_srv_5_sn_3FD4A4D8AB14F0EAC249BD397DE6F604_perc_100000_ol_0_mul_1_app-3Af908d76079915f06_1_rcs-3Acss_0")
                .build();

        Item item = new Item("test");
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

    // Search Woolworths by term i.e. "apples" "fish"
    public Item[] searchWoolworths(String term) {
        Item[] items;

        // Construct post request
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
            request.setHeader("cookie",
                    "akaalb_woolworths.com.au=~op=www_woolworths_com_au_Zone" +
                    "AandC:PROD-ZoneC|"
//                            +"~rv=10~m=PROD-ZoneC:0|"
//                            +"~os=43eb3391333cc20efbd7f812851447e6"
                            +"~id=ca7997d0f95fd2955be7c8ab2c06376c; "
//                    +"INGRESSCOOKIE=1648595781.231.59.751502|5eeab2ea8cdefed6dbef0b52cd0ff0b3; "
//                    +"dtCookie=v_4_srv_5_sn_3FD4A4D8AB14F0EAC249BD397DE6F604_perc_100000_ol_0_mul_1_app-3Af908d76079915f06_1_rcs-3Acss_0;"
//                    +" bm_sv=D312E699BF21D77834BC1912EB98A25D~pLTyONWpj1gACt3NqNT3gxB0ewDwyN3jH6qg+BBJkWFgybMOyc6P8jcS1"
//                    +"cjfnT2qYYQ+bRFVWLLHh+ZqxZfmX9UQYAUcQeUYzisO7/vdJHpqrBnwdNHbrn9NjBVeC9Bus92f2/IpvdJYki5s+zs2unKXC+lwW6iCNwZQVOdsemA=; "
                    +"_abck=4D008D2225FDB4B0E808F320484544EB~-1~YAAQnd/3SC3zK0GAAQAAZbrVaQeey99sBa8WZGUAspcNpRvE0+bAeniSauCuYlbXm8" +
                    "R3IoQJzYJBTz7Anyc2KiNgn2K37LaLT59XX7DcTK+n0jIbTlOWXRVmpuJVZT1OEhNYSBclM+VpBioAFGjtlZtTTUh9TaTaIxcS6OIiFFfiZxId" +
                    "QbxFmHc3do71B+8t4SHLvKj1l/WqqxAPKT3Orees6pzBmT/40Dho9f9ZE96DIYrdLdh0LYxg5FOlVz5KzQnoehKqkSbtwMKQLvFXFR8fOGqlWC" +
                    "hffsUQ2bt1kP5zRhPjPLd4VNx0sESVk6Z7bf3zyHuR8WHjEg6ZKeJonIocQjN8hmnLMgIshQuRAijmjIZRCvtKgCsbTQqSx9u/PziQixQHFjxOAkhSztc"
                    +"=~-1~-1~-1"
            );

            // Execute post request
            CloseableHttpResponse response = client.execute(request);

            // Transform response to JSON
            String rawData = EntityUtils.toString(response.getEntity());
            JSONObject data = new JSONObject(rawData);

            // Process data and fetch item information
            JSONArray products = (JSONArray) data.get("Products");
            items = new Item[products.length()];

            JSONObject product, productDetails;
            JSONArray productInfo;

            for (int i = 0; i < products.length(); i++) {
                product = (JSONObject) products.get(i);
                productInfo = (JSONArray) product.get("Products");
                productDetails = (JSONObject) productInfo.get(0);

                items[i] = new Item(productDetails.getString("DisplayName"));
                items[i].setWoolworthsPrice(productDetails.getDouble("Price"));
                items[i].setBarcode(Long.parseLong(productDetails.getString("Barcode")));
                Log.i("Response", response.getEntity().getContent().toString());
            }


        } catch (IOException | UnsupportedOperationException | JSONException | StackOverflowError e) {
            e.printStackTrace();
            return new Item[0];
        }


        return items;
    }

    public Item[] searchColes(String term) {
        // Start HTTP instance
        HttpGet request = new HttpGet("https://shop.coles.com.au/online/COLRSSearchDisplay?storeId=20601&searchTerm="+term);
        CloseableHttpClient client = HttpClients.createDefault();
        Item[] items;
        try {
            // Fetch HTML document
            CloseableHttpResponse response = client.execute(request);

            // Transform HTML into JSONObject for processing
            Document dom = Jsoup.parse(EntityUtils.toString(response.getEntity()));
            Elements rawElement = dom.body().getElementsByAttributeValue("data-colrs-bind",
                    "colrsCatalogEntryListWidget_Data_2_3074457345618260154_3074457345618264559");
            Node stringData = rawElement.get(0).childNode(0);
            JSONObject data = new JSONObject(stringData.toString());


            // Update each item information with JSONObject
            JSONArray products = (JSONArray) data.get("products");
            items = new Item[products.length()];

            for (int i =0; i < products.length(); i++) {
                JSONObject product = (JSONObject) products.get(i);
                JSONObject price = (JSONObject) product.get("p1");

                items[i] = new Item((String) product.get("n"));
                items[i].setColesPrice(Double.parseDouble((String) price.get("o")));
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
            return new Item[0];
        }
        return items;
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
