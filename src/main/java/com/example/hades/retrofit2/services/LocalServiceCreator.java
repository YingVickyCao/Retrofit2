package com.example.hades.retrofit2.services;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

public abstract class LocalServiceCreator {
    private String BASE_URL = "http://localhost:7777/";

    protected void init() throws IOException {
        Retrofit retrofit = new Retrofit.Builder()
                .client(new OkHttpClient())
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        LocalService service = retrofit.create(LocalService.class);
        request(service);
    }

    protected abstract void request(LocalService service) throws IOException;
}
