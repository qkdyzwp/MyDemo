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
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.fir.wp.mybase.dialog.ColorDialog;
import com.fir.wp.mybase.dialog.PromptDialog;
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

    private Button textDiaolog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Fresco.initialize(MainActivity.this);
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
                    Log.e("aseMsg",aesMsg);
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
            }

            );
            downLoad.setOnClickListener(new View.OnClickListener()

            {
                @Override
                public void onClick (View v){
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
        });
    }
    public void showPromptDialog(View view) {
        showPromptDlg();
    }

    private void showPromptDlg() {
        new PromptDialog(this)
                .setDialogType(PromptDialog.DIALOG_TYPE_SUCCESS)
                .setAnimationEnable(true)
                .setTitleText("Success").setContentText("Your info text goes here. Loremipsum dolor sit amet, consecteturn adipisicing elit, sed do eiusmod.")
                .setPositiveListener("OK", new PromptDialog.OnPositiveListener() {
                    @Override
                    public void onClick(PromptDialog dialog) {
                        dialog.dismiss();
                    }
                }).show();
    }

    public void showTextDialog(View view) {
        ColorDialog dialog = new ColorDialog(this);
        dialog.setColor("#8ECB54");
        dialog.setAnimationEnable(true);
        dialog.setTitle(getString(R.string.operation));
        dialog.setContentText(getString(R.string.content_text));
        dialog.setPositiveListener(getString(R.string.text_iknow), new ColorDialog.OnPositiveListener() {
            @Override
            public void onClick(ColorDialog dialog) {
                Toast.makeText(MainActivity.this, dialog.getPositiveText().toString(), Toast.LENGTH_SHORT).show();
            }
        }).show();
    }

    public void showPicDialog(View v) {
        ColorDialog dialog = new ColorDialog(this);
        dialog.setTitle(getString(R.string.operation));
        dialog.setAnimationEnable(true);
        dialog.setContentImage(getResources().getDrawable(R.mipmap.sample_img));
        Log.e("wp","在调用show之前");
        dialog.setPositiveListener(getString(R.string.delete), new ColorDialog.OnPositiveListener() {
            @Override
            public void onClick(ColorDialog dialog) {
                Toast.makeText(MainActivity.this, dialog.getPositiveText().toString(), Toast.LENGTH_SHORT).show();
            }
        })
                .setNegativeListener(getString(R.string.cancel), new ColorDialog.OnNegativeListener() {
                    @Override
                    public void onClick(ColorDialog dialog) {
                        Toast.makeText(MainActivity.this, dialog.getNegativeText().toString(), Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                }).show();
    }

    public void showAllModeDialog(View view) {
        ColorDialog dialog = new ColorDialog(this);
        dialog.setTitle(getString(R.string.operation));
        dialog.setAnimationEnable(true);
        dialog.setContentText(getString(R.string.content_text));
        dialog.setContentImage(getResources().getDrawable(R.mipmap.sample_img));
        dialog.setPositiveListener(getString(R.string.delete), new ColorDialog.OnPositiveListener() {
            @Override
            public void onClick(ColorDialog dialog) {
                Toast.makeText(MainActivity.this, dialog.getPositiveText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        dialog.setNegativeListener(getString(R.string.cancel), new ColorDialog.OnNegativeListener() {
            @Override
            public void onClick(ColorDialog dialog) {
                Toast.makeText(MainActivity.this, dialog.getNegativeText().toString(), Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
                });
        dialog.show();
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
