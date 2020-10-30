package com.hades.example.retrofit2._1_get;

import com.hades.example.retrofit2.UrlConstants;
import com.hades.example.retrofit2.User;
import com.hades.example.retrofit2.services.GitHubService;
import com.hades.example.retrofit2.services.RetrofitUtils;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

public class Test {
    private static final String TAG = Test.class.getSimpleName();

    public static void main(String[] args) throws IOException {
        /**
         * https://api.github.com/users/list
         */
        GitHubService service = RetrofitUtils.createRetrofit(UrlConstants.BASE_URL_3).create(GitHubService.class);
        new Thread(new Runnable() {
            @Override
            public void run() {
                Call<User> call = service.listUsers();
                Response<User> response = null;
                try {
                    response = call.execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                response(response);
            }
        }).start();
    }

    private static void response(Response<User> response) {
        if (null != response && response.isSuccessful()) {
            User user = response.body();
            if (null != user) {
                System.out.println(TAG + ",login=" + user.toString());
            }
        }
    }

}
