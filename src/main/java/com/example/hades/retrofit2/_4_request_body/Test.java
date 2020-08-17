package com.example.hades.retrofit2._4_request_body;

import com.example.hades.retrofit2.services.GitHubService;
import com.example.hades.retrofit2.Issue;
import com.example.hades.retrofit2.services.GitHubServiceCreator;
import com.example.hades.retrofit2._1_get.Repo;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

public class Test extends GitHubServiceCreator {
    public static void main(String[] args) throws IOException {
        new Test().init();
    }

    @Override
    protected void request(GitHubService service) throws IOException {
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

    private void response(Response<Integer> response) {
        if (null != response && null != response.body() && response.isSuccessful()) {
            System.out.println(response.toString());
        }
    }
}
