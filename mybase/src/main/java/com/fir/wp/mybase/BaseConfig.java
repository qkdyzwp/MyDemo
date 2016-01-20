package com.fir.wp.mybase;

import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;

import java.nio.charset.Charset;

/**
 * Created by wp on 2016/1/19.
 * about
 */
public class BaseConfig {
    public static  String packageName;
    private static  Charset charset;
    private static boolean isLoadImg;
    public  BaseConfig builder(Context context){
        if(isLoadImg){
            Fresco.initialize(context);
        }
        //初始化一些东西
        return this;
    }
    public static String getPackageName() {
        return packageName;
    }

    public BaseConfig setPackageName(String packageName) {
        BaseConfig.packageName = packageName;
        return this;
    }

    public static Charset getCharset() {
        return charset;
    }

    public  BaseConfig setCharset(Charset charset) {
        BaseConfig.charset = charset;
        return this;
    }

    public static boolean isLoadImg() {
        return isLoadImg;
    }

    public  BaseConfig setIsLoadImg(boolean isLoadImg) {
        BaseConfig.isLoadImg = isLoadImg;
        return this;
    }
}
