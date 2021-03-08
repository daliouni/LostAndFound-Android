package com.example.projetandroid.Service;

import com.example.projetandroid.Entities.Objet;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ServiceObjet {

    public String url="http://192.168.1.15:3000";
    @GET("objet")
    Call<List<Objet>>getAll();

    @POST("objet/add")
    Call<Objet> create(@Body Objet objet);



}

