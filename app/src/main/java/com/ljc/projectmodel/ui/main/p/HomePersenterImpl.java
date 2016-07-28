package com.ljc.projectmodel.ui.main.p;

import com.ljc.projectmodel.ui.main.m.HomeModel;
import com.ljc.projectmodel.ui.main.m.HomeModelImpl;
import com.ljc.projectmodel.ui.main.v.HomeActivity;
import com.ljc.projectmodel.ui.main.v.HomeView;

/**
 * Created by lijiacheng on 16/6/29.
 */
public class HomePersenterImpl implements HomePersenter {
    private HomeView activity;
    private HomeModel model;

    public HomePersenterImpl(HomeActivity activity) {
        this.activity = activity;
        model = new HomeModelImpl();
    }
}
