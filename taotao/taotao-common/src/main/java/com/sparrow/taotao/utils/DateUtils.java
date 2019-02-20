package com.sparrow.taotao.utils;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.Date;

/**
 * Created by xuefeiyuluo on 2019/2/18.
 */
public class DateUtils {

    public static String formatDate(Date date, String pattern) {
        if (date == null) {
            date = new Date(System.currentTimeMillis());
        }
        if (pattern == null) {
            pattern = "yyyy-MM-dd HH:mm:ss";
        }
        return DateFormatUtils.format(date,pattern);
    }
}
