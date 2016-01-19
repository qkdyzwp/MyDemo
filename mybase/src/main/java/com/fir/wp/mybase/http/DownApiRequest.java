package com.fir.wp.mybase.http;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;

import cn.edu.zafu.coreprogress.helper.ProgressHelper;

/**
 * Created by wp on 2016/1/18.
 * about
 */
public class DownApiRequest extends HttpRequest{
    @Override
    public void getMessage() {
        BaseCallBack callBack=getCallBack();
        Params params=getParams();
        //实现网络请求
        try {
            OkHttpClient mOkhttpClient=getmOkHttpClient();
            String url=getUrl();
            callBack.onStart(url,params);
            Request request = new Request.Builder()
                    .url(url)
                    .build();
            ProgressHelper.addProgressResponseListener(mOkhttpClient, callBack.listener).newCall(request).enqueue(callBack);
//            Call call = mOkhttpClient.newCall(request);
//            call.enqueue(callBack);
        }catch(IllegalArgumentException ex){
            callBack.onFailed("Url路径不合法");
        }catch(Exception ex){
            callBack.onFailed("网络异常，稍后访问");
        }finally {
            if (params != null) params.clear();
        }
    }
}
