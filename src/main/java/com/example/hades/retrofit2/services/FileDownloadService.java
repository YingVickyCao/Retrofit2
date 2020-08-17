package com.example.hades.retrofit2.services;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

public interface FileDownloadService {
    // https://gitee.com/YingVickyCao/ServerMocker/blob/master/full.zip
    static String ZIP_FILE = "https://gitee.com/YingVickyCao/ServerMocker/blob/master/full.zip";

    // Way 1 : a resource relative to your base URL
    @GET("/full.zip")
    Call<ResponseBody> downloadFileWithFixedUrl();

    // Way 2: using a dynamic URL
    @GET
    Call<ResponseBody> downloadFileWithDynamicUrlSync(@Url String fileUrl);


    @Streaming
    @GET
    Call<ResponseBody> downloadFileWithDynamicUrlSync2(@Url String fileUrl);
}
