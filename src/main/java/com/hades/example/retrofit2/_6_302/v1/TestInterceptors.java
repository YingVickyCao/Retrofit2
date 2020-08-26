package com.hades.example.retrofit2._6_302.v1;

import com.hades.example.java.lib.FileUtils;
import com.hades.example.retrofit2.services.LocalServiceCreator;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class TestInterceptors {
    public static void main(String[] args) {
        TestInterceptors testInterceptors = new TestInterceptors();
        testInterceptors.test();
    }

    private void test() {
        test_ApplicationInterceptors();
    }

    // 重定向

    /*
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
     */

    /*

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
     */
    private void test_ApplicationInterceptors() {
        try {
            Request request = new Request.Builder()
//                    .url("http://www.publicobject.com/helloworld.txt")
                    .url("https://www.publicobject.com/helloworld.txt")
                    .header("User-Agent", "OkHttp Example")
                    .build();

            Response response = new LocalServiceCreator()
                    .createOkHttpClient()
                    .newCall(request).execute();

            ResponseBody responseBody = response.body();
            FileUtils fileUtils = new FileUtils();
            String content = fileUtils.convertStreamToStr(responseBody.byteStream());
            System.out.println(content);
            if (responseBody != null) {
                responseBody.close();
            }
        } catch (Exception exception) {
            System.err.println(exception);
        }
    }
}
