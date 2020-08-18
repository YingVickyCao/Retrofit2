package com.hades.example.retrofit2._3_url_manipulation._querry_parameters;

import com.hades.example.retrofit2.services.GitHubServiceCreator;
import com.hades.example.retrofit2.services.GitHubService;
import com.hades.example.retrofit2.User;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;

public class Test extends GitHubServiceCreator {

    public static void main(String[] args) throws IOException {
        new Test().init();
    }

    /**
     * https://api.github.com/users?since=135
     */
    protected void request(GitHubService service) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Call<List<User>> call = service.listUsersSinceID(17293752);
                Response<List<User>> response = null;
                try {
                    response = call.execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                response(response);
            }
        }).start();
    }

    private void response(Response<List<User>> response) {
        if (null != response && response.isSuccessful()) {
            List<User> list = response.body();
            if (null != list && !list.isEmpty()) {
                for (User repo : list) {
                    System.out.println(repo.toString());
                }
            }
        }
    }
}
