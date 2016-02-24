package com.fir.wp.mydemo.ui;

import android.view.View;

/**
 * 作者：fir on 16/2/17 15:48
 * 邮箱：446893043@qq.com
 * 公司：北京微宝
 */
public class MainActivity extends  ButtonActivity{
    private String [] buttons=new String []{
            "网络请求OkHttp",
            "Dialog",
            "图片加载",
            "菜单选项"
    };
    @Override
    public String[] getButtons() {
        return buttons;
    }

    @Override
    public void initView() {
        super.initView();
        title.setText("主页面");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case 0:
                intentActivity(NetWorkActivity.class,null);
                break;
            case 1:
                intentActivity(DialogActivity.class,null);
                break;
            case 2:
                intentActivity(PicActivity.class,null);
                break;
            case 3:
                intentActivity(MenuActivity.class,null);
                break;
            default:
                break;
        }

    }
}
