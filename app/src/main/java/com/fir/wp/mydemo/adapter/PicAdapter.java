package com.fir.wp.mydemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fir.wp.mybase.load.LoadImg;
import com.fir.wp.mydemo.R;

import java.util.List;

/**
 * 作者：fir on 16/2/17 16:55
 * 邮箱：446893043@qq.com
 * 公司：北京微宝
 */
public class PicAdapter extends RecyclerView.Adapter{

    public List<String> urlList;
    public LayoutInflater infalter;
    public PicAdapter(List<String> urlList, Context context){
        this.urlList=urlList;
        this.infalter=LayoutInflater.from(context);
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=infalter.inflate(R.layout.pic_item,parent,false);
        return new PicViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((PicViewHolder)holder).loadImg.setUrl(urlList.get(position));
    }

    @Override
    public int getItemCount() {
        return urlList.size();
    }

    public class PicViewHolder extends RecyclerView.ViewHolder{

        LoadImg loadImg;
        public PicViewHolder(View itemView) {
            super(itemView);
            loadImg= (LoadImg) itemView.findViewById(R.id.loadImage);
        }
    }
}
