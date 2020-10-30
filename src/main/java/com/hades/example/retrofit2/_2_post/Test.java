package com.hades.example.retrofit2._2_post;

import com.hades.example.retrofit2.services.LocalService;
import com.hades.example.retrofit2.services.RetrofitUtils;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Test {
    private static final String TAG = Test.class.getSimpleName();

    public static void main(String[] args) throws IOException {
        new Test().test();
    }


    private void test() {
        try {
            LocalService localService = RetrofitUtils.createRetrofit(LocalService.BASE_URL).create(LocalService.class);
            request(localService);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    protected void request(LocalService service) throws IOException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Map<String,String> map = new HashMap<>();
                map.put("name","test");
                map.put("pwd","123456");
                Call<LoginResult> call = service.login(map);
                Response<LoginResult> loginResult = null;
                try {
                    loginResult = call.execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                response(loginResult);
            }
        }).start();
    }

    private void response(Response<LoginResult> loginResult) {
        if (null != loginResult && loginResult.isSuccessful()) {
            LoginResult result = loginResult.body();
            if (null != result) {
                System.out.println(TAG + ",result=" + result.toString());
            }
        }
    }

}
