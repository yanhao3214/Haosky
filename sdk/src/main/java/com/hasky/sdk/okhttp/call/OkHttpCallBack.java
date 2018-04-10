package com.hasky.sdk.okhttp.call;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by GPT-2964 on 2018/4/7.
 * 自定义事件监听：
 * 1.防止框架更新，名字改变
 * 2.便于扩展，下载监听等
 */

public interface OkHttpCallBack {
    /*
    * 请求失败回调事件处理
    * */
    void onFailure(Object object);
    /*
    * 请求成功回调事件处理
    * */
    void onSuccess(Object object);
}
