package com.fir.wp.mybase.http;

import com.squareup.okhttp.OkHttpClient;

import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

import cn.edu.zafu.coreprogress.listener.impl.UIProgressListener;

/**
 * Created by wp on 2016/1/14.
 * about
 */
public abstract class HttpRequest {
    public static final Charset chars= Charset.forName("UTF-8");
    private  static  OkHttpClient mOkHttpClient;
    private static final int OUTTIME=30;
    private Params params;
    private String url;
    private BaseCallBack  callBack;
    private UIProgressListener uiProgressResponseListener;//只有下载的时候用到
    public UIProgressListener getUiProgressResponseListener() {
        return uiProgressResponseListener;
    }

    public void setUiProgressResponseListener(UIProgressListener uiProgressResponseListener) {
        this.uiProgressResponseListener = uiProgressResponseListener;
    }


    //先初始化mOkHttpClient
    static{
        initClient();
    }
    public OkHttpClient getmOkHttpClient() {
        if(mOkHttpClient==null)
        initClient();
        return mOkHttpClient;
    }
    public static void initClient(){
        mOkHttpClient=new OkHttpClient();
        mOkHttpClient.setConnectTimeout(OUTTIME, TimeUnit.SECONDS);
        mOkHttpClient.setReadTimeout(OUTTIME, TimeUnit.SECONDS);
        mOkHttpClient.setWriteTimeout(OUTTIME, TimeUnit.SECONDS);
    }
    public abstract void getMessage();
    public Params getParams() {
        return params;
    }

    public void setParams(Params params) {
        this.params = params;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    public BaseCallBack getCallBack() {
        return callBack;
    }

    public void setCallBack(BaseCallBack callBack) {
        this.callBack = callBack;
    }


}
