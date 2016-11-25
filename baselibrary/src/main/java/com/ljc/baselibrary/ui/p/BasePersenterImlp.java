package com.ljc.baselibrary.ui.p;

import com.ljc.baselibrary.ui.v.BaseActivity;
import com.ljc.baselibrary.ui.v.BaseView;

/**
 * Created by lijiacheng on 16/6/29.
 */
public class BasePersenterImlp implements BasePersenter {
    private BaseView activity;

    public BasePersenterImlp(BaseActivity activity) {
        this.activity = activity;
    }
}
