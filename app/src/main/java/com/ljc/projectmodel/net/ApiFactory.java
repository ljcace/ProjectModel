package com.ljc.projectmodel.net;

import com.ljc.baselibrary.net.RetrofitHttpUtil;

/**
 * Created by lijiacheng on 16/7/22.
 */
public enum ApiFactory {
    INSTANCE;
    private final InterfaceApi interfaceApi;

    ApiFactory() {
        interfaceApi = RetrofitHttpUtil.getInstance().getRetrofit().create(InterfaceApi.class);
    }

    public InterfaceApi getInterfaceApi() {
        return interfaceApi;
    }
}
