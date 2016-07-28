package com.ljc.baselibrary.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by lijiacheng on 16/7/20.
 */
public abstract class BaseViewHolder<H> extends RecyclerView.ViewHolder {
    public H holder;

    public BaseViewHolder(H holder, View itemView) {
        super(itemView);
        this.holder = holder;
        initViews(holder);
    }

    protected abstract void initViews(H holder);
}
