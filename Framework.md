# Framework

![retrofit2](https://yingvickycao.github.io/img/retrofit2.jpg)

Retrofit2 底层的网络请求库是基于 OKHTTP 的.  
Retrofit2:对接口的封装  
OKHTTP:处理网络请求,包括拦截功能

# 1 OkHttp3

https://square.github.io/okhttp/

## Interceptor(拦截器)

https://square.github.io/okhttp/interceptors/  
https://www.jianshu.com/p/fc4d4348dc58

![interceptors](https://square.github.io/okhttp/images/interceptors@2x.png)

| Method                | Type        |
| --------------------- | ----------- |
| addNetworkInterceptor | network     |
| addInterceptor        | application |

- 每一次请求通过 chain.request()执行得到 Request，并使用 chain.proceed(request)得到 Response，包括每次的 302 jump

```java
public class CustomLoggingInterceptor implements Interceptor {
    HttpLoggingInterceptor.Logger logger = HttpLoggingInterceptor.Logger.DEFAULT;

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);
        return response;
    }
```

### 302

- 302 通常用在跨 domian 请求

- 默认有 Interceptor。 不添加自定义 Interceptor ，也能执行 302 跳转，但没有 log。

### Application Interceptors

- If having 302, 自动 跳转 location 处理，但不打印 location 的中间请求。
- 允许 short-circuit (短路):  
   Chain.proceed()不需要一定要调用去服务器请求，但是必须还是需要返回 Respond 实例。  
   实例从哪里来？  
   本地缓存 / 服务器请求 来 获取响应实例返回给客户端

- 重写 application interceptor 添？  
  设置 Header，包括缓存时间等  
  打印 code  
  发送 tracking wiki  
  打印 error 日志

### Network Interceptors

If having 302, 自动 跳转 location 处理，打印 location 的中间请求。

- 跳转不一定只有一次.  
  e.g.,
  ```
  http://www.publicobject.com/helloworld.txt
  -> https://www.publicobject.com/helloworld.txt
  - https://publicobject.com/helloworld.txt
  ```

```
Application Interceptors

Aug 20, 2020 11:08:28 AM okhttp3.internal.platform.Platform log
INFO: Sending request https://www.publicobject.com/helloworld.txt on null
User-Agent: OkHttp Example

Aug 20, 2020 11:08:33 AM okhttp3.internal.platform.Platform log
INFO: Received response for https://publicobject.com/helloworld.txt in 5792.5ms
status code 200
Server: nginx/1.10.3 (Ubuntu)
Date: Thu, 20 Aug 2020 03:08:33 GMT
Content-Type: text/plain
Content-Length: 1759
Last-Modified: Tue, 27 May 2014 02:35:47 GMT
Connection: keep-alive
ETag: "5383fa03-6df"
Accept-Ranges: bytes
```

```
Network Interceptors

    Aug 20, 2020 11:12:04 AM okhttp3.internal.platform.Platform log
    INFO: Sending request https://www.publicobject.com/helloworld.txt on Connection{www.publicobject.com:443, proxy=DIRECT hostAddress=www.publicobject.com/54.187.32.157:443 cipherSuite=TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384 protocol=http/1.1}
    User-Agent: OkHttp Example
    Host: www.publicobject.com
    Connection: Keep-Alive
    Accept-Encoding: gzip

    Aug 20, 2020 11:12:05 AM okhttp3.internal.platform.Platform log
    INFO: Received response for https://www.publicobject.com/helloworld.txt in 845.8ms
    status code 301
    Server: nginx/1.10.3 (Ubuntu)
    Date: Thu, 20 Aug 2020 03:12:04 GMT
    Content-Type: text/html
    Content-Length: 194
    Connection: keep-alive
    Location: https://publicobject.com/helloworld.txt

    Aug 20, 2020 11:12:06 AM okhttp3.internal.platform.Platform log
    INFO: Sending request https://publicobject.com/helloworld.txt on Connection{publicobject.com:443, proxy=DIRECT hostAddress=publicobject.com/54.187.32.157:443 cipherSuite=TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384 protocol=http/1.1}
    User-Agent: OkHttp Example
    Host: publicobject.com
    Connection: Keep-Alive
    Accept-Encoding: gzip

    Aug 20, 2020 11:12:07 AM okhttp3.internal.platform.Platform log
    INFO: Received response for https://publicobject.com/helloworld.txt in 1008.1ms
    status code 200
    Server: nginx/1.10.3 (Ubuntu)
    Date: Thu, 20 Aug 2020 03:12:06 GMT
    Content-Type: text/plain
    Content-Length: 1759
    Last-Modified: Tue, 27 May 2014 02:35:47 GMT
    Connection: keep-alive
    ETag: "5383fa03-6df"
    Accept-Ranges: bytes


    BUILD SUCCESSFUL in 7s
    2 actionable tasks: 2 executed
    11:12:07 AM: Task execution finished 'TestInterceptors.main()'.
```
