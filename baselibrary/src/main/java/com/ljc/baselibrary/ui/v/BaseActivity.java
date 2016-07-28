package com.ljc.baselibrary.ui.v;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.ljc.baselibrary.ApplicationBase;
import com.ljc.baselibrary.R;
import com.ljc.baselibrary.manager.ActivityManager;
import com.ljc.baselibrary.ui.p.BasePersenter;
import com.ljc.baselibrary.ui.p.BasePersenterImlp;
import com.ljc.baselibrary.utils.LogUtils;

import java.io.Serializable;

/**
 * Created by lijiacheng on 16/6/28.
 */
public class BaseActivity extends Activity implements BaseView {
    private BasePersenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ApplicationBase.getInstance().getActivityManager().pushInActivity(this);
        presenter = new BasePersenterImlp(this);
    }

    @Override
    public void startActivity(Class<?> activity) {
        startActivity(activity, null);
    }

    @Override
    public void startActivity(Class<?> activity, Object data) {
        Intent intent = new Intent();
        intent.setClass(getApplicationContext(), activity);
        if (data != null)
            intent.putExtra("data", (Serializable) data);
        startActivity(intent);
        overridePendingTransition(R.anim.anim_enter, R.anim.anim_exit);
    }

    @Override
    public void startActivityForResult(Class<?> activity, int requstcode) {
        startActivityForResult(activity, null, requstcode);
    }

    @Override
    public void startActivityForResult(Class<?> activity, Object data, int requstcode) {
        Intent intent = new Intent();
        intent.setClass(getApplicationContext(), activity);
        if (data != null)
            intent.putExtra("data", (Serializable) data);
        startActivityForResult(intent, requstcode);
        overridePendingTransition(R.anim.anim_enter, R.anim.anim_exit);
    }

    @Override
    public void finish() {
        ApplicationBase.getInstance().getActivityManager().pushOutActivity(this);
        super.finish();
        overridePendingTransition(R.anim.anim_enter, R.anim.anim_exit);
    }

    @Override
    public void finishAll() {
        ApplicationBase.getInstance().getActivityManager().finishAll();
    }

    @Override
    public void finishExcept(Activity activity) {
        ApplicationBase.getInstance().getActivityManager().finishAllExcept(activity);
    }

    @Override
    protected void onStart() {
        LogUtils.d("onStart");
        super.onStart();
    }

    @Override
    protected void onResume() {
        LogUtils.d("onResume");
        super.onResume();
    }

    @Override
    protected void onPause() {
        LogUtils.d("onPause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        LogUtils.d("onStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        LogUtils.d("onDestroy");
        super.onDestroy();
    }
}
