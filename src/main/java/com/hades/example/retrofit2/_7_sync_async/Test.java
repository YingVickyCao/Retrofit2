package com.hades.example.retrofit2._7_sync_async;

import com.hades.example.java.lib.FileUtils;
import com.hades.example.retrofit2.services.LocalService;
import com.hades.example.retrofit2.services.RetrofitUtils;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Subscriber;

import java.io.IOException;

public class Test {
    private static final String TAG = Test.class.getSimpleName();
    private LocalService localService;

    public static void main(String[] args) throws IOException {
        Test test = new Test();
        test.init();

        test.hello_sync();
        test.hello_async();
        test.hello3_sync();
    }

    private void init() {
        localService = RetrofitUtils.createRetrofit(LocalService.BASE_URL).create(LocalService.class);
    }

    protected void hello_sync() {
        try {
            Call<String> call = localService.hello();
            Response<String> response = call.execute();
            if (response.isSuccessful()) {
                String result = response.body();
                if (null != result) {
                    // thread id=main,thread name=1
                    System.out.println("hello,thread id=" + Thread.currentThread().getName() + ",thread name=" + Thread.currentThread().getId());
                    // World
                    System.out.println("hello,result=" + result);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void hello_async() {
        try {
            Call<String> call = localService.hello();
            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    if (response.isSuccessful()) {
                        String result = response.body();
                        if (null != result) {
                            // thread id=OkHttp http://localhost:7777/...,thread name=11
                            System.out.println("hello,thread id=" + Thread.currentThread().getName() + ",thread name=" + Thread.currentThread().getId());
                            // World
                            System.out.println("hello,result=" + result);
                        }
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void hello3_sync() {
        try {
            localService.hello3().subscribe(new Subscriber<Response<ResponseBody>>() {
                @Override
                public void onCompleted() {
                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(Response<ResponseBody> responseBodyResponse) {
                    String result = null;
                    try {
                        result = new FileUtils().convertStreamToStr(responseBodyResponse.body().byteStream());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (null != result) {
                        // thread id=main,thread name=1
                        System.out.println("hello,thread id=" + Thread.currentThread().getName() + ",thread name=" + Thread.currentThread().getId());
                        // World
                        System.out.println("hello,result=" + result);
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
