package com.example.trolley;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.*;

public interface UserService {

    @GET("/users")
    public Call<List<GitUser>> getUsers(
            @Query("per_page") int per_page,
            @Query("page") int page);

    @GET("/users/{username}")
    public Call<GitUser> getUser(@Path("username") String username);
}
