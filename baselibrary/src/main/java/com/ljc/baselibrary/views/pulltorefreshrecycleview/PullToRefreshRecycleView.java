package com.ljc.baselibrary.views.pulltorefreshrecycleview;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.net.Uri;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.ljc.baselibrary.R;
import com.ljc.baselibrary.utils.LogUtils;

import java.io.File;

/**
 * Created by lijiacheng on 2016/11/30.
 */

public class PullToRefreshRecycleView extends LinearLayout {
    private Context mContext;
    private RelativeLayout rl_head;
    private LinearLayout ll_texthead;
    private ImageView iv_headicon;
    private TextView tv_headhint;

    private RelativeLayout rl_animationhead;
    private ImageView iv_headbackground, iv_headanimation;

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private LinearLayout ll_foot;

    private float downY;
    private float extraLengh;
    private boolean isSwipeRefreshable = false;
    private boolean isTop = true;
    private boolean isScrolling = false;
    private boolean isPull = false;
    private boolean isRefreshing = false;
    private boolean isLoading = false;

    private int mode;
    private int headIcon;
    private int refreshAnimation;
    private int headAnimationBackground;

    private int MAX_LENGH;
    private int BOTH = 0;
    private int REFRESH = 1;
    private int LOADMORE = 2;


    public PullToRefreshRecycleView(Context context) {
        super(context);
    }

