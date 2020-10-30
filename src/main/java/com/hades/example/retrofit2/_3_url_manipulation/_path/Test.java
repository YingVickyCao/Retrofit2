package com.hades.example.retrofit2._3_url_manipulation._path;

import com.hades.example.retrofit2.UrlConstants;
import com.hades.example.retrofit2._1_get.Repo;
import com.hades.example.retrofit2.services.GitHubService;
import com.hades.example.retrofit2.services.RetrofitUtils;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class Test {

    public static void main(String[] args) throws IOException {
        GitHubService service = RetrofitUtils.createRetrofit(UrlConstants.BASE_URL_3).create(GitHubService.class);
        new Thread(new Runnable() {
            @Override
            public void run() {
                Call<List<Repo>> call = service.listRepos(UrlConstants.GITHUB_USER_NAME);
                Response<List<Repo>> response = null;
                try {
                    response = call.execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                response(response);
            }
        }).start();
    }

    private static void response(Response<List<Repo>> response) {
        if (null != response && response.isSuccessful()) {
            List<Repo> info = response.body();
            if (null != info && !info.isEmpty()) {
                Collections.sort(info);
                for (Repo repo : info) {
                    System.out.println(repo.toString());
                }
            }
        }
    }
}