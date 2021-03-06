package com.hades.example.retrofit2._2_post;

import com.hades.example.retrofit2.bean.LoginResult;
import com.hades.example.retrofit2.services.LocalService;
import com.hades.example.retrofit2.services.RetrofitUtils;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

public class TestField {
    private static final String TAG = TestField.class.getSimpleName();

    public static void main(String[] args) throws IOException {
        new TestField().test();
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
                Call<LoginResult> call = service.login("test", "123456");
                Response<LoginResult> loginResult = null;
                try {
                    loginResult = call.execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (null != loginResult && loginResult.isSuccessful()) {
                    LoginResult result = loginResult.body();
                    if (null != result) {
                        System.out.println(TAG + ",result=" + result.toString());
                    }
                }
            }
        }).start();
    }
}
