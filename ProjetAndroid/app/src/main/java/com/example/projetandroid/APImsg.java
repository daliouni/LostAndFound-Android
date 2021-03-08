package com.example.projetandroid;

import retrofit2.Retrofit;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.converter.gson.GsonConverterFactory;

public class APImsg {
    private static Retrofit retrofit = null;
    public  static  Retrofit getClient()
    {
        HttpLoggingInterceptor interceptor = new  HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.addInterceptor(interceptor).build();
        retrofit =new Retrofit.Builder().baseUrl("http://192.168.1.15:3000/")
                .addConverterFactory(GsonConverterFactory.create()).client(client.build()).build();
        return retrofit;

    }
}
