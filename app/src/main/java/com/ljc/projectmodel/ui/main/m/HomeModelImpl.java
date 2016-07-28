package com.ljc.projectmodel.ui.main.m;

import android.content.Context;

import com.ljc.baselibrary.ApplicationBase;

/**
 * Created by lijiacheng on 16/6/29.
 */
public class HomeModelImpl implements HomeModel {
    private Context mContext;

    public HomeModelImpl() {
        mContext = ApplicationBase.getInstance().getApplicationContext();
    }
}
