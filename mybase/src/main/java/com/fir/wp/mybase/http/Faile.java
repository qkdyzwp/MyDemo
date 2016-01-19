package com.fir.wp.mybase.http;

/**
 * Created by wp on 2016/1/15.
 * about
 */
public class Faile {

    public static final String TIMEOUT="网络请求超时";
    public static final String NETERROR="网络请求失败";
    public static final String DOWNERROR="文件下载失败";
    public String error;
    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
