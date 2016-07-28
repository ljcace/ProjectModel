package com.ljc.projectmodel.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ljc.baselibrary.adapter.BaseRecycleAdapter;
import com.ljc.baselibrary.adapter.BaseViewHolder;
import com.ljc.projectmodel.R;

import java.util.ArrayList;

/**
 * Created by lijiacheng on 16/7/14.
 */
public class PictureAdapter extends BaseRecycleAdapter<ArrayList<String>> {
    public PictureAdapter(ArrayList<ArrayList<String>> list) {
        super(list);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        BaseViewHolder holder = new BaseViewHolder(new ViewHolder(), mInflater.inflate(R.layout.lv_item_linkman, null)) {
            @Override
            protected void initViews(Object holder) {
                ((ViewHolder) holder).tv = (TextView) itemView.findViewById(R.id.tv_name);

            }
        };
        return holder;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
//        ((ViewHolder)holder.holder).tv
    }

    class ViewHolder {
        TextView tv;
        ImageView iv;
    }
}
