package com.fir.wp.mybase.http.utils;

/**
 * Created by wp on 2016/1/14.
 * about
 */
public class StringUtils {
    public static boolean isEmpty(CharSequence charSequence){
        return charSequence==null||charSequence.equals("");
    }
}
