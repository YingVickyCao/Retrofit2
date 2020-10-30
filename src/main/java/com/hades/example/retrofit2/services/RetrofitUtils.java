package com.hades.example.retrofit2.services;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitUtils {
    public static Retrofit createRetrofit(String baseUrl) {
        return createRetrofit(OkHttpClientUtils.createOkHttpClient(), baseUrl);
    }

    public static Retrofit createRetrofit_forbid302(String baseUrl) {
        return createRetrofit(OkHttpClientUtils.createOkHttpClient_forbid302(), baseUrl);
    }

    private static Retrofit createRetrofit(OkHttpClient client, String baseURL) {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.client(client)
                .addConverterFactory(ScalarsConverterFactory.create())      // return String:Call<String>
                .addConverterFactory(GsonConverterFactory.create())         // return json:bean)
                // FIXED_ERROR:Caused by: java.lang.IllegalArgumentException: Could not locate call adapter for io.reactivex.Observable.
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create());  // return Observable<Response<ResponseBody>>
        builder.baseUrl(baseURL);
        return builder.build();
    }
}