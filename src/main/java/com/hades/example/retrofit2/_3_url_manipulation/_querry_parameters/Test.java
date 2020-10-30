package com.hades.example.retrofit2._3_url_manipulation._querry_parameters;

import com.hades.example.retrofit2.UrlConstants;
import com.hades.example.retrofit2.User;
import com.hades.example.retrofit2.services.GitHubService;
import com.hades.example.retrofit2.services.RetrofitUtils;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;

public class Test {

    public static void main(String[] args) throws IOException {
        GitHubService service = RetrofitUtils.createRetrofit(UrlConstants.BASE_URL_3).create(GitHubService.class);
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
    
    private static void response(Response<List<User>> response) {
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
