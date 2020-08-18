package com.hades.example.retrofit2._3_url_manipulation._querry_parameters;

import com.hades.example.retrofit2.services.GitHubServiceCreator;
import com.hades.example.retrofit2.services.GitHubService;
import com.hades.example.retrofit2._1_get.Repo;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class Test2 extends GitHubServiceCreator {

    public static void main(String[] args) throws IOException {
        new Test2().init();
    }

    /**
     * https://api.github.com/users/YingVickyCao/repos?page=2&per_page=2&id=60006312
     */
    protected void request(GitHubService service) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Call<List<Repo>> call = service.listRepos(USER_NAME, 2, 2, 60006312);
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

    private void response(Response<List<Repo>> response) {
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