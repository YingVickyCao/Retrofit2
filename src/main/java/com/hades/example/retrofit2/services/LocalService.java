package com.hades.example.retrofit2.services;

import com.hades.example.retrofit2._2_post.LoginResult;
import com.hades.example.retrofit2._2_post.User;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.*;
import rx.Observable;

import java.util.Map;

public interface LocalService {
    // http://localhost:7777/getSum?num1=5&num2=15
    @GET("getSum")
    Call<Integer> getSum(@Query("num1") int num1, @Query("num2") int num2);

    /*
       Request URL: http://localhost:7777/login
       Request Method: POST
       From Data :
               {"name":"test","pwd":"123456"}
               name=test&pwd=123456
       Content-Type: application/json; charset=utf8
     */
    @POST("login")
    @Headers("Content-Type: application/json; charset=utf8")
    Call<LoginResult> login(@Body User user);


    /**
     * <pre>
     * Server:
     * http://localhost:7777/login
     * POST
     * name=test&pwd=123456
     *
     * HTML submit:
     * name=test&pwd=123456
     * </pre>
     */
    @FormUrlEncoded
    @POST("login")
    Call<LoginResult> login(@FieldMap Map<String, String> map);

    /**
     * <pre>
     * Server:
     * http://localhost:8888/login
     * POST
     * name=test&pwd=123456
     *
     * HTML submit:
     * name=test&pwd=123456
     * </pre>
     */
    @FormUrlEncoded
    @POST("login")
    Call<LoginResult> login(@Field("name") String name, @Field("pwd") String pwd);

    @POST("login")
    Observable<Response<ResponseBody>> login3(@FieldMap Map<String, String> map);
}