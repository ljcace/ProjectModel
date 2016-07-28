package com.ljc.baselibrary.finals;

import android.os.Environment;

import com.ljc.baselibrary.ApplicationBase;
import com.ljc.baselibrary.R;

import java.io.File;

/**
 * Created by lijiacheng on 16/6/29.
 */
public class BaseConstant {
    public static final String CACHE_DIR = Environment
            .getExternalStorageDirectory().getPath() + File.separator + ApplicationBase.getInstance
            ().getString(R
            .string.app_name);
    public static final String CACHE_IMAGE_DIR = CACHE_DIR + File.separator + "image";
    public static final String CACHE_HTTP_DIR = CACHE_DIR + File.separator + "http";
}
