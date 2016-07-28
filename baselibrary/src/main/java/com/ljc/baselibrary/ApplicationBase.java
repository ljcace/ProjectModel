package com.ljc.baselibrary;

import android.app.Application;
import android.util.DisplayMetrics;

import com.ljc.baselibrary.finals.BaseConstant;
import com.ljc.baselibrary.manager.ActivityManager;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import java.io.File;
import java.util.HashMap;

/**
 * Created by lijiacheng on 16/6/24.
 */
public class ApplicationBase extends Application {
    private static ApplicationBase applicationBase = null;
    private ActivityManager activityManager;
    private HashMap<String, Object> mCache;
    private float deviceDensity;
    private int deviceWidth;
    private int deviceHeight;


    @Override
    public void onCreate() {
        super.onCreate();
        applicationBase = this;
        activityManager = ActivityManager.getInstance();
        mCache = new HashMap<>();
        initDeviceMsg();
        initFilePath();
        initLib();
    }

    public static ApplicationBase getInstance() {
        return applicationBase;
    }

    public ActivityManager getActivityManager() {
        return activityManager;
    }

    public float getDeviceDensity() {
        return deviceDensity;
    }

    public int getDeviceWidth() {
        return deviceWidth;
    }

    public int getDeviceHeight() {
        return deviceHeight;
    }

    public void cache(String key, Object value) {
        mCache.put(key, value);
    }

    public Object getCache(String key) {
        return mCache.get(key);
    }

    public void removeCache(String key) {
        mCache.remove(key);
    }

    public void clearCache() {
        mCache.clear();
        Runtime.getRuntime().gc();
    }

    private void initDeviceMsg() {
        DisplayMetrics dm = getResources().getDisplayMetrics();
        deviceWidth = dm.widthPixels;
        deviceHeight = dm.heightPixels;
        deviceDensity = dm.density;
    }

    private void initFilePath() {
        File cacheDir = new File(BaseConstant.CACHE_DIR);
        if (!cacheDir.exists()) {
            cacheDir.mkdirs();
        }
        File imageCacheDir = new File(BaseConstant.CACHE_IMAGE_DIR);
        if (!imageCacheDir.exists()) {
            imageCacheDir.mkdirs();
        }
    }

    public void initLib() {
        initImageLoader();
    }

    /**
     * init ImageLoader
     */
    private void initImageLoader() {
        File cacheDir = new File(BaseConstant.CACHE_DIR);
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                this).threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .diskCache(new UnlimitedDiskCache(cacheDir))
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.LIFO)// Not
                .build();
        ImageLoader.getInstance().init(config);
    }
}
