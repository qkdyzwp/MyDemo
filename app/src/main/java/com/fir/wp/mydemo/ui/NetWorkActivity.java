package com.fir.wp.mydemo.ui;

import android.content.Context;
import android.os.Environment;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import com.fir.wp.mybase.http.DownCallBack;
import com.fir.wp.mybase.http.HttpGetMessage;
import com.fir.wp.mybase.http.Params;
import com.fir.wp.mybase.http.PostCallBack;
import com.fir.wp.mydemo.aes.AES;
import com.fir.wp.mydemo.bean.MemberLogin;
import com.google.gson.Gson;

/**
 * 作者：fir on 16/2/17 16:11
 * 邮箱：446893043@qq.com
 * 公司：北京微宝
 */
public class NetWorkActivity extends ButtonActivity {
    private String[] buttons = new String[]{
            "post请求",
            "download",
    };
    @Override
    public String[] getButtons() {
        return buttons;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case 0:
                doPost();
                break;
            case 1:
                downLoad();
                break;
            default:
                break;
        }
    }

    /**
     * post操作
     */
    public void doPost() {
            MemberLogin member = new MemberLogin();
            member.setTelphone("12345678900");
            member.setPassword("123");
            member.setImei(getDeviceId(NetWorkActivity.this));
            String message = new Gson().toJson(member);
            HttpGetMessage httpGetMessage = new HttpGetMessage();
            String aesMsg = null;
            try {
                aesMsg = AES.Encrypt(message, "weibao201410wlwp");
                Log.e("aseMsg", aesMsg);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Params params = new Params();
            params.put("param", aesMsg);
            httpGetMessage.postMessage("http://www.weibaobeijing.com/Accounting/monitored/asyn/authenticate.jspx", params, new PostCallBack() {
                @Override
                public void onStart(String url, Params params) {
                    Log.e("wp", "网络请求开始");
                }

                @Override
                public void onComplete(String request) {
                    Log.e("wp", "网络请求完成" + request);
                }

                @Override
                public void onFailed(String error) {
                    Log.e("wp", "网络请求失败" + error);
                }
            });

    }

    /**
     * 下载
     */
    public void downLoad(){
        {
            HttpGetMessage httpGetMessage = new HttpGetMessage();
            httpGetMessage.downFile("http://www.weibaobeijing.com/Accounting/downloadApk2.jspx", null, new DownCallBack(Environment.getExternalStorageDirectory().getPath(), "abc.apk") {
                @Override
                public void onStart(String url, Params params) {
                    Log.e("wp", "网络请求开始");
                }

                @Override
                public void onComplete(String request) {
                    Log.e("wp", "网络请求完成" + request);
                }

                @Override
                public void onFailed(String request) {

                }
                @Override
                public void onProgress(long currentBytes, long contentLength, boolean done) {
                    Log.e("wp111111", "currentBytes=" + currentBytes);
                    Log.e("wp11111", "contentLength=" + contentLength);//包长
                    Log.e("wp11111", "done=" + done);
                }
            });
        }
    }
    public static String getDeviceId(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        String imei = telephonyManager.getDeviceId();
        return imei;
    }
}
