package com.hasky.sdk.okhttp.response;

import android.os.Handler;
import android.os.Looper;

import com.hasky.sdk.okhttp.call.CallbackHandler;
import com.hasky.sdk.okhttp.call.OkHttpCallBack;
import com.hasky.sdk.okhttp.exception.OkHttpException;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by GPT-2964 on 2018/4/7.
 */

public class JsonCallback implements Callback {

    /*
    * 与服务器返回的字段的对应关系
    * */
    protected final String RESULT_CODE = "ecode";
    protected final int RESULT_CODE_VALUE = 0;
    protected final String ERROR_MSG = "emsg";
    protected final String EMPTY_MSG = "";
    /*
    * 自定义异常类型
    * */
    protected final int NETWORK_ERROR = -1;
    protected final int JSON_ERROR = -2;
    protected final int OTHER_ERROR = -3;
    /*
    * 进行消息转发
    * */
    private Handler deliverHandler;
    private OkHttpCallBack callBack;
    private Class<?> mClass;

    public JsonCallback(CallbackHandler callbackHandler) {
        this.callBack = callbackHandler.okHttpCallBack;
        this.mClass = callbackHandler.clazz;
        this.deliverHandler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void onFailure(Call call, final IOException e) {
        deliverHandler.post(new Runnable() {
            @Override
            public void run() {
                callBack.onFailure(new OkHttpException(NETWORK_ERROR, e));
            }
        });
    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
        final String result = response.body().string();
        deliverHandler.post(new Runnable() {
            @Override
            public void run() {
                handleResponse(result);
            }
        });
    }

    private void handleResponse(Object resultObj) {
        //为了保证代码健壮性
        if (resultObj == null || resultObj.toString().trim().equals("")) {
            callBack.onFailure(new OkHttpException(NETWORK_ERROR, EMPTY_MSG));
            return;
        }
        try {
            JSONObject result = new JSONObject(resultObj.toString());
            /*
            * 开始尝试解析json
            * */
            if (result.has(RESULT_CODE)) {
                /*
                * 与服务器约定好：若从json对象中取出的响应吗为0，则是正确的响应
                * */
                if (result.getInt(RESULT_CODE) == RESULT_CODE_VALUE) {
                    if (mClass == null) {
                        callBack.onSuccess(resultObj);
                    } else {
                        /*
                        * 将json对象转化为实体对象
                        * */
                        Object object = parseJson(result, mClass);
                        if (object != null) {
                            callBack.onSuccess(object);
                        } else {
                            /*
                            * 返回的不是合法的json
                            * */
                            callBack.onFailure(new OkHttpException(JSON_ERROR, EMPTY_MSG));
                        }
                    }
                } else {
                    /*
                    * 将服务器返回的异常回调到应用层去处理
                    * */
                    callBack.onFailure(new OkHttpException(OTHER_ERROR, result.get(RESULT_CODE)));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
            callBack.onFailure(new OkHttpException(OTHER_ERROR, e.getMessage()));
        }
//        if (mClass == null) {
//            okHttpCallBack.onSuccess(result);
//        } else {
//            //对数据进行疯狂操作
//            Object object = parseJson(result, mClass);
//            if (object != null) {
//                okHttpCallBack.onSuccess(object);
//            } else {
//                okHttpCallBack.onFailure(object);
//            }
//        }
    }

    private Object parseJson(Object result, Class<?> mClass) {
        /*
        * 解析json
        * */
        return new Object();
    }
}
