package com.hades.example.retrofit2._2_post;

import com.hades.example.retrofit2.bean.Issue;
import com.hades.example.retrofit2.UrlConstants;
import com.hades.example.retrofit2.bean.LoginResult;
import com.hades.example.retrofit2.bean.Repo;
import com.hades.example.retrofit2.bean.User;
import com.hades.example.retrofit2.services.GitHubService;
import com.hades.example.retrofit2.services.LocalService;
import com.hades.example.retrofit2.services.RetrofitUtils;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

public class TestBody {
    private static final String TAG = TestBody.class.getSimpleName();

    public static void main(String[] args) throws IOException {
        body1();
        body2();
        body3();
        body4();
    }

    private static void body1() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                LocalService service = RetrofitUtils.createRetrofit(LocalService.BASE_URL).create(LocalService.class);
                Call<LoginResult> call = service.login(new User("test", "123456"));
                Response<LoginResult> loginResult = null;
                try {
                    loginResult = call.execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (null != loginResult && loginResult.isSuccessful()) {
                    LoginResult result = loginResult.body();
                    if (null != result) {
                        System.out.println(TAG + ",result=" + result.toString());
                    }
                }
            }
        }).start();
    }

    private static void body2() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                LocalService service = RetrofitUtils.createRetrofit(LocalService.BASE_URL).create(LocalService.class);
                Call<LoginResult> call = service.login_2(new User("test", "123456"));
                Response<LoginResult> loginResult = null;
                try {
                    loginResult = call.execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (null != loginResult && loginResult.isSuccessful()) {
                    LoginResult result = loginResult.body();
                    if (null != result) {
                        System.out.println(TAG + ",result=" + result.toString());
                    }
                }
            }
        }).start();
    }

    private static void body3() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                LocalService service = RetrofitUtils.createRetrofit(LocalService.BASE_URL).create(LocalService.class);
                Call<LoginResult> call = service.login_3(new User("test", "123456"));
                Response<LoginResult> loginResult = null;
                try {
                    loginResult = call.execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (null != loginResult && loginResult.isSuccessful()) {
                    LoginResult result = loginResult.body();
                    if (null != result) {
                        System.out.println(TAG + ",result=" + result.toString());
                    }
                }
            }
        }).start();
    }

    private static void body4() {
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

                if (null != response && null != response.body() && response.isSuccessful()) {
                    System.out.println(response.toString());
                }
            }
        }).start();
    }
}
