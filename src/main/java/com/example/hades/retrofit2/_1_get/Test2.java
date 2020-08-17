package com.example.hades.retrofit2._1_get;

import com.example.hades.retrofit2.services.LocalServiceCreator;
import com.example.hades.retrofit2.services.LocalService;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

public class Test2 extends LocalServiceCreator {
    private static final String TAG = Test2.class.getSimpleName();

    public static void main(String[] args) throws IOException {
        new Test2().init();
    }

    @Override
    protected void request(LocalService service) throws IOException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                /**
                 * http://localhost:7777/getSum?num1=5&num2=15
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
