package com.example.proxy;

import okhttp3.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class NetTool {
    public final static int CONNECT_TIMEOUT = 60;
    public final static int READ_TIMEOUT = 100;
    public final static int WRITE_TIMEOUT = 60;
    public static final OkHttpClient client = new OkHttpClient.Builder()
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)//设置读取超时时间
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)//设置写的超时时间
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)//设置连接超时时间
            .build();
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    public static final String basebaseurl = "http://150.158.52.158:8080";

    public static String get(String url) throws IOException {
        Request request = new Request.Builder().url(url).get().build();
        Response response = client.newCall(request).execute();
        if (response.isSuccessful()) {
            String res = response.body().string();
            response.close();
            return res;
        } else {
            throw new IOException("Unexpected code " + response);
        }
    }

    public static String post(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(json,JSON);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        if (response.isSuccessful()) {
            String res = response.body().string();
            response.close();
            return res;
        } else {
            throw new IOException("Unexpected code " + response);
        }
    }
}