    public PullToRefreshRecycleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.PullToRefreshRecycleView);

        mode = ta.getInt(R.styleable.PullToRefreshRecycleView_Mode, 0);
        headIcon = ta.getResourceId(R.styleable.PullToRefreshRecycleView_headIcon, -1);
        refreshAnimation = ta.getResourceId(R.styleable.PullToRefreshRecycleView_refreshAnimation, -1);
        headAnimationBackground = ta.getResourceId(R.styleable.PullToRefreshRecycleView_headAnimationBackground, -1);

        ta.recycle();

        init(context);
    }

    private void init(Context context) {
        mContext = context;
        MAX_LENGH = mContext.getResources().getDimensionPixelOffset(R.dimen.dim300);
        this.setOrientation(LinearLayout.VERTICAL);
        View view = LayoutInflater.from(context).inflate(R.layout.pulltorefreshrecycleview, null);
        rl_head = (RelativeLayout) view.findViewById(R.id.rl_head);

        ll_texthead = (LinearLayout) view.findViewById(R.id.ll_texthead);
        iv_headicon = (ImageView) view.findViewById(R.id.iv_headicon);
        if (headIcon != -1)
            setHeadIcon(headIcon);
        tv_headhint = (TextView) view.findViewById(R.id.tv_headhint);

        rl_animationhead = (RelativeLayout) view.findViewById(R.id.rl_animationhead);
        iv_headbackground = (ImageView) view.findViewById(R.id.iv_headbackground);
        iv_headanimation = (ImageView) view.findViewById(R.id.iv_headanimation);

        ll_foot = (LinearLayout) view.findViewById(R.id.ll_foot);

        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.mSwipeRefreshLayout);
        mSwipeRefreshLayout.setEnabled(isSwipeRefreshable);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                LogUtils.d("mSwipeRefreshLayout");
                isRefreshing = true;
                PullToRefreshRecycleView.this.onRefresh();
            }
        });
        mRecyclerView = (RecyclerView) view.findViewById(R.id.mRecyclerView);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                LogUtils.d("newState=" + newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (!recyclerView.canScrollVertically(-1)) {
                    LogUtils.d("到顶部");
                    isTop = true;
                } else if (!recyclerView.canScrollVertically(1)) {
                    LogUtils.d("到底部");
                    isTop = false;
                    if (mode == BOTH || mode == LOADMORE) {
                        if (isRefreshing || isLoading) {
                            isScrolling = false;
                            return;
                        }
                        loading();
                    }
                } else {
                    LogUtils.d("滑动中");
                    isTop = false;
                }
            }
        });
        mRecyclerView.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (mode == BOTH || mode == REFRESH) {
                    if (isRefreshing || isLoading) {
                        return true;
                    }
                    if (isSwipeRefreshable) {
                        return false;
                    }
                    ViewGroup.LayoutParams lp = rl_head.getLayoutParams();
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_MOVE:
                            isScrolling = true;
                            float y = event.getY();
                            if (isTop) {
                                pulling(lp, y);
                            }
                            break;
                        case MotionEvent.ACTION_UP:
                            refreshing();
                            break;
                    }
                } else {
                    return false;
                }
                return !isScrolling;
            }
        });
        this.addView(view);
    }

    /**
     * LinearLayoutManager
     */
    public void setLinearLayout() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
    }

    /**
     * GridLayoutManager
     */
    public void setGridLayout(int spanCount) {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, spanCount);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(gridLayoutManager);
    }

    /**
     * StaggeredGridLayoutManager
     */
    public void setStaggeredGridLayout(int spanCount) {
        StaggeredGridLayoutManager staggeredGridLayoutManager =
                new StaggeredGridLayoutManager(spanCount, LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(staggeredGridLayoutManager);
    }

    public void setSwipeRefreshable(boolean enabled) {
        isSwipeRefreshable = enabled;
        mSwipeRefreshLayout.setEnabled(isSwipeRefreshable);
    }

    public RecyclerView.LayoutManager getLayoutManager() {
        return mRecyclerView.getLayoutManager();
    }

    public RecyclerView getRecyclerView() {
        return mRecyclerView;
    }

    public void setHeadIcon(int imgRes) {
        headIcon = imgRes;
        iv_headicon.setImageResource(headIcon);
    }

    public void setHeadIcon(String path) {
        setHeadIcon(path, -1);
    }

    public void setHeadIcon(String path, int default_icon) {
        if (path == null) {
            path = "";
        }
        Glide.with(mContext)
                .load(path.contains("http") ? path : Uri.fromFile(new File(path)))
                .error(default_icon)
                .dontAnimate()
                .placeholder(default_icon)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(iv_headicon);
    }

    public void setAdapter(RecyclerView.Adapter adapter) {
        if (adapter != null) {
            mRecyclerView.setAdapter(adapter);
        }
    }

    private void pulling(ViewGroup.LayoutParams lp, float y) {
        if (!isPull) {
            isPull = true;
            downY = y;
            extraLengh = 0;
        }
        int extraY = (int) (y - downY);
        extraLengh += extraY;
        if (extraLengh < 0) {
            return;
        }
        isScrolling = false;
        if (extraLengh > MAX_LENGH) {
            extraLengh = MAX_LENGH;
        }
        lp.height = (int) extraLengh;
        rl_head.setLayoutParams(lp);
        if (!ll_texthead.isShown()) {
            ll_texthead.setVisibility(VISIBLE);
        }
    }

    private void refreshing() {
        final ViewGroup.LayoutParams lp = rl_head.getLayoutParams();
        final int height = lp.height;
        final int keepHeight = ll_texthead.getHeight();
        if (height <= keepHeight) {
            refreshFinish();
            return;
        }
        isRefreshing = true;
        ValueAnimator anim = ObjectAnimator.ofFloat(1.0F, 0.0F).setDuration(300);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float cVal = (Float) animation.getAnimatedValue();
                lp.height = (int) (keepHeight + (height - keepHeight) * cVal);
                rl_head.setLayoutParams(lp);
            }
        });
        anim.setInterpolator(new AccelerateDecelerateInterpolator());
        anim.start();
        tv_headhint.setText("加载中...");
        refreshingAnimation();
        onRefresh();
    }

    private void loading() {
        isLoading = true;
        loadMoreAnimation();
        onLoadMore();
    }

    private void refreshingAnimation() {

    }

    /**
     * 刷新结束，重置状态
     */
    private void refreshFinish() {
        isPull = false;
        isScrolling = false;
        final ViewGroup.LayoutParams lp;
        final float h;
        lp = rl_head.getLayoutParams();
        h = lp.height;// 图片当前高度
        // 设置动画
        ValueAnimator anim = ObjectAnimator.ofFloat(1.0F, 0.0F).setDuration(300);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float cVal = (Float) animation.getAnimatedValue();
                lp.height = (int) (h * cVal);
                rl_head.setLayoutParams(lp);
            }
        });
        anim.setInterpolator(new AccelerateDecelerateInterpolator());
        anim.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                tv_headhint.setText("释放加载");
                ll_texthead.setVisibility(GONE);
                extraLengh = 0;
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        anim.start();
    }

    private void loadMoreAnimation() {
        float height = ll_foot.getHeight();
        ObjectAnimator animation = new ObjectAnimator().ofFloat(ll_foot, "translationY", height, 0);
        animation.setDuration(300);
        animation.setInterpolator(new AccelerateDecelerateInterpolator());
        animation.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                ll_foot.setVisibility(VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animation.start();
    }

    private void loadMoreFinish() {
        float height = ll_foot.getHeight();
        ObjectAnimator animation = new ObjectAnimator().ofFloat(ll_foot, "translationY", 0, height);
        animation.setDuration(300);
        animation.setInterpolator(new AccelerateDecelerateInterpolator());
        animation.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                ll_foot.setVisibility(GONE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animation.start();
    }

    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                onPullLoadMoreCompleted();
            }
        }, 2000);
    }

    public void onLoadMore() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                onPullLoadMoreCompleted();
            }
        }, 2000);
    }

    public void onPullLoadMoreCompleted() {
        if (isRefreshing) {
            if (isSwipeRefreshable) {
                mSwipeRefreshLayout.setRefreshing(false);
            } else {
                refreshFinish();
            }
        } else if (isLoading) {
            loadMoreFinish();
        }
        isRefreshing = false;
        isLoading = false;
    }
}
