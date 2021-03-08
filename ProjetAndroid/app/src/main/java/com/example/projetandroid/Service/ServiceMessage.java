package com.example.projetandroid.Service;

import com.example.projetandroid.Entities.Message;
import com.example.projetandroid.Entities.Objet;
import com.example.projetandroid.Entities.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ServiceMessage {

    public String url="http://192.168.1.15:3000";
    @POST("message/add")
    Call<Message> create(@Body Message message);
    @GET("message/{id}")
    Call<List<Message>>getAll(@Path("id")int id);
}
