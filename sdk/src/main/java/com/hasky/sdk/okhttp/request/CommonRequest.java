package com.hasky.sdk.okhttp.request;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by GPT-2964 on 2018/4/6.
 * 接收请求参数，生成Request对象
 */

public class CommonRequest {
    public static Request createPostRequest(String url, RequestParams params) {
        /*
        * 1.获取FormBody.Builder对象
        * 2.为FormBody.Builder对象添加待提交的参数信息
        * 3.调用build()方法创建FormBody对象
        * */
        FormBody.Builder bodyBuilder = new FormBody.Builder();
        if (params != null) {
            for (Map.Entry<String, String> map : params.urlParams.entrySet()) {
                bodyBuilder.add(map.getValue(), map.getKey());
            }
        }
        RequestBody body = bodyBuilder.build();
        /*
        * 4.创建Request对象，并传入url和RequestBody等信息
        * 5.返回Request对象
        * */
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        return request;
    }

    public static Request createGetRequest(String url, RequestParams params) {
        StringBuilder urlBuilder = new StringBuilder(url).append("?");
        if (params != null) {
            for (Map.Entry<String, String> map : params.urlParams.entrySet()) {
                urlBuilder = urlBuilder.append(map.getKey()).append("=").append(map.getValue()).append("&");
            }
        }
        Request request = new Request.Builder()
                .url(urlBuilder.substring(0,urlBuilder.length()-1))
                .get().build();
        return request;
    }
}
