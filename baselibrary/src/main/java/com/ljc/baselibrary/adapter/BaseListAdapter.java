package com.ljc.baselibrary.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.ljc.baselibrary.ApplicationBase;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import java.util.ArrayList;

/**
 * Created by lijiacheng on 16/6/29.
 */
public abstract class BaseListAdapter<T> extends BaseAdapter {
    public Context mContext;
    public LayoutInflater mInflater;
    public ArrayList<T> mList;
    public DisplayImageOptions options;

    public static final int NOIMAGE = 1;
    public static final int NODEFAULT = 2;
    public static final int DEFAULT = 3;

    protected BaseListAdapter(ArrayList<T> list) {
        this(list, NOIMAGE);
    }

    protected BaseListAdapter(ArrayList<T> list, int imgType) {
        this(list, imgType, -1);
    }

    protected BaseListAdapter(ArrayList<T> list, int imgType, int defalutImg) {
        this(list, imgType, defalutImg, 0);
    }

    public BaseListAdapter(ArrayList<T> list, int imgType, int defalutImg, int radius) {
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
        init();
    }

    public void init() {
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public abstract View getView(int position, View convertView, ViewGroup parent);
}
