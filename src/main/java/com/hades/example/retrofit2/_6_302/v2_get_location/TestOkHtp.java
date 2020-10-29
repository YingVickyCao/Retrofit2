package com.hades.example.retrofit2._6_302.v2_get_location;

import com.hades.example.retrofit2.services.OkHttpUtils;
import okhttp3.*;
import okhttp3.logging.HttpLoggingInterceptor;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class TestOkHtp {
    static HttpLoggingInterceptor.Logger logger = HttpLoggingInterceptor.Logger.DEFAULT;

    public static void main(String[] args) {
        force302();
    }

    private static void force302() {
        logger.log("force302:-------->");
        try {
            Request request = new Request.Builder()
                    .url("http://www.publicobject.com/helloworld.txt")
                    .header("User-Agent", "OkHttp Example")
                    .build();

            OkHttpClient client = new OkHttpUtils().createOkHttpClient_force302();
            client.newCall(request)
                    .enqueue(new Callback() {
                        @Override
                        public void onFailure(@NotNull Call call, @NotNull IOException e) {
                        }

                        @Override
                        public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                            logger.log("force302:onResponse ----->");
                            String location = response.header("location");
                            System.err.println(location);  // https://www.publicobject.com/helloworld.txt
                            ResponseBody responseBody = response.body();
                            if (responseBody != null) {
                                responseBody.close();
                            }
                            logger.log("force302:onResponse <-----");
                        }
                    });
        } catch (Exception exception) {
            System.err.println(exception);
        }
        logger.log("force302:<--------");
    }
}
