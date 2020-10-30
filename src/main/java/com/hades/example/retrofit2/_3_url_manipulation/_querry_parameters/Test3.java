package com.hades.example.retrofit2._3_url_manipulation._querry_parameters;

import com.hades.example.retrofit2.UrlConstants;
import com.hades.example.retrofit2._1_get.Repo;
import com.hades.example.retrofit2.services.GitHubService;
import com.hades.example.retrofit2.services.RetrofitUtils;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test3 {

    public static void main(String[] args) throws IOException {
        GitHubService service = RetrofitUtils.createRetrofit(UrlConstants.BASE_URL_3).create(GitHubService.class);
        new Thread(new Runnable() {
            @Override
            public void run() {
                Map<String, Integer> parmeters = new HashMap<>();
                parmeters.put("page", 2);
                parmeters.put("per_page", 2);
                parmeters.put("id", 60006312);

                Call<List<Repo>> call = service.listRepos(UrlConstants.GITHUB_USER_NAME, parmeters);
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