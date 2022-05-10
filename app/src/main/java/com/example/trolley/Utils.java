package com.example.trolley;

import android.util.Log;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.methods.CloseableHttpResponse;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.methods.HttpGet;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.methods.HttpPost;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.entity.StringEntity;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.client.CloseableHttpClient;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.client.HttpClients;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.util.EntityUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;

public class Utils {

    public Item[] searchColes(String term) {
        // Start HTTP instance
        HttpGet request = new HttpGet("https://shop.coles.com.au/online/COLRSSearchDisplay?storeId=20601&searchTerm="+term.replace(" ", "%20"));
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
                JSONObject prices = (JSONObject) product.get("p1");

                items[i] = new Item((String) product.get("n"));
                items[i].setColesPrice(Double.parseDouble((String) prices.get("o")));
                try {
                    items[i].setColesWasPrice(Double.parseDouble((String) prices.get("l4")));
                    items[i].setColesHasCupPrice(true);
                } catch (JSONException e) {
                    items[i].setColesHasCupPrice(false);
                }
                items[i].setColesCupPrice((String) product.get("u2"));
                items[i].setColesImageURL((String) product.get("t"));
                items[i].setColesCode((String) product.get("p"));
                items[i].setBrand((String) product.get("m"));
                items[i].setAtColes(true);
                items[i].setColesDone(true);
                items[i].setWoolworthsDone(true);
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
            Item[] empty = new Item[1];
            empty[0] = new Item();
            empty[0].setInStock(false);
            return empty;
        }

        if (items.length < 1) {
            Item[] empty = new Item[1];
            empty[0] = new Item();
            empty[0].setInStock(false);
            return empty;
        }
        return items;
    }


    // Search Woolworths by term i.e. "apples" "fish"
    public Item[] searchWoolworths(String term) {
        ArrayList<Item> items;

        // Construct post request
        HttpPost request = new HttpPost("https://www.woolworths.com.au/apis/ui/Search/products");
        CloseableHttpClient client = HttpClients.createDefault();
        String json = "{\"IsSpecial\":false,\"Location\":\"/shop/search/products?searchTerm="+term+"\",\"PageNumber\":1,\"PageSize\":36,\"SearchTerm\":\""+term+"\",\"SortType\":\"TraderRelevance\"}";

        try {
            StringEntity entity = new StringEntity(json);
            request.setEntity(entity);
            request.setHeader("accept", "*/*");
            request.setHeader("content-type", "application/json");
            request.setHeader("host", "www.woolworths.com.au");
            request.setHeader("user-agent", "insomnia/2022.2.1");
            request.setHeader("cookie", "akaalb_woolworths.com.au=~op=www_woolworths_com_au_Zone" +
                    "AandC:PROD-ZoneC|~id=ca7997d0f95fd2955be7c8ab2c06376c; "
                    +"_abck=4D008D2225FDB4B0E808F320484544EB~-1~YAAQnd/3SC3zK0GAAQAAZbrVaQeey9"
                    +"9sBa8WZGUAspcNpRvE0+bAeniSauCuYlbXm8R3IoQJzYJBTz7Anyc2KiNgn2K37LaLT59XX7"
                    +"DcTK+n0jIbTlOWXRVmpuJVZT1OEhNYSBclM+VpBioAFGjtlZtTTUh9TaTaIxcS6OIiFFfiZxId"
                    +"QbxFmHc3do71B+8t4SHLvKj1l/WqqxAPKT3Orees6pzBmT/40Dho9f9ZE96DIYrdLdh0LYxg5F"
                    +"OlVz5KzQnoehKqkSbtwMKQLvFXFR8fOGqlWChffsUQ2bt1kP5zRhPjPLd4VNx0sESVk6Z7bf3z"
                    +"yHuR8WHjEg6ZKeJonIocQjN8hmnLMgIshQuRAijmjIZRCvtKgCsbTQqSx9u/PziQixQHFjxOAk"
                    +"hSztc=~-1~-1~-1");

            // Execute post request
            CloseableHttpResponse response = client.execute(request);

            // Transform response to JSON
            String rawData = EntityUtils.toString(response.getEntity());
            JSONObject data = new JSONObject(rawData);

            // Process data and fetch item information
            JSONArray products = (JSONArray) data.get("Products");
            items = new ArrayList<>();

            JSONObject product, productDetails;
            JSONArray productInfo;

            // Parse JSON into Items, avoiding when IsInStock = false
            for (int i = 0, index = 0; i < products.length(); i++) {
                product = (JSONObject) products.get(i);
                productInfo = (JSONArray) product.get("Products");
                productDetails = (JSONObject) productInfo.get(0);


                if (productDetails.getBoolean("IsInStock")) {
                    items.add(new Item(productDetails.getString("DisplayName")));
                    items.get(index).setWoolworthsPrice(productDetails.getDouble("Price"));
                    items.get(index).setWoolworthsWasPrice(productDetails.getDouble("WasPrice"));
                    items.get(index).setWoolworthsHasCupPrice(productDetails.getBoolean("HasCupPrice"));
                    items.get(index).setWoolworthsCupPrice(productDetails.getString("CupString"));
                    items.get(index).setBarcode(Long.parseLong(productDetails.getString("Barcode")));
                    items.get(index).setAtWoolworths(true);
                    items.get(index).setWoolworthsImageURL(productDetails.getString("MediumImageFile"));
                    Log.i("Response", response.getEntity().getContent().toString());
                    items.get(index).setWoolworthsDone(true);
                    index++;
                }
            }

        } catch (IOException | UnsupportedOperationException | JSONException | StackOverflowError e) {
            e.printStackTrace();
            return new Item[0];
        }
        return items.toArray(new Item[0]);
    }

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

    public int getRandomNumber(int from, int to) {
        return (int) ((Math.random() * (to - from)) + from);
    }
}
