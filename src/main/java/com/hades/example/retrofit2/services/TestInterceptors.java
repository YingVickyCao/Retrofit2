package com.hades.example.retrofit2.services;

import okhttp3.Request;
import okhttp3.Response;

public class TestInterceptors {
    public static void main(String[] args) {
        TestInterceptors testInterceptors = new TestInterceptors();
        testInterceptors.test();
    }

    private void test() {
        test_ApplicationInterceptors();
    }

    private void test_ApplicationInterceptors() {
        try {
            Request request = new Request.Builder()
                    .url("https://www.publicobject.com/helloworld.txt")
                    .header("User-Agent", "OkHttp Example")
                    .build();

            Response response = new LocalServiceCreator()
                    .createOkHttpClient()
                    .newCall(request).execute();
            response.body().close();
        } catch (Exception exception) {
            System.err.println(exception);
        }
    }
}
