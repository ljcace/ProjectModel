package com.ljc.projectmodel.ui.menu4.p;

import com.ljc.projectmodel.ui.menu4.m.LinkmanModel;
import com.ljc.projectmodel.ui.menu4.m.LinkmanModelImpl;
import com.ljc.projectmodel.ui.menu4.v.LinkmanActivityMain;
import com.ljc.projectmodel.ui.menu4.v.LinkmanView;

/**
 * Created by lijiacheng on 16/6/30.
 */
public class LinkmanPersenterImpl implements LinkmanPersenter {
    private LinkmanView activity;
    private LinkmanModel model;

    public LinkmanPersenterImpl(LinkmanActivityMain activity) {
        this.activity = activity;
        model = new LinkmanModelImpl();
    }

    @Override
    public void showLinkmanList() {
        activity.setLinkmanList(model.getLinkman());
    }

    @Override
    public int checkMoveToIndex(String title) {
        return model.getMoveToIndex(activity.getLinkmanList(), title);
    }
}
