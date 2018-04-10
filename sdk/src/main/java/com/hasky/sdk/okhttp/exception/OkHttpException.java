package com.hasky.sdk.okhttp.exception;

/**
 * Created by GPT-2964 on 2018/4/7.
 * 自定义异常类
 * 返回ecode和emsg到业务层
 */

public class OkHttpException extends Exception {
    private static final long serialVersionUID = 1L;

    private int ecode;
    private Object emsg;

    public OkHttpException(int ecode, Object emsg) {
        this.ecode = ecode;
        this.emsg = emsg;
    }

    public int getEcode() {
        return ecode;
    }

    public void setEcode(int ecode) {
        this.ecode = ecode;
    }

    public Object getEmsg() {
        return emsg;
    }

    public void setEmsg(Object emsg) {
        this.emsg = emsg;
    }
}
