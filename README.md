# Retrofit2

- Code  
  [Retrofit2](https://gitee.com/YingVickyCao/Retrofit2)  
  [retrofit2-Android](https://gitee.com/YingVickyCao/Retrofit2-android)

- [Framework](Framework.md)

# 1 GET

## Simple

```java
// http://localhost:7777/News
@GET("News")
Call<NewsBean> getItem();
```

## Query value

```java
// http://localhost:7777/sum?num1=5&num2=15
@GET("sum")
Call<Integer> getSum(@Query("num1") int num1, @Query("num2") int num2);
```

```java
// http://localhost:7777/sum?num1=5&num2=15
@GET("sum")
Call<Integer> getSum(@QueryMap Map<String, Integer> map);
```

```java
// http://localhost:7777/sum?num1=5&num2=15
@GET("sum")
Call<Integer> getSum(@Query("num1") int num1,@QueryMap Map<String, Integer> map);
```

## URL having paramer

```java
// https://api.github.com/users/YingVickyCao/repos
@GET("users/{login}/repos")
Call<List<Repo>> listRepos(@Path("login") String user);
```

```java
// http://localhost:7777/News/1/类型1
// http://localhost:7777/News/{资讯id}/{类型}
@GET("News/{newsId}/{type}")
Call<NewsBean> getItem(@Path("newsId") String newsId， @Path("type") String type);
```

# 2 POST

## Post - send form data

```
@POST("login")
@FormUrlEncoded
Call<LoginResult> login(@FieldMap Map<String, String> map);
```

# 3 Download zip

```java
@GET
Call<ResponseBody> downloadFile(@Url String fileUrl);

// Way 2 : @Streaming for large file
@Streaming
@GET
Call<ResponseBody> downloadFile_Streaming(@Url String fileUrl);
```

Time 1: (MS) of Way 1 download 72.3kb  
Time 2: (MS) of Way 2 download 72.3kb

| No.     | Time 1 | Time 2 |
| ------- | ------ | ------ |
| 1       | 45     | 112    |
| 2       | 5      | 102    |
| 3       | 11     | 84     |
| 4       | 5      | 130    |
| 5       | 5      | 110    |
| 6       | 16     | 94     |
| 7       | 16     | 130    |
| 8       | 7      | 59     |
| 9       | 4      | 95     |
| 10      | 9      | 126    |
| 11      | 6      | 109    |
| 12      | 3      | 182    |
| 13      | 3      | 50     |
| 14      | 3      | 87     |
| 15      | 4      | 106    |
| 16      | 3      | 47     |
| 17      | 5      | 105    |
| Sum     | 150    | 1728   |
| Average | 8      | 105    |

Time 1: (MS) of Way 1 download 1.4 MB  
Time 2: (MS) of Way 2 download 1.4 MB

| No.     | Time | Time2   |
| ------- | ---- | ------- |
| 1       | 80   | 20596   |
| 2       | 79   | 14820   |
| 3       | 86   | 10930   |
| 4       | 103  | 21278   |
| 5       | 87   | 12973   |
| 6       | 97   | 12232   |
| 5       | 64   | 11866   |
| 6       | 72   | 5084    |
| 7       | 82   | 6114    |
| 8       | 70   | 5493    |
| 9       | 59   | 6867    |
| 10      | 55   | 8842    |
| 11      | 55   | 22555   |
| 12      | 59   | 12792   |
| 13      | 56   | 9273    |
| 14      | 80   | 20596   |
| 15      | 79   | 14820   |
| Sum     | 1263 | 217131  |
| Average | 84.2 | 14475.4 |

TBD:73.2MB

# 4 set Dynamic URL being not same with Base URL

```java
public interface API{
    @Get
    call<Uers> getUsers(@Url String url);

    @Post
    call<Uers> getUsers2(@Url String url);
}
```

# 5 Add Headers

- 动态添加

Way 1 : @Header

```
@GET("/")
Call<ResponseBody> foo(@Header("Accept-Language") String lang);
```

Way 2 : @HeaderMap

```
@GET("/search")
Call<ResponseBody> list(@HeaderMap Map<String, String> headers);
```

- 静态添加

Way 1 : @Headers

```java
@Headers("
Cache-Control: max-age=640000",
"Accept-Language":"en")
@GET("/tasks")
Call<List<Task>> getDataList();
```

Way 2 : @Headers

```java
@Headers({
    "X-Foo: Bar",
    "X-Ping: Pong"
})
@GET("/")
Call(ResponseBody) getData(@Query("id") String id);
```

# 6 Return value

```java
@GET("sum")
Call<Integer> getSum(@Query("num1") int num1, @Query("num2") int num2)

/

@GET("sum")
Observable<Response<ResponseBody>> getSum(@Query("num1") int num1, @Query("num2") int num2);

/
@GET("sum")
Integer getSum(@Query("num1") int num1, @Query("num2") int num2)
```

# ERROR

- `Q :ERROR:Caused by: java.lang.IllegalArgumentException: Could not locate call adapter for rx.Observable<okhttp3.ResponseBody>`

A :  
只有添加 RxJavaCallAdapterFactory，才可以使用  
`Observable<Response<ResponseBody>> redirect(@Path("num") int num);`

```java
Retrofit retrofit = new Retrofit.Builder()
    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
    .build();
```

# Refs

https://www.jianshu.com/p/3bbd12ba6309
