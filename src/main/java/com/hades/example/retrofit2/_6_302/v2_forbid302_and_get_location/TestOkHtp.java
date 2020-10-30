package com.hades.example.retrofit2._6_302.v2_forbid302_and_get_location;

import com.hades.example.retrofit2.UrlConstants;
import com.hades.example.retrofit2.services.OkHttpClientUtils;
import com.hades.example.retrofit2.services.RetrofitUtils;
import okhttp3.*;
import okhttp3.logging.HttpLoggingInterceptor;
import org.jetbrains.annotations.NotNull;
import rx.Observer;

import java.io.IOException;

public class TestOkHtp {
    static HttpLoggingInterceptor.Logger logger = HttpLoggingInterceptor.Logger.DEFAULT;

    public static void main(String[] args) {
        forbid302_retrofit2();
//        forbid302();
    }

    private static void forbid302_retrofit2() {
        ISampleService service = RetrofitUtils
                .createRetrofit_forbid302(UrlConstants.BASE_URL_1)
                .create(ISampleService.class);

        service.getPosition(UrlConstants.URL_1)
                .subscribe(new Observer<retrofit2.Response<ResponseBody>>() {
                    @Override
                    public void onCompleted() {
                        System.out.println();
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("forbid302_retrofit2,onError,ex:" + e.getMessage());
                    }

                    @Override
                    public void onNext(retrofit2.Response<ResponseBody> responseBodyResponse) {
                        // Jump from redirect
                        String location = responseBodyResponse.headers().get("location");
                        // https://www.publicobject.com/helloworld.txt
                        System.out.println("forbid302_retrofit2,onNext,location:" + location);
                    }
                });

    }

    private static void forbid302() {
        logger.log("forbid302:-------->");
        try {
            Request request = new Request.Builder()
                    .url("http://www.publicobject.com/helloworld.txt")
                    .header("User-Agent", "OkHttp Example")
                    .header("Connection", "close")
                    .build();

            OkHttpClient client = new OkHttpClientUtils().createOkHttpClient_forbid302();
            client.newCall(request)
                    .enqueue(new Callback() {
                        @Override
                        public void onFailure(@NotNull Call call, @NotNull IOException e) {
                        }

                        @Override
                        public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                            logger.log("force302:onResponse ----->");
                            String location = response.header("location");
                            System.out.println(location);  // https://www.publicobject.com/helloworld.txt
                            // After get location from 302, close connection immediately
                            client.dispatcher().executorService().shutdownNow();
                            logger.log("force302:onResponse <-----");
                        }
                    });
        } catch (Exception exception) {
            System.err.println(exception);
        }
        logger.log("force302:<--------");
    }
}
