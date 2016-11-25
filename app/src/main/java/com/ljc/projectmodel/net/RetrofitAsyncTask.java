package com.ljc.projectmodel.net;

import com.ljc.baselibrary.net.RetrofitHttpUtil;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by lijiacheng on 16/7/22.
 */
public class RetrofitAsyncTask implements Callback {
    public static RetrofitAsyncTask retrofitAsyncTask = null;
    public RetrofitHttpUtil retrofitHttpUtil;
    public ApiFactory apiFactory;
    private HashMap<String, onNetResponseListener> listeners;

    public RetrofitAsyncTask() {
        listeners = new HashMap<>();
        retrofitHttpUtil = RetrofitHttpUtil.getInstance();
        apiFactory = ApiFactory.INSTANCE;
    }

    public static RetrofitAsyncTask getInstance() {
        if (retrofitAsyncTask == null) {
            synchronized (RetrofitAsyncTask.class) {
                retrofitAsyncTask = new RetrofitAsyncTask();
            }
        }
        return retrofitAsyncTask;
    }

    public void getUserMsg(String userId, onNetResponseListener listener) {
        Call call = apiFactory.getInterfaceApi().userInfo(userId);
        listeners.put(call.request().tag().toString(), listener);
        retrofitHttpUtil.httpRequest(call, this);
    }

    public void login(String mobile, String password, onNetResponseListener listener) {
        Call call = apiFactory.getInterfaceApi().login(mobile, password);
        listeners.put(call.request().tag().toString(), listener);
        retrofitHttpUtil.httpRequest(apiFactory.getInterfaceApi().login(mobile, password), this);
    }

    @Override
    public void onResponse(Call call, Response response) {
        String tag = call.request().tag().toString();
        onNetResponseListener listener = listeners.get(tag);
        if (response.isSuccessful()) {
            String result = response.body().toString();
            listener.onSuccess(result, tag);
        } else {
            String err_result = "";
            try {
                err_result = response.errorBody().string();
            } catch (Exception e) {
                e.printStackTrace();
            }
            listener.onFailure(err_result, tag);
        }
    }

    @Override
    public void onFailure(Call call, Throwable t) {

    }

    public interface onNetResponseListener {
        void onSuccess(String result, String tag);

        void onFailure(String err_result, String tag);
    }
}
