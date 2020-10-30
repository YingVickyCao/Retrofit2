package com.hades.example.retrofit2._4_request_body;

import com.hades.example.retrofit2.Issue;
import com.hades.example.retrofit2.UrlConstants;
import com.hades.example.retrofit2._1_get.Repo;
import com.hades.example.retrofit2.services.GitHubService;
import com.hades.example.retrofit2.services.RetrofitUtils;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException {
        GitHubService service = RetrofitUtils.createRetrofit(UrlConstants.BASE_URL_3).create(GitHubService.class);
        Issue issue = new Issue();
        new Thread(new Runnable() {
            @Override
            public void run() {
//                Call<ResponseIssue> call = service.createIssue("YingVickyCao", "GitTest", issue);
                Repo repo = new Repo(53325230L, "android-art-res");
                Call<Integer> call = service.createIssue(repo);
                Response<Integer> response = null;
                try {
                    response = call.execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if (null != response) {
                    response(response);
                }
            }
        }).start();
    }

    private static void response(Response<Integer> response) {
        if (null != response && null != response.body() && response.isSuccessful()) {
            System.out.println(response.toString());
        }
    }
}
