package com.ljc.projectmodel.net;

import com.ljc.baselibrary.net.RetrofitHttpUtil;

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

    public RetrofitAsyncTask() {
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
        retrofitHttpUtil.httpRequest(apiFactory.getInterfaceApi().userInfo(userId), this);
    }

    public void login(String mobile, String password, onNetResponseListener listener) {
        retrofitHttpUtil.httpRequest(apiFactory.getInterfaceApi().login(mobile, password), this);
    }

    @Override
    public void onResponse(Call call, Response response) {
    }

    @Override
    public void onFailure(Call call, Throwable t) {

    }

    public interface onNetResponseListener {
        void onSuccess();

        void onFailure();
    }
}
