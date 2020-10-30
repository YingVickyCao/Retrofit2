package com.hades.example.retrofit2._6_302.v2_forbid302_and_get_location;

import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Url;
import rx.Observable;

public interface ISampleService {
    @GET
    Observable<Response<ResponseBody>> getPosition(@Url String url);
}
