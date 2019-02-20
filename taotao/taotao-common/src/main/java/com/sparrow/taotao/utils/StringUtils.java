package com.sparrow.taotao.utils;

/**
 * Created by xuefeiyuluo on 2019/2/18.
 */
public class StringUtils {


    // 字符串的非空判断包括空字符串" "
    public static Boolean isEmpty(String s){
        if (s == null || "".equals(s.trim())) {
            return false;
        }
        return true;
    }
}
