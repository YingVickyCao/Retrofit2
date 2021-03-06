package com.hades.example.retrofit2._1_get;

import com.hades.example.retrofit2.UrlConstants;
import com.hades.example.retrofit2.bean.Repo;
import com.hades.example.retrofit2.services.GitHubService;
import com.hades.example.retrofit2.services.RetrofitUtils;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestPath {

    public static void main(String[] args) throws IOException {
        path1();
        path2();
    }

    private static void path1() {
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
        }).start();
    }

    private static void path2() {
        GitHubService service = RetrofitUtils.createRetrofit(UrlConstants.BASE_URL_3).create(GitHubService.class);
        new Thread(new Runnable() {
            @Override
            public void run() {
                Call<List<Repo>> call = service.listRepos(UrlConstants.GITHUB_USER_NAME, 2, 2, 60006312);
                Response<List<Repo>> response = null;
                try {
                    response = call.execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }
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
        }).start();
    }

    public static void path3() {
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
        }).start();
    }
}