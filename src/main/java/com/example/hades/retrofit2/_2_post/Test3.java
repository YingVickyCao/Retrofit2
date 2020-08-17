package com.example.hades.retrofit2._2_post;

import com.example.hades.retrofit2.services.LocalServiceCreator;
import com.example.hades.retrofit2.services.LocalService;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

public class Test3 extends LocalServiceCreator {
    private static final String TAG = Test3.class.getSimpleName();

    public static void main(String[] args) throws IOException {
        new Test3().init();
    }

    @Override
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
