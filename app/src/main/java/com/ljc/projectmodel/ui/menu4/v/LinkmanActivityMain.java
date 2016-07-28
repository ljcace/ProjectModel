package com.ljc.projectmodel.ui.menu4.v;

import android.widget.ListView;

import com.ljc.baselibrary.widgets.SideBar;
import com.ljc.projectmodel.R;
import com.ljc.projectmodel.adapter.LinkManAdapter;
import com.ljc.projectmodel.beans.LinkManBean;
import com.ljc.projectmodel.ui.MainActivity;
import com.ljc.projectmodel.ui.menu4.p.LinkmanPersenter;
import com.ljc.projectmodel.ui.menu4.p.LinkmanPersenterImpl;
import com.ljc.projectmodel.widgets.CommTitle;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by lijiacheng on 16/6/30.
 */
public class LinkmanActivityMain extends MainActivity implements LinkmanView, SideBar.OnTouchingLetterChangedListener {
    @BindView(R.id.lv_linkman)
    ListView lv_linkman;
    @BindView(R.id.sideBar)
    SideBar sideBar;
    private LinkmanPersenter persenter;

    private ArrayList<LinkManBean> mList;
    private LinkManAdapter adapter;


    public LinkmanActivityMain() {
        super(R.layout.act_linkman, true);
    }

    @Override
    public void initViews() {
        persenter = new LinkmanPersenterImpl(this);

        mList = new ArrayList<>();
        adapter = new LinkManAdapter(mList);
        lv_linkman.setAdapter(adapter);

        sideBar.setOnTouchingLetterChangedListener(this);
        CommTitle commTitle = new CommTitle(this);
        commTitle.setTitle("联系人");
    }

    @Override
    public void refreshViews() {
        persenter.showLinkmanList();
    }

    @Override
    public void onTouchingLetterChanged(String title) {
        lv_linkman.smoothScrollToPositionFromTop(persenter.checkMoveToIndex(title), 0);
    }

    @Override
    public ArrayList<LinkManBean> getLinkmanList() {
        return mList;
    }

    @Override
    public void setLinkmanList(ArrayList<LinkManBean> list) {
        mList.clear();
        mList.addAll(list);
        adapter.notifyDataSetChanged();
    }
}
