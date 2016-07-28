package com.ljc.projectmodel.ui.menu3;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ljc.baselibrary.widgets.DividerItemDecoration;
import com.ljc.projectmodel.R;
import com.ljc.projectmodel.ui.MainActivity;

import butterknife.BindView;

/**
 * Created by lijiacheng on 16/7/14.
 */
public class RecycleViewActivity extends MainActivity {
    @BindView(R.id.lv_show)
    RecyclerView lv_show;

    public RecycleViewActivity() {
        super(R.layout.act_recycleview, true);
    }

    @Override
    public void initViews() {
        lv_show.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        lv_show.setLayoutManager(new LinearLayoutManager(this));
        lv_show.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    public void refreshViews() {

    }
}
