package com.ljc.projectmodel.widgets;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ljc.baselibrary.ui.v.BaseActivity;
import com.ljc.projectmodel.R;

/**
 * Created by lijiacheng on 16/6/30.
 */
public class CommTitle {
    ImageView iv_left;
    ImageView iv_right;
    TextView tv_title;

    public CommTitle(final BaseActivity activity) {
        iv_left = (ImageView) activity.findViewById(R.id.iv_left);
        iv_right = (ImageView) activity.findViewById(R.id.iv_right);
        tv_title = (TextView) activity.findViewById(R.id.tv_title);
        iv_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.finish();
            }
        });
    }

    public void setTitle(String title) {
        tv_title.setText(title);
    }

    public void setLeftOnClickListener(View.OnClickListener listener) {
        iv_left.setOnClickListener(listener);
    }

    public void setRightOnClickListener(View.OnClickListener listener) {
        iv_right.setOnClickListener(listener);
    }
}
