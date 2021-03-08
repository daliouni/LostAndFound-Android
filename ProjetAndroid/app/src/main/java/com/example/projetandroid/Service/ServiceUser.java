package com.example.projetandroid.Service;

import com.example.projetandroid.Entities.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ServiceUser {

    public String url="http://192.168.1.15:3000";
    @GET("user")
    Call<List<User>>getAll();

    @POST("user/add")
    Call<User> create(@Body User user);

    @POST ("user/update")
    Call<User> update (@Body User user);
    @GET ("user/{id}")
    Call<List<User>> getUser(@Path("id")int id);


}


