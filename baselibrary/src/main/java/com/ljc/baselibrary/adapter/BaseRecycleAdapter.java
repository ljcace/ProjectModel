package com.ljc.baselibrary.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ljc.baselibrary.ApplicationBase;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import java.util.ArrayList;

/**
 * Created by lijiacheng on 16/7/14.
 */
public abstract class BaseRecycleAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> {
    public Context mContext;
    public LayoutInflater mInflater;
    public ArrayList<T> mList;
    public DisplayImageOptions options;

    public static final int NOIMAGE = 1;
    public static final int NODEFAULT = 2;
    public static final int DEFAULT = 3;

    protected BaseRecycleAdapter(ArrayList<T> list) {
        this(list, NOIMAGE);
    }

    protected BaseRecycleAdapter(ArrayList<T> list, int imgType) {
        this(list, imgType, -1);
    }

    protected BaseRecycleAdapter(ArrayList<T> list, int imgType, int defalutImg) {
        this(list, imgType, defalutImg, 0);
    }

    protected BaseRecycleAdapter(ArrayList<T> list, int imgType, int defalutImg, int radius) {
        mList = list;
        mContext = ApplicationBase.getInstance().getApplicationContext();
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (imgType == NOIMAGE) {
            options = null;
        } else {
            DisplayImageOptions.Builder builder = new DisplayImageOptions.Builder();
            builder.delayBeforeLoading(1000)  // 下载前的延迟时间
                    .cacheInMemory(true) // default  设置下载的图片是否缓存在内存中
                    .bitmapConfig(Bitmap.Config.ARGB_8888) // default 设置图片的解码类型
                    .displayer(new RoundedBitmapDisplayer(radius));
            if (imgType == DEFAULT) {
                if (defalutImg != -1)
                    try {
                        builder.showImageOnLoading(defalutImg) // 设置图片下载期间显示的图片
                                .showImageForEmptyUri(defalutImg) // 设置图片Uri为空或是错误的时候显示的图片
                                .showImageOnFail(defalutImg);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
            }
            options = builder.build();
        }
    }

    @Override
    public abstract BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType);

    @Override
    public abstract void onBindViewHolder(BaseViewHolder holder, int position);

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }
}
