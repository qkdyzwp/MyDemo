package com.fir.wp.mybase.http;
import com.squareup.okhttp.Callback;
import java.io.IOException;
import java.net.ConnectException;
import java.util.concurrent.TimeoutException;
import cn.edu.zafu.coreprogress.listener.impl.UIProgressListener;
/**
 * Created by wp on 2016/1/15.
 * about
 * UIProgressListener里面的onUIProgress是下载查看进度的时候用到的
 */
public abstract class BaseCallBack extends UIProgressListener implements Callback {
    public static UIProgressListener listener;
    public abstract void onStart(String url,Params params);
    public abstract  void onComplete(String request);
    public abstract  void onFailed(String request);
    public  void onProgress(long currentBytes, long contentLength, boolean done){};
    public void errorMessage(Throwable ex){
        String request="网络错误";
        if(ex instanceof IOException){
            request= Faile.NETERROR;
        }
        if(ex instanceof TimeoutException){
            request= Faile.TIMEOUT;
        }
        if(ex instanceof ConnectException){
            request= Faile.NETERROR;
        }
        onFailed(request);
    }
}
