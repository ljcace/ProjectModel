package com.ljc.projectmodel.ui.menu4.m;

import com.ljc.projectmodel.beans.LinkManBean;

import java.util.ArrayList;

/**
 * Created by lijiacheng on 16/6/30.
 */
public interface LinkmanModel {
    ArrayList<LinkManBean> getLinkman();
    int getMoveToIndex(ArrayList<LinkManBean> list ,String title);
}
