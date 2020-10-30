package com.hades.example.retrofit2._6_302.v2_forbid302_and_get_location;

import com.hades.example.retrofit2.UrlConstants;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class TestHttpURLConnection {
    public static void main(String[] args) {
        forbid302();
    }

    private static void forbid302() {
        try {
            String url = UrlConstants.URL_1;
            System.out.println("访问地址:" + url);
            URL serverUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) serverUrl.openConnection();
            conn.setRequestMethod("GET");
            // 必须设置false，否则会自动redirect到Location的地址
            conn.setInstanceFollowRedirects(false);
            String location = conn.getHeaderField("location");
            System.out.println(location); // https://www.publicobject.com/helloworld.txt
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
