package com.ljc.baselibrary.ui.p;

import com.ljc.baselibrary.ui.m.BaseModel;
import com.ljc.baselibrary.ui.m.BaseModelImpl;
import com.ljc.baselibrary.ui.v.BaseActivity;
import com.ljc.baselibrary.ui.v.BaseView;

/**
 * Created by lijiacheng on 16/6/29.
 */
public class BasePersenterImlp implements BasePersenter {
    private BaseView activity;
    private BaseModel model;

    public BasePersenterImlp(BaseActivity activity) {
        this.activity = activity;
        model = new BaseModelImpl();
    }
}
