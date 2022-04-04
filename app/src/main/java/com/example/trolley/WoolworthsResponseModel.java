package com.example.trolley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class WoolworthsResponseModel {
    private JSONArray products;

    public JSONObject getItem(int index) throws JSONException {
        return products.getJSONObject(index);
    }
}
