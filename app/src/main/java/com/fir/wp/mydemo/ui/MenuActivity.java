package com.fir.wp.mydemo.ui;

import android.util.Log;

import com.fir.wp.mybase.menu.ExpandPopTabView;
import com.fir.wp.mybase.menu.KeyValueBean;
import com.fir.wp.mybase.menu.PopOneListView;
import com.fir.wp.mybase.menu.PopTwoListView;
import com.fir.wp.mydemo.R;
import com.fir.wp.mydemo.bean.ConfigAreaDTO;
import com.fir.wp.mydemo.bean.ConfigsDTO;
import com.fir.wp.mydemo.bean.ConfigsMessageDTO;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 作者：fir on 16/2/17 16:12
 * 邮箱：446893043@qq.com
 * 公司：北京微宝
 */
public class MenuActivity extends BaseActivity{
    private ExpandPopTabView expandTabView;
    private List<KeyValueBean> mParentLists = new ArrayList<>();
    private List<ArrayList<KeyValueBean>> mChildrenListLists = new ArrayList<>();
    private List<KeyValueBean> mPriceLists;
    private List<KeyValueBean> mSortLists;
    private List<KeyValueBean> mFavorLists;
    @Override
    public int getLayout() {
        return R.layout.menu_activity;
    }

    @Override
    public void initView() {

        setConfigsDatas();

        expandTabView = (ExpandPopTabView) findViewById(R.id.expandtab_view);
        addItem(expandTabView, mPriceLists, "", "价格");
        addItem(expandTabView, mFavorLists, "默认", "排序");
        addItem(expandTabView, mSortLists, "优惠最多", "优惠");
        addItem(expandTabView, mParentLists, mChildrenListLists, "锦江区", "合江亭", "区域");
//        TabLayout menuOneLayout= (TabLayout) findViewById(R.id.expanded_menu);
//        MenuOneLayout layout =new MenuOneLayout(MenuActivity.this);
//        menuOneLayout.addMenu("hello",layout);
//        MenuOneLayout layout2 =new MenuOneLayout(MenuActivity.this);
//        menuOneLayout.addMenu("word",layout2);
//        menuOneLayout.init(this);
//        LinearLayout menuLayout= (LinearLayout) findViewById(R.id.menuLayout);
//        LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
//        menuLayout.addView(new MenuOneLayout(MenuActivity.this),layoutParams);

    }
    public void addItem(ExpandPopTabView expandTabView, List<KeyValueBean> lists, String defaultSelect, String defaultShowText) {
        PopOneListView popOneListView = new PopOneListView(this);
        popOneListView.setDefaultSelectByValue(defaultSelect);
        //popViewOne.setDefaultSelectByKey(defaultSelect);
        popOneListView.setCallBackAndData(lists, expandTabView, new PopOneListView.OnSelectListener() {
            @Override
            public void getValue(String key, String value) {


                Log.e("tag", "key :" + key + " ,value :" + value);
            }
        });
        expandTabView.addItemToExpandTab(defaultShowText, popOneListView);
    }

    public void addItem(ExpandPopTabView expandTabView, List<KeyValueBean> parentLists,
                        List<ArrayList<KeyValueBean>> childrenListLists, String defaultParentSelect, String defaultChildSelect, String defaultShowText) {
        PopTwoListView popTwoListView = new PopTwoListView(this);
        popTwoListView.setDefaultSelectByValue(defaultParentSelect, defaultChildSelect);
        //distanceView.setDefaultSelectByKey(defaultParent, defaultChild);
        popTwoListView.setCallBackAndData(expandTabView, parentLists, childrenListLists, new PopTwoListView.OnSelectListener() {
            @Override
            public void getValue(String showText, String parentKey, String childrenKey) {
                Log.e("tag", "showText :" + showText + " ,parentKey :" + parentKey + " ,childrenKey :" + childrenKey);
            }
        });
        expandTabView.addItemToExpandTab(defaultShowText, popTwoListView);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(expandTabView != null){
            expandTabView.onExpandPopView();
        }
    }


    private void setConfigsDatas() {
        try {
            InputStream is = getAssets().open("searchType");
            String searchTypeJson = readStream(is);
            ConfigsMessageDTO messageDTO;

            Gson gson=new Gson();
            messageDTO=gson.fromJson(searchTypeJson,ConfigsMessageDTO.class);
            ConfigsDTO configsDTO = messageDTO.getInfo();

            mPriceLists = configsDTO.getPriceType();
            mSortLists = configsDTO.getSortType();
            mFavorLists = configsDTO.getSortType();

            List<ConfigAreaDTO> configAreaListDTO = configsDTO.getCantonAndCircle();
            for (ConfigAreaDTO configAreaDTO : configAreaListDTO) {
                KeyValueBean keyValueBean = new KeyValueBean();
                keyValueBean.setKey(configAreaDTO.getKey());
                keyValueBean.setValue(configAreaDTO.getValue());
                mParentLists.add(keyValueBean);

                ArrayList<KeyValueBean> childrenLists = new ArrayList<>();
                for (KeyValueBean keyValueBean1 : configAreaDTO.getBusinessCircle()) {
                    childrenLists.add(keyValueBean1);
                }
                mChildrenListLists.add(childrenLists);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String readStream(InputStream is) {
        try {
            ByteArrayOutputStream bo = new ByteArrayOutputStream();
            int i = is.read();
            while (i != -1) {
                bo.write(i);
                i = is.read();
            }
            return bo.toString();
        } catch (IOException e) {
            return "";
        }
    }
}
