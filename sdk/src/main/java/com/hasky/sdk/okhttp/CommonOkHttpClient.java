package com.hasky.sdk.okhttp;

import com.hasky.sdk.okhttp.call.CallbackHandler;
import com.hasky.sdk.okhttp.response.JsonCallback;
import com.hasky.sdk.okhttp.call.OkHttpCallBack;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.Call;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by GPT-2964 on 2018/4/6.
 *
 * @function 发送请求、参数配置、https支持
 */

public class CommonOkHttpClient {

    private static final long TIME_OUT = 30;
    private static OkHttpClient mOkHttpClient;

    static {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        return null;
                    }
                })
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .hostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                })
                .followRedirects(true)
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .writeTimeout(TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(TIME_OUT, TimeUnit.SECONDS);
        mOkHttpClient = builder.build();
    }

    public static OkHttpClient getOkHttpClient() {
        return mOkHttpClient;
    }

    /*
    * 直接用Request作为参数，解耦
    * */
//    public static Call get(String url, RequestParams params) {
//        Call call = mOkHttpClient.newCall(CommonRequest.createGetRequest(url, params));
//        return call;
//    }
//
//    public static Call post(String url, RequestParams params) {
//        Call call = mOkHttpClient.newCall(CommonRequest.createPostRequest(url, params));
//        return call;
//    }

    /*
    * 异步请求数据，返回Call对象
    * */
    public static Call getCall(Request request, OkHttpCallBack okHttpCallBack) {
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new JsonCallback(new CallbackHandler(okHttpCallBack)));
        return call;
    }

    /*
    * 同步请求数据，返回Call对象
    * */
    public static Call getSyncCall(Request request) {
        Call call = mOkHttpClient.newCall(request);
        try {
            call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return call;
    }

    /*
    * 异步发送数据，返回自定义的OkHttpCallback对象
    * */
    public static Call postCall(Request request, OkHttpCallBack okHttpCallBack) {
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new JsonCallback(new CallbackHandler(okHttpCallBack)));
        return call;
    }

    /*
    * 同步发送请求，返回Call对象
    * */
    public static Call postSyncCall(Request request) {
        Call call = mOkHttpClient.newCall(request);
        try {
            call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return call;
    }
}
