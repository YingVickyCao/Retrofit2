package com.hades.example.retrofit2._1_get;

import com.hades.example.retrofit2.services.GitHubServiceCreator;
import com.hades.example.retrofit2.services.GitHubService;
import com.hades.example.retrofit2.User;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

public class Test extends GitHubServiceCreator {
    private static final String TAG = Test.class.getSimpleName();

    public static void main(String[] args) throws IOException {
        /**
         * https://api.github.com/users/list
         */
        new Test().init();
    }

    @Override
    protected void request(GitHubService service) throws IOException {
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

    private void response(Response<User> response) {
        if (null != response && response.isSuccessful()) {
            User user = response.body();
            if (null != user) {
                System.out.println(TAG + ",login=" + user.toString());
            }
        }
    }

}
