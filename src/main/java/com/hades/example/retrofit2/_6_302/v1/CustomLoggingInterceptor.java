package com.hades.example.retrofit2._6_302.v1;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

import java.io.IOException;

public class CustomLoggingInterceptor implements Interceptor {
    HttpLoggingInterceptor.Logger logger = HttpLoggingInterceptor.Logger.DEFAULT;

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request request = chain.request();

        long t1 = System.nanoTime();
        logger.log(String.format("Sending request %s on %s%n%s", request.url(), chain.connection(), request.headers()));
        Response response = chain.proceed(request);

        long t2 = System.nanoTime();
        logger.log(String.format("Received response for %s in %.1fms \nstatus code %s  %n%s", response.request().url(), (t2 - t1) / 1e6d, response.code(), response.headers()));
//        logger.log("Response status code:" + response.code());
        return response;
    }
}