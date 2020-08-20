package com.hades.example.retrofit2._1_get;

import com.hades.example.retrofit2.services.LocalService;
import com.hades.example.retrofit2.services.LocalServiceCreator;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

public class Test2 {
    private static final String TAG = Test2.class.getSimpleName();

    public static void main(String[] args) throws IOException {
        new Test2().test();
    }

    private void test() {
        try {
            LocalServiceCreator localServiceCreator = new LocalServiceCreator();
            LocalService localService = localServiceCreator.init();
            request(localService);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    protected void request(LocalService service) throws IOException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                /**
                 * http://localhost:7777/sum?num1=5&num2=15
                 */
                Call<Integer> call = service.getSum(5, 15);
                Response<Integer> response = null;
                try {
                    response = call.execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                response(response);
            }
        }).start();
    }

    private void response(Response<Integer> response) {
        if (null != response && response.isSuccessful()) {
            Integer result = response.body();
            if (null != result) {
                System.out.println(TAG + ",result=" + result.toString());
            }
        }
    }

}
