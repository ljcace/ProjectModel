package com.ljc.projectmodel.ui.menu2.v;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.ljc.projectmodel.R;
import com.ljc.projectmodel.ui.MainActivity;
import com.ljc.projectmodel.views.DampScrollView;
import com.ljc.projectmodel.widgets.CommTitle;

import butterknife.BindView;

/**
 * Created by lijiacheng on 2016/11/24.
 */

public class ImageZoomingActivity extends MainActivity implements ImageZoomingView {
    @BindView(R.id.root)
    DampScrollView root;
    @BindView(R.id.iv_img)
    ImageView iv_img;

    public ImageZoomingActivity() {
        super(R.layout.act_imagezooming);
    }

    @Override
    public void initViews() {
    }

    @Override
    public void refreshViews() {
        Glide.with(this).load("http://4493bz.1985t.com/uploads/allimg/150127/4-15012G52133.jpg").into(iv_img);
        root.setView(iv_img);
        CommTitle title = new CommTitle(this);
        title.setTitle("详情");
        root.setTitleLayout(title.getTitleLayout());
    }
}
