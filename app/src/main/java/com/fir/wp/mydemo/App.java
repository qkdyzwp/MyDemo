package com.fir.wp.mydemo;

import android.app.Application;

import com.fir.wp.mybase.BaseConfig;

import java.nio.charset.Charset;

/**
 * Created by wp on 2016/1/19.
 * about
 */
public class App extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        BaseConfig config=new BaseConfig();
        config.setCharset(Charset.forName("UTF-8"))
        .setIsLoadImg(false)
        .setPackageName(App.this.getPackageName())
        .builder(App.this);
    }
}
