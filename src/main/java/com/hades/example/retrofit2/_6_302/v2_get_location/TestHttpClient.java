package com.hades.example.retrofit2._6_302.v2_get_location;

import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;

public class TestHttpClient {
    public static void main(String[] args) throws IOException {
        getPosition();
    }

    private static void getPosition() throws IOException {
        RequestConfig config = RequestConfig.custom()
                .setRedirectsEnabled(false) //不允许重定向
                .build();

        CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(config).build();
        HttpResponse response = httpClient.execute(new HttpGet("http://www.publicobject.com/helloworld.txt"));
        String location = response.getFirstHeader("location").getValue();
        System.out.println(location); // https://www.publicobject.com/helloworld.txt
        httpClient.close();
    }
}
