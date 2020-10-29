package com.hades.example.retrofit2.services;

import com.hades.example.retrofit2._6_302.v1.CustomLoggingInterceptor;
import com.hades.example.retrofit2._6_302.v2_get_location.RedirectInterceptor;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;

public class OkHttpUtils {
    public OkHttpClient createOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addNetworkInterceptor(createHttpLoggingInterceptor());
//        builder.addInterceptor(createHttpLoggingInterceptor());
        OkHttpClient okHttpClient = builder.build();
        return okHttpClient;
    }

    public OkHttpClient createOkHttpClient_force302() {
        return new OkHttpClient.Builder()
        .addNetworkInterceptor(new RedirectInterceptor())
//        .addInterceptor(createHttpLoggingInterceptor())
                .followRedirects(false)
                .followSslRedirects(false)
                .build();
    }

    private Interceptor createHttpLoggingInterceptor() {
        CustomLoggingInterceptor httpLoggingInterceptor = new CustomLoggingInterceptor();
//        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return httpLoggingInterceptor;
    }
}
