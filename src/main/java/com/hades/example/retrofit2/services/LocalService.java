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
    /**
     * Test synchronous or asynchronous HTTP request,START
     */
    // http://localhost:7777/hello
    @GET("hello")
    Call<String> hello();

    // http://localhost:7777/hello
    @GET("hello")
    Observable<Response<ResponseBody>> hello3();

    /**
     * Test synchronous or asynchronous HTTP request,END
     */

    // http://localhost:7777/sum?num1=5&num2=15
    @GET("sum")
    Call<Integer> getSum(@Query("num1") int num1, @Query("num2") int num2);

    // http://localhost:7777/sum?num1=5&num2=15
    @GET("sum")
    Call<Integer> getSum(@QueryMap Map<String, Integer> map);

    // http://localhost:7777/sum?num1=5&num2=15
    @GET("sum")
    Call<Integer> getSum(@Query("num1") int num1, @QueryMap Map<String, Integer> map);

    // 302
    // http://localhost:7777/before_redirect/40
    @GET("/before_redirect/{num}")
//    Call<String> redirect(@Path("num") int num);
    Observable<Response<ResponseBody>> redirect(@Path("num") int num);

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
    @POST("login")
    @FormUrlEncoded
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
    @POST("login")
    @FormUrlEncoded
    Call<LoginResult> login(@Field("name") String name, @Field("pwd") String pwd);

    @POST("login")
    Observable<Response<ResponseBody>> login3(@FieldMap Map<String, String> map);
}