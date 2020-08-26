package com.hades.example.retrofit2.services;

import com.hades.example.retrofit2._6_302.v1.CustomLoggingInterceptor;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

public class LocalServiceCreator {
    private String BASE_URL = "http://localhost:7777/";

    public LocalService init() throws IOException {
        Retrofit retrofit = new Retrofit.Builder()
                .client(createOkHttpClient())
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        LocalService service = retrofit.create(LocalService.class);
        return service;
    }

    public OkHttpClient createOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addNetworkInterceptor(createHttpLoggingInterceptor());
//        builder.addInterceptor(createHttpLoggingInterceptor());
        OkHttpClient okHttpClient = builder.build();
        return okHttpClient;
    }

    private Interceptor createHttpLoggingInterceptor() {
        CustomLoggingInterceptor httpLoggingInterceptor = new CustomLoggingInterceptor();
//        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return httpLoggingInterceptor;
    }
}
