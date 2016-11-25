package com.ljc.projectmodel.net;

import com.ljc.projectmodel.beans.UserBean;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by lijiacheng on 16/7/22.
 */
public interface InterfaceApi {
    @Headers({
            "appcode: fdd",
            "appversion: 1.0",
            "languagetype:zh-cn",
            "devicetype:android",
            "devicemodel:phone",
            "sys:iphones5s",
            "sysversion:7.1.2",
            "deviceidentifier:deviceidentifier",
            "service:/http/user",
            "action:checkvercode.action",

    })
    @POST("users/{user}")
    Call<UserBean> userInfo(@Path("userId") String userId);

    @FormUrlEncoded
    @POST("user/login")
    Call<UserBean> login(@Field("mobile") String mobile, @Field("password") String password);
}