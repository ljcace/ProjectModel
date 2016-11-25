package com.ljc.baselibrary.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.ljc.baselibrary.ApplicationBase;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by lijiacheng on 16/6/29.
 */
public abstract class BaseListAdapter<T> extends BaseAdapter {
    public Context mContext;
    public LayoutInflater mInflater;
    public ArrayList<T> mList;

    public BaseListAdapter(ArrayList<T> list) {
        mList = list;
        mContext = ApplicationBase.getInstance().getApplicationContext();
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        init();
    }

    public void init() {
    }

    private void setImg(ImageView imageView, String path) {
        if (path.contains("http")) {
            Glide.with(mContext).load(path).diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView);
        } else {
            Glide.with(mContext).load(new File(path)).diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView);
        }
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
