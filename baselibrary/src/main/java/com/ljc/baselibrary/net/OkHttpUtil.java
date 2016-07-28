package com.ljc.baselibrary.net;


import com.ljc.baselibrary.utils.LogUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Bassam on 5/15/16.
 */
public class OkHttpUtil implements Callback {
    private Map<String, Callback> callbackMap;//接口回调队列
    private OkHttpClient mOkHttpClient;//请求客户端
    private static OkHttpUtil instance;

    public static OkHttpUtil getInstance() {
        if (instance == null) {
            synchronized (OkHttpUtil.class) {
                if (instance == null) {
                    instance = new OkHttpUtil();
                }
            }
        }
        return instance;
    }

    public OkHttpUtil() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(10, TimeUnit.SECONDS);
        mOkHttpClient = builder.build();
        callbackMap = new HashMap<>();
    }

    public void httpRequest(Request request, Callback callback) {
        cancelTag(request.tag());
        Call call = mOkHttpClient.newCall(request);
        LogUtils.d("开始网络请求");
        call.enqueue(this);
        if (callback != null) {
            callbackMap.put(request.tag().toString(), callback);
        }
    }

    public void cancelTag(Object tag) {
        for (Call call : mOkHttpClient.dispatcher().queuedCalls()) {
            if (tag.equals(call.request().tag())) {
                call.cancel();
            }
        }
        for (Call call : mOkHttpClient.dispatcher().runningCalls()) {
            if (tag.equals(call.request().tag())) {
                call.cancel();
            }
        }
        LogUtils.d("清理指定的网络请求:");
    }

    public void cancelAllRequest() {
        LogUtils.d("清理所有的网络请求:");
        mOkHttpClient.dispatcher().cancelAll();
    }

    @Override
    public void onFailure(Call call, IOException e) {
        LogUtils.d("网络请求失败");
        if (callbackMap.containsKey(call.request().tag().toString()) && callbackMap.get(call.request().tag().toString()) != null) {
            callbackMap.get(call.request().tag().toString()).onFailure(call, e);
        }
        callbackMap.remove(call.request().tag().toString());
    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
        LogUtils.d("回调key：" + call.request().tag().toString());
        if (callbackMap.containsKey(call.request().tag().toString()) && callbackMap.get(call.request().tag().toString()) != null) {
            callbackMap.get(call.request().tag().toString()).onResponse(call, response);
        }
        callbackMap.remove(call.request().tag().toString());
    }
}

