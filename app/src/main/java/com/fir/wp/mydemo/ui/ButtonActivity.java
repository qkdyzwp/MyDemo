package com.fir.wp.mydemo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fir.wp.mydemo.R;

/**
 * 作者：fir on 16/2/17 15:15
 * 邮箱：446893043@qq.com
 * 公司：北京微宝
 */
public abstract class ButtonActivity extends BaseActivity implements View.OnClickListener {

    protected TextView title;

    protected LinearLayout buttonLayout;

    @Override
    public int getLayout() {
        return R.layout.layout;
    }
    @Override
    public void initView() {
        title= (TextView) findViewById(R.id.title);
        buttonLayout= (LinearLayout) findViewById(R.id.topLayout);
//        String title=getIntent().getStringExtra("title");
        String [] buttons=getButtons();
        int id = 0;
        for (String btnText : buttons)
        {
            Button button=new Button(this);
            button.setText(btnText);
            button.setId(id++);
            button.setOnClickListener(this);
            button.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT));
            button.setTextSize(18);
            buttonLayout.addView(button);
        }
    }
    public abstract String[] getButtons();
    protected void intentActivity(Class intentClass, Bundle bundle){
        Intent intent=new Intent();
        intent.setClass(this,intentClass);
        intent.putExtra("msg",bundle);
        startActivity(intent);
    }
}
