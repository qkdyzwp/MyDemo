package com.fir.wp.mybase.http;

import android.util.Log;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;
import java.io.IOException;

/**
 * Created by wp on 2016/1/14.
 * about
 */
public class MyHttp {
    public String post(String msg){
//实例化httpClient
        OkHttpClient myOkHttpClient=new OkHttpClient();
//构建一个上传的内容
        FormEncodingBuilder builder = new FormEncodingBuilder();
        builder.add("param",msg);
//创建一个Request（请求对象）
                 Request request = new Request.Builder()
                .url("http://www.weibaobeijing.com/Accounting/monitored/asyn/authenticate.jspx")
                .post(builder.build())
                .build();
        myOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Log.e("wp","失败");
            }
            @Override
            public void onResponse(Response response) throws IOException {
                ResponseBody body = response.body();
                String result = body != null ? body.string() : null;
                Log.e("wp",result);
            }
        });
        return "";
    }
}
