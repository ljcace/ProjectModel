package com.ljc.projectmodel.ui.menu4.m;

import android.content.Context;

import com.ljc.baselibrary.ApplicationBase;
import com.ljc.projectmodel.beans.LinkManBean;

import java.util.ArrayList;

/**
 * Created by lijiacheng on 16/6/30.
 */
public class LinkmanModelImpl implements LinkmanModel {
    private Context mContext;

    public LinkmanModelImpl() {
        mContext = ApplicationBase.getInstance().getApplicationContext();
    }

    @Override
    public ArrayList<LinkManBean> getLinkman() {
        ArrayList<LinkManBean> list = new ArrayList<>();
        String[] letter = mContext.getResources().getStringArray(com.ljc.baselibrary.R.array.letter_list);
        for (int i = 0; i < 27; i++) {
            LinkManBean title = new LinkManBean();
            title.setTitle(letter[i]);
            list.add(title);
            for (int j = 0; j < (int) (Math.random() * 10) + 1; j++) {
                LinkManBean item = new LinkManBean();
                item.setName(letter[i] + "名字");
                item.setPhone("18888888888");
                list.add(item);
            }
        }
        return list;
    }

    @Override
    public int getMoveToIndex(ArrayList<LinkManBean> list, String title) {
        int index = 0;
        for (int i = 0; i < list.size(); i++) {
            if (title.equals(list.get(i).getTitle())) {
                index = i;
                break;
            }
        }
        return index;
    }
}
