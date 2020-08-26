package com.hades.example.retrofit2._6_302.v2;

import com.hades.example.retrofit2.services.LocalService;
import com.hades.example.retrofit2.services.LocalServiceCreator;
import okhttp3.ResponseBody;
import retrofit2.Response;
import rx.Observer;

import java.io.IOException;

public class Test302 {
    private static final String TAG = Test302.class.getSimpleName();

    public static void main(String[] args) {
        new Test302().test();
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
        /**
         * http://localhost:7777/getSum?num1=5&num2=15
         */
        service.redirect(5)
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onCompleted() {
                        System.out.println();
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println(e);
                    }

                    @Override
                    public void onNext(Response<ResponseBody> responseBodyResponse) {
                        System.out.println(responseBodyResponse);
                    }
                });
//                Call<String> call = service.redirect(5);
//                Response<String> response = null;
//                try {
//                    response = call.execute();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                response(response);
    }

    private void response(Response<String> response) {
        if (null != response && response.isSuccessful()) {
            String result = response.body();
            if (null != result) {
                System.out.println(TAG + ",result=" + result.toString());
            }
        }
    }
}
