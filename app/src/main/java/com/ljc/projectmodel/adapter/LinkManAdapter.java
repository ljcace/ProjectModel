package com.ljc.projectmodel.adapter;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ljc.baselibrary.adapter.BaseListAdapter;
import com.ljc.projectmodel.R;
import com.ljc.projectmodel.beans.LinkManBean;

import java.util.ArrayList;

/**
 * Created by lijiacheng on 16/6/29.
 */
public class LinkManAdapter extends BaseListAdapter<LinkManBean> {
    public LinkManAdapter(ArrayList<LinkManBean> list) {
        super(list, NODEFAULT);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.lv_item_linkman, null);
            viewHolder.ll_title = (LinearLayout) convertView.findViewById(R.id.ll_title);
            viewHolder.ll_info = (LinearLayout) convertView.findViewById(R.id.ll_info);
            viewHolder.tv_title = (TextView) convertView.findViewById(R.id.tv_title);
            viewHolder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            viewHolder.tv_phone = (TextView) convertView.findViewById(R.id.tv_phone);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        LinkManBean item = mList.get(position);
        if (!TextUtils.isEmpty(item.getTitle())) {
            viewHolder.ll_title.setVisibility(View.VISIBLE);
            viewHolder.ll_info.setVisibility(View.GONE);
        } else {
            viewHolder.ll_title.setVisibility(View.GONE);
            viewHolder.ll_info.setVisibility(View.VISIBLE);
        }
        viewHolder.tv_title.setText(item.getTitle());
        viewHolder.tv_name.setText(item.getName());
        viewHolder.tv_phone.setText(item.getPhone());
        return convertView;
    }

    class ViewHolder {
        LinearLayout ll_title, ll_info;
        TextView tv_title;
        TextView tv_name;
        TextView tv_phone;
    }
}
