package com.fir.wp.mydemo;

import android.app.Application;

import com.fir.wp.mybase.BaseConfig;

import java.nio.charset.Charset;
/**
 * Created by wp on 2016/1/19.
 * about就喜欢看你那种看我不顺眼还干不掉我的样子
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        new BaseConfig()
                .setCharset(Charset.forName("UTF-8"))
                .setIsLoadImg(true)
                .setPackageName(App.this.getPackageName())
                .builder(App.this);
    }
}
