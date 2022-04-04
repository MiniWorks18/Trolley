package com.example.trolley;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.*;

public interface ItemService {

    @Headers({
            "Content-Type: application/json",
            "origin: https://www.woolworths.com.au",
            "referer: https://www.woolworths.com.au/shop/search/products?searchTerm=apples",
            "user-agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/99.0.4844.88 Safari/537.36"
    })
    @POST(".")
    Call<WoolworthsResponseModel> getItems(@Body WoolworthsPostBodyModel woolworthsPostBodyModel,
                              @Header("content-type") String contentType);

}
