package com.hades.example.retrofit2.services;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

public class FileDownloadServiceCreator {
    private String BASE_URL = "https://gitee.com/YingVickyCao/ServerMocker/blob/master/";

    public void init() throws IOException {
        Retrofit retrofit = new Retrofit.Builder()
                .client(new OkHttpClient())
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        FileDownloadService service = retrofit.create(FileDownloadService.class);
    }

    public FileDownloadService create() throws IOException {
        Retrofit retrofit = new Retrofit.Builder()
                .client(new OkHttpClient())
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(FileDownloadService.class);
    }}
