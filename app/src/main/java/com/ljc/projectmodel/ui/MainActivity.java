package com.ljc.projectmodel.ui;

import android.os.Bundle;
import android.util.DisplayMetrics;

import com.ljc.baselibrary.ui.v.BaseActivity;
import com.ljc.baselibrary.utils.LogUtils;
import com.ljc.baselibrary.widgets.SlideLayout;

import butterknife.ButterKnife;

public abstract class MainActivity extends BaseActivity {
    private int layoutId;
    private boolean isSlide;

    public MainActivity(int layoutId) {
        this(layoutId, false);
    }

    public MainActivity(int layoutId, boolean isSlide) {
        this.layoutId = layoutId;
        this.isSlide = isSlide;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        beforeSetContentView();
        setContentView(layoutId);
        if (isSlide) {
            new SlideLayout(this).bindActivity();
        }
        getData();
        ButterKnife.bind(this);
        LogUtils.d("初始化view");
        initViews();
        LogUtils.d("刷新view");
        refreshViews();
    }

    public void beforeSetContentView() {
        LogUtils.d("设置view前操作");
    }

    public void getData() {
        LogUtils.d("获取数据");
        DisplayMetrics metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);
        int width = metric.widthPixels; // 屏幕宽度（像素）
        int height = metric.heightPixels; // 屏幕高度（像素）
        float density = metric.density; // 屏幕密度（0.75 / 1.0 / 1.5）
        int densityDpi = metric.densityDpi; // 屏幕密度DPI（120 / 160 / 240）
        LogUtils.d("width=" + width);
        LogUtils.d("height=" + height);
        LogUtils.d("density=" + density);
        LogUtils.d("densityDpi=" + densityDpi);
        LogUtils.d("dim1080=" + getResources().getDimension(com.ljc.baselibrary.R.dimen.dim1080));
    }

    public abstract void initViews();

    public abstract void refreshViews();
}
