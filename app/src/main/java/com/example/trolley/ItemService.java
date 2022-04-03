package com.example.trolley;

import com.google.gson.JsonArray;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.*;

public interface ItemService {

    @POST()
    public Call<JsonArray> getItems(@Body WoolworthsPostBodyModel woolworthsPostBodyModel);

}
