package com.ljc.baselibrary.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by lijiacheng on 16/6/27.
 * 时间处理工具类
 */
public class DateUtils {
    public static String getTimeString(long time, String type) {
        SimpleDateFormat sdf = new SimpleDateFormat(type,
                Locale.CHINA);
        return sdf.format(time);
    }

    public static long getTimeLong(String time, String type) {
        SimpleDateFormat sdf = new SimpleDateFormat(type,
                Locale.CHINA);
        Date date;
        try {
            date = sdf.parse(time);
            return date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
