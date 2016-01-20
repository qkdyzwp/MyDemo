package com.fir.wp.mydemo;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.facebook.drawee.view.SimpleDraweeView;
import com.fir.wp.mybase.http.DownCallBack;
import com.fir.wp.mybase.http.HttpGetMessage;
import com.fir.wp.mybase.http.Params;
import com.fir.wp.mybase.http.PostCallBack;
import com.fir.wp.mydemo.aes.AES;
import com.fir.wp.mydemo.bean.MemberLogin;
import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {
    private Button postGetMessage;
    private Button downLoad;
    private SimpleDraweeView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        postGetMessage = (Button) findViewById(R.id.postGetMessage);
        downLoad = (Button) findViewById(R.id.downLoad);
        img= (SimpleDraweeView) findViewById(R.id.image_view);
        Uri uri = Uri.parse("http://img4.duitang.com/uploads/item/201510/07/20151007124603_4Tj2K.jpeg");
        img.setImageURI(uri);
        postGetMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MemberLogin member = new MemberLogin();
                member.setTelphone("12345678900");
                member.setPassword("123");
                member.setImei(getDeviceId(MainActivity.this));
                String message = new Gson().toJson(member);
                HttpGetMessage httpGetMessage = new HttpGetMessage();
                String aesMsg = null;
                try {
                    aesMsg = AES.Encrypt(message, "weibao201410wlwp");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Params params = new Params();
                params.put("param", aesMsg);
//              params.put("file",new File(""));
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
        });
        downLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HttpGetMessage httpGetMessage = new HttpGetMessage();
                httpGetMessage.downFile("http://www.weibaobeijing.com/Accounting/downloadApk2.jspx", null, new DownCallBack( Environment.getExternalStorageDirectory().getPath(),"abc.apk") {
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
                        Log.e("wp11111","contentLength="+contentLength);//包长
                        Log.e("wp11111","done="+done);
                    }
                });
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static String getDeviceId(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        String imei = telephonyManager.getDeviceId();
        return imei;
    }

}
