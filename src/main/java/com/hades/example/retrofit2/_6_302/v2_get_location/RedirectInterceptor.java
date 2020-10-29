package com.hades.example.retrofit2._6_302.v2_get_location;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

import java.io.IOException;

public class RedirectInterceptor implements Interceptor {
    private static final String TAG = RedirectInterceptor.class.getSimpleName();
    HttpLoggingInterceptor.Logger logger = HttpLoggingInterceptor.Logger.DEFAULT;

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        long t1 = System.nanoTime();
        logger.log(String.format("Sending request %s on %s%n%s", request.url(), chain.connection(), request.headers()));
        Response response = chain.proceed(request);

        long t2 = System.nanoTime();
        logger.log(String.format("Received response for %s in %.1fms \nstatus code %s  %n%s", response.request().url(), (t2 - t1) / 1e6d, response.code(), response.headers()));
//        logger.log("Response status code:" + response.code());
//        logger.log(response.header("location"));

        logger.log("response.isRedirect():" + response.isRedirect());
        if (response.code() == 301 || response.code() == 302) {
            String location = response.header("location");
            if (null != location && location.startsWith("https://www.publicobject.com/")) {
                logger.log("location:" + location);
                response.close();
                response.body().close();
            }
        }
        return response;
    }
}