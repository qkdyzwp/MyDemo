package com.fir.wp.mydemo.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

/**
 * 作者：fir on 16/2/17 15:00
 * 邮箱：446893043@qq.com
 * 公司：北京微宝
 */
public abstract class BaseActivity extends AppCompatActivity {

    public abstract int getLayout();

    public abstract void initView();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        initView();
    }
}
