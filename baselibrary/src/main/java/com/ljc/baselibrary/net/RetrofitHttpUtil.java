package com.ljc.baselibrary.net;

import android.app.ProgressDialog;
import android.content.Context;

import com.ljc.baselibrary.ApplicationBase;
import com.ljc.baselibrary.R;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lijiacheng on 16/7/21.
 */
public class RetrofitHttpUtil<T> implements Callback<T> {
    private static RetrofitHttpUtil retrofitHttpUtil = null;
    private OkHttpClient mHttpClient = null;
    private Retrofit retrofit = null;
    private Callback<T> callback;
    private Context mContext;
    private ProgressDialog dialog;

    public RetrofitHttpUtil() {
        mHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .build();
        retrofit = new Retrofit.Builder()
                .client(mHttpClient)
                .baseUrl(ApplicationBase.getInstance().getApplicationContext().getString(R.string.baseUrl))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mContext = ApplicationBase.getInstance().getApplicationContext();
        dialog = new ProgressDialog(mContext);
        dialog.setMessage(mContext.getString(R.string.text_loading));
    }

    public static RetrofitHttpUtil getInstance() {
        if (retrofitHttpUtil == null) {
            synchronized (RetrofitHttpUtil.class) {
                retrofitHttpUtil = new RetrofitHttpUtil();
            }
        }
        return retrofitHttpUtil;
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }

    public void httpRequest(Call<T> call, Callback<T> callback) {
        cancelTag(call);
        if (dialog != null) {
            dialog.show();
        }
        this.callback = callback;
        call.enqueue(this);
    }

    private void cancelTag(Call<T> call) {
        call.cancel();
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        cancleDialog();
        callback.onResponse(call, response);
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        cancleDialog();
        callback.onFailure(call, t);
    }

    private void cancleDialog() {
        if (dialog != null) {
            dialog.cancel();
        }
    }
}
