package com.ljc.baselibrary.manager;

import android.app.Activity;

import com.ljc.baselibrary.utils.LogUtils;

import java.util.Stack;

/**
 * Created by lijiacheng on 16/6/28.
 */
public class ActivityManager {
    private static ActivityManager activityManager;
    private Stack<Activity> activityTask;

    private void ActivityManager() {
    }

    public static synchronized ActivityManager getInstance() {
        if (activityManager == null) {
            activityManager = new ActivityManager();
        }
        return activityManager;
    }

    public void pushInActivity(Activity activity) {
        LogUtils.d(activity.getClass().getSimpleName());
        if (activityTask == null) {
            activityTask = new Stack<>();
        }
        activityTask.add(activity);
    }

    public void pushOutActivity(Activity activity) {
        if (activityTask != null && activityTask.size() > 0) {
            if (activity != null) {
                activityTask.remove(activity);
                activity = null;
            }
        }
    }

    private Activity getLastActivity() {
        return activityTask.lastElement();
    }

    public void finishAllExcept(Activity ExceptActivity) {
        if (activityTask != null) {
            while (activityTask.size() > 1) {
                Activity activity = getLastActivity();
                if (activity == null)
                    break;
                if (activity != ExceptActivity)
                    pushOutActivity(activity);
            }
        }
    }

    public void finishAll() {
        if (activityTask != null) {
            while (activityTask.size() > 0) {
                Activity activity = getLastActivity();
                if (activity == null)
                    break;
                pushOutActivity(activity);
            }
        }
    }
}
