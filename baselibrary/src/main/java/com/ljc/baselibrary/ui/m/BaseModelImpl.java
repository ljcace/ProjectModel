package com.ljc.baselibrary.ui.m;

import android.content.Context;

import com.ljc.baselibrary.ApplicationBase;

/**
 * Created by lijiacheng on 16/6/29.
 */
public class BaseModelImpl implements BaseModel {
    private Context mContext;
    public BaseModelImpl() {
        mContext= ApplicationBase.getInstance().getApplicationContext();
    }
}
