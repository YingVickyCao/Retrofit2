package com.hades.example.retrofit2._2_post;

import com.hades.example.retrofit2.services.LocalServiceCreator;
import com.hades.example.retrofit2.services.LocalService;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

public class Test3 {
    private static final String TAG = Test3.class.getSimpleName();

    public static void main(String[] args) throws IOException {
        new Test3().test();
    }

    private void test() {
        try {
            LocalServiceCreator localServiceCreator = new LocalServiceCreator();
            LocalService localService = localServiceCreator.init();
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
