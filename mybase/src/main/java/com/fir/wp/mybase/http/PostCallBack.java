package com.fir.wp.mybase.http;

import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;

import org.apache.http.conn.ConnectTimeoutException;

import java.io.IOException;
import java.net.ConnectException;

/**
 * Created by wp on 2016/1/15.
 * about
 */
public abstract class PostCallBack extends BaseCallBack{

    @Override
    public void onFailure(Request request, IOException e) {
        onFailed(Faile.NETERROR);//网络请求失败
    }
    @Override
    public void onResponse(Response response) throws IOException {
        if (response != null && response.isSuccessful()) {
            ResponseBody body = response.body();
            try {
                //在请求返回的结果去判断是否成功，然后调用对应的抽象类，如果是我的话我还会写接口去调用接口的。。。。
                String result = body != null ? body.string() : null;
                onComplete(result);
            } catch (ConnectTimeoutException e) {
                errorMessage(e);
            } catch (ConnectException e) {
                errorMessage(e);
            } catch (Exception e) {
                errorMessage(e);
            } finally {
                if (body != null) body.close();
            }
        } else {
            throw new IOException("Network error：Unexpected code " + response);
        }
    }

    @Override
    public void onUIProgress(long currentBytes, long contentLength, boolean done) {

    }
}
