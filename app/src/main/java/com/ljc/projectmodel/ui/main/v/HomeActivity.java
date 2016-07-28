package com.ljc.projectmodel.ui.main.v;

import android.view.View;
import android.widget.TextView;

import com.ljc.projectmodel.R;
import com.ljc.projectmodel.ui.MainActivity;
import com.ljc.projectmodel.ui.menu4.v.LinkmanActivityMain;


/**
 * Created by lijiacheng on 16/6/29.
 */
public class HomeActivity extends MainActivity implements HomeView, View.OnClickListener {
    TextView tv_linkman;

    public HomeActivity() {
        super(R.layout.act_home);
    }

    @Override
    public void initViews() {
        tv_linkman = (TextView) findViewById(R.id.tv_linkman);
        tv_linkman.setOnClickListener(this);
    }

    @Override
    public void refreshViews() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_linkman:
                startActivity(LinkmanActivityMain.class);
                break;
        }
    }
}
