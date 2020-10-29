package com.hades.example.retrofit2._6_302.v2_get_location;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class TestHttpURLConnection {
    public static void main(String[] args) {
        getLocation();
    }

    private static void getLocation() {
        try {
            String url = "http://www.publicobject.com/helloworld.txt";
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
