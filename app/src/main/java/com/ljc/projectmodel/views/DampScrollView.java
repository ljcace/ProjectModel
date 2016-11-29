package com.ljc.projectmodel.views;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

import com.ljc.baselibrary.ApplicationBase;

/**
 * 图片放大，标题栏改变透明度
 * Created by lijiacheng on 2016/11/24.
 */

public class DampScrollView extends ScrollView {
    private View view;
    private View title;

    private float downY;
    private boolean isScrolling = false;
    private float alpha = 0;//title当前透明度

    private int width = 0;//图片初始宽度
    private int height = 0;//图片初始高度
    private int red = 0;
    private int green = 0;
    private int blue = 0;

    private static final int MIN_ALPHA = 0;//最小透明度
    private static final int MAX_ALPHA = 255;//最大透明度
    private static final float IMG_SCALE = 4l / 3l;//图片宽高比
    private static final float IMG_DAMP = 0.75f;//图片放大系数
    private static final float ALPHA_DAMP = 0.03f;//标题栏透明度变化系数

    public DampScrollView(Context context) {
        this(context, null);
    }

    public DampScrollView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DampScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        width = ApplicationBase.getInstance().getDeviceWidth();
        height = (int) (width / IMG_SCALE);
    }

    public void setView(View view) {
        this.view = view;
        ViewGroup.LayoutParams lp = this.view.getLayoutParams();
        lp.width = width;
        lp.height = height;
        this.view.setLayoutParams(lp);
    }

    public void setTitleLayout(View title) {
        this.title = title;
        int color = ((ColorDrawable) this.title.getBackground()).getColor();
        setColor(color);
        this.title.setBackgroundColor(Color.argb((int) alpha, red, green, blue));
    }

    private void setColor(int color) {
        red = (color & 0xff0000) >> 16;
        green = (color & 0x00ff00) >> 8;
        blue = (color & 0x0000ff);
    }

    @Override
    protected void onScrollChanged(int x, int y, int oldx, int oldy) {
        super.onScrollChanged(x, y, oldx, oldy);
        if (y > oldy ? y >= height : y < height)
            refreshTitleAlpha(y - oldy);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        ViewGroup.LayoutParams lp = view.getLayoutParams();
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downY = ev.getY();
                isScrolling = true;
                break;
            case MotionEvent.ACTION_MOVE:
                if (isScrolling) {
                    refreshImgScale(lp, ev.getY() - downY);
                }
                break;
            case MotionEvent.ACTION_UP:
                isScrolling = false;
                resetImgScale();
                break;
        }
        return super.onTouchEvent(ev);
    }

    /**
     * 刷新控件大小
     *
     * @param lp
     * @param extraY
     */
    private void refreshImgScale(ViewGroup.LayoutParams lp, float extraY) {
        if (view == null) {
            return;
        }
        float disPosition = extraY * IMG_DAMP;
        if (disPosition < 0) {
            return;
        }

        lp.width = (int) (width + disPosition * IMG_SCALE);
        lp.height = (int) (height + disPosition);
        view.setLayoutParams(lp);
    }

    /**
     * 刷新标题透明度
     *
     * @param exalpha
     */
    private void refreshTitleAlpha(float exalpha) {
        if (title == null) {
            return;
        }
        alpha += exalpha;
        if (alpha < MIN_ALPHA) {
            alpha = MIN_ALPHA;
        }
        if (alpha > MAX_ALPHA) {
            alpha = MAX_ALPHA;
        }
        this.title.setBackgroundColor(Color.argb((int) alpha, red, green, blue));
    }

    /**
     * 重置控件大小
     */
    private void resetImgScale() {
        if (view == null) {
            return;
        }
        final ViewGroup.LayoutParams lp = view.getLayoutParams();
        final float w = lp.width;// 图片当前宽度
        final float h = lp.height;// 图片当前高度
        // 设置动画
        ValueAnimator anim = ObjectAnimator.ofFloat(0.0F, 1.0F).setDuration(300);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float cVal = (Float) animation.getAnimatedValue();
                lp.width = (int) (w - (w - width) * cVal);
                lp.height = (int) (h - (h - height) * cVal);
                view.setLayoutParams(lp);
            }
        });
        anim.start();
    }
}
