package com.hasky.sdk.okhttp.request;

import java.util.HashMap;

/**
 * Created by GPT-2964 on 2018/4/6.
 *
 * @function 封装所有的请求参数到HashMap中
 */

public class RequestParams {
    public HashMap<String, String> urlParams = new HashMap<>();
    public HashMap<String, Object> fileParams = new HashMap<>();

    public RequestParams(HashMap<String, String> urlParams, HashMap<String, Object> fileParams) {
        this.urlParams = urlParams;
        this.fileParams = fileParams;
    }

    public void put(String key, String value) {
        if (key != null && value != null) {
            urlParams.put(key, value);
        }
    }

    public void put(String key, Object object) {
        if (key != null && object != null) {
            fileParams.put(key, object);
        }
    }

    public boolean hasParams() {
        if (urlParams != null && fileParams != null) {
            return true;
        }
        return false;
//        if (urlParams.size() > 0 || fileParams.size() > 0) {
//            return true;
//        }
//        return false;
    }
}
