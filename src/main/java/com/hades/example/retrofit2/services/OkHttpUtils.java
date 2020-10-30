package com.hades.example.retrofit2.services;

import com.hades.example.retrofit2._6_302.v1.LoggingInterceptor;
import com.hades.example.retrofit2._6_302.v2_forbid302_and_get_location.RedirectInterceptor;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;

public class OkHttpUtils {
    public static OkHttpClient createOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addNetworkInterceptor(createHttpLoggingInterceptor());
//        builder.addInterceptor(createHttpLoggingInterceptor());
        OkHttpClient okHttpClient = builder.build();
        return okHttpClient;
    }

    public static OkHttpClient createOkHttpClient_forbid302() {
        return new OkHttpClient.Builder()
                .addNetworkInterceptor(createHttpLoggingInterceptor())
                .addNetworkInterceptor(new RedirectInterceptor())
//                .addInterceptor(new RedirectInterceptor())
//                .addInterceptor(createHttpLoggingInterceptor())
                .followRedirects(false)
                .followSslRedirects(false)
                .build();
    }

    private static Interceptor createHttpLoggingInterceptor() {
        LoggingInterceptor httpLoggingInterceptor = new LoggingInterceptor();
//        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return httpLoggingInterceptor;
    }
}
