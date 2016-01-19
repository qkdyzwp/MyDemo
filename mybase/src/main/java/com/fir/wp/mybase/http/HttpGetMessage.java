package com.fir.wp.mybase.http;

/**
 * Created by wp on 2016/1/15.
 * about
 */
public class HttpGetMessage {
    public void postMessage(String url,Params params,BaseCallBack callBack){
        HttpRequest request=new PostApiRequest();
        request.setUrl(url);
        request.setParams(params);
        request.setCallBack(callBack);
        request.getMessage();
    };

    public void downFile(String url,Params params,BaseCallBack callBack){
//        private  String fileSavePath;
//        private  String fileSaveName;
        HttpRequest request=new DownApiRequest();
        request.setUrl(url);
        request.setParams(params);
        request.setCallBack(callBack);
        request.getMessage();
    }
}
