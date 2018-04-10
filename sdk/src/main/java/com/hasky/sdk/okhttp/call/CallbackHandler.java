package com.hasky.sdk.okhttp.call;

/**
 * Created by GPT-2964 on 2018/4/7.
 * 将响应回调事件和字节码封装，以完成Json对象到实体对象的转化
 */

public class CallbackHandler {
    public OkHttpCallBack okHttpCallBack = null;
    public Class<?> clazz = null;

    /*
    * 返回Class，在回调中将json转化成实体对象
    * */
    public CallbackHandler(OkHttpCallBack okHttpCallBack, Class<?> clazz) {
        this.okHttpCallBack = okHttpCallBack;
        this.clazz = clazz;
    }
    /*
    * 没有Class，则将服务器返回的数据直接扔到应用层
    * */
    public CallbackHandler(OkHttpCallBack okHttpCallBack) {
        this.okHttpCallBack = okHttpCallBack;
    }
}
