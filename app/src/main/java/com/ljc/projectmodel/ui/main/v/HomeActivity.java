package com.ljc.projectmodel.ui.main.v;

import android.view.View;
import android.widget.TextView;

import com.ljc.baselibrary.adapter.recycleview.BaseRecycleAdapter;
import com.ljc.baselibrary.adapter.recycleview.BaseRecyclerVH;
import com.ljc.baselibrary.views.pulltorefreshrecycleview.PullToRefreshRecycleView;
import com.ljc.projectmodel.R;
import com.ljc.projectmodel.ui.MainActivity;
import com.ljc.projectmodel.ui.menu2.v.ImageZoomingActivity;
import com.ljc.projectmodel.ui.menu3.v.NewsActivity;
import com.ljc.projectmodel.ui.menu4.v.LinkmanActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by lijiacheng on 16/6/29.
 */
public class HomeActivity extends MainActivity implements HomeView, View.OnClickListener {
    @BindView(R.id.tv_linkman)
    TextView tv_linkman;
    @BindView(R.id.tv_news)
    TextView tv_news;
    @BindView(R.id.tv_img)
    TextView tv_img;
    @BindView(R.id.rlv_home)
    PullToRefreshRecycleView rlv_home;

    BaseRecycleAdapter adapter;
    ArrayList<String> mList;

    public HomeActivity() {
        super(R.layout.act_home);
    }

    @Override
    public void initViews() {
        mList = new ArrayList<>();
        adapter = new BaseRecycleAdapter(this, mList, R.layout.lv_item_news) {
            @Override
            public void convert(BaseRecyclerVH holder, Object item, int position) {
                holder.setText(R.id.tv_content, mList.get(position));
            }
        };
//        rlv_home.setSwipeRefreshable(true);
        rlv_home.setLinearLayout();
        rlv_home.setAdapter(adapter);
    }

    @Override
    public void refreshViews() {
        for (int i = 0; i < 20; i++) {
            mList.add("item" + i);
        }
        adapter.notifyDataSetChanged();
    }

    @OnClick({R.id.tv_linkman, R.id.tv_news, R.id.tv_img})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_linkman:
                startActivity(LinkmanActivity.class);
                break;
            case R.id.tv_news:
                startActivity(NewsActivity.class);
                break;
            case R.id.tv_img:
                startActivity(ImageZoomingActivity.class);
                break;
        }
    }
}
