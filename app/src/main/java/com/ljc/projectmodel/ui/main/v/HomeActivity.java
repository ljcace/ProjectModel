package com.ljc.projectmodel.ui.main.v;

import android.view.View;
import android.widget.TextView;

import com.ljc.projectmodel.R;
import com.ljc.projectmodel.ui.MainActivity;
import com.ljc.projectmodel.ui.menu3.v.NewsActivity;
import com.ljc.projectmodel.ui.menu4.v.LinkmanActivity;

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

    public HomeActivity() {
        super(R.layout.act_home);
    }

    @Override
    public void initViews() {
    }

    @Override
    public void refreshViews() {
    }

    @OnClick({R.id.tv_linkman, R.id.tv_news})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_linkman:
                startActivity(LinkmanActivity.class);
                break;
            case R.id.tv_news:
                startActivity(NewsActivity.class);
                break;
        }
    }
}
