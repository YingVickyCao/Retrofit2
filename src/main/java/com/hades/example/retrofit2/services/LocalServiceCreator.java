package com.hades.example.retrofit2.services;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import java.io.IOException;

public class LocalServiceCreator {
    private String BASE_URL = "http://localhost:7777/";

    public LocalService init() throws IOException {
        Retrofit retrofit = new Retrofit.Builder()
                .client(new OkHttpUtils().createOkHttpClient())
                .baseUrl(BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())      // return String:Call<String>
                .addConverterFactory(GsonConverterFactory.create())         // return json:bean)
                // FIXED_ERROR:Caused by: java.lang.IllegalArgumentException: Could not locate call adapter for io.reactivex.Observable.
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())   // return Observable<Response<ResponseBody>>
                .build();

        LocalService service = retrofit.create(LocalService.class);
        return service;
    }
}
