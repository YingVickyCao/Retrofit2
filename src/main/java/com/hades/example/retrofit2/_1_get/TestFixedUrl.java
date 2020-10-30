package com.hades.example.retrofit2._1_get;

import com.hades.example.retrofit2.UrlConstants;
import com.hades.example.retrofit2.bean.User2;
import com.hades.example.retrofit2.services.GitHubService;
import com.hades.example.retrofit2.services.RetrofitUtils;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

public class TestFixedUrl {
    private static final String TAG = TestFixedUrl.class.getSimpleName();

    public static void main(String[] args) throws IOException {
        /**
         * https://api.github.com/users/list
         */
        GitHubService service = RetrofitUtils.createRetrofit(UrlConstants.BASE_URL_3).create(GitHubService.class);
        new Thread(new Runnable() {
            @Override
            public void run() {
                Call<User2> call = service.listUsers();
                Response<User2> response = null;
                try {
                    response = call.execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (null != response && response.isSuccessful()) {
                    User2 user2 = response.body();
                    if (null != user2) {
                        System.out.println(TAG + ",login=" + user2.toString());
                    }
                }
            }
        }).start();
    }

    public static void fixed_url() throws IOException {
        /**
         * https://api.github.com/users/list
         */
        GitHubService service = RetrofitUtils.createRetrofit(UrlConstants.BASE_URL_3).create(GitHubService.class);
        new Thread(new Runnable() {
            @Override
            public void run() {
                Call<User2> call = service.listUsersWithSpecifyParametersInURL();
                Response<User2> response = null;
                try {
                    response = call.execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (null != response && response.isSuccessful()) {
                    User2 user2 = response.body();
                    if (null != user2) {
                        System.out.println(TAG + ",login=" + user2.toString());
                    }
                }
            }
        }).start();
    }
}
