package com.ljc.baselibrary.net;

import java.io.Serializable;

/**
 * Created by lijiacheng on 16/7/22.
 */
public class ResponseBean<T> implements Serializable {
    protected String code;
    protected String error_msg;
    protected String msg;
    protected T result;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getError_msg() {
        return error_msg;
    }

    public void setError_msg(String error_msg) {
        this.error_msg = error_msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
