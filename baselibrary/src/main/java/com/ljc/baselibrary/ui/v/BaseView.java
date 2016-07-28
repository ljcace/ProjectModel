package com.ljc.baselibrary.ui.v;

import android.app.Activity;

/**
 * Created by lijiacheng on 16/6/28.
 */
public interface BaseView {
    void startActivity(Class<?> activity);

    void startActivity(Class<?> activity, Object data);

    void startActivityForResult(Class<?> activity, int requstcode);

    void startActivityForResult(Class<?> activity, Object data, int requstcode);

    void finish();

    void finishAll();

    void finishExcept(Activity activity);
}
