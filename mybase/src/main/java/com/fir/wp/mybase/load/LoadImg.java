package com.fir.wp.mybase.load;

import android.content.Context;
import android.net.Uri;
import android.util.AttributeSet;

import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.view.SimpleDraweeView;
import com.fir.wp.mybase.http.utils.StringUtils;

import java.io.File;

/**
 * Created by wp on 2016/1/19.
 * about
 */
public class LoadImg extends SimpleDraweeView{
    public LoadImg(Context context, GenericDraweeHierarchy hierarchy) {
        super(context, hierarchy);
    }

    public LoadImg(Context context) {
        super(context);
    }

    public LoadImg(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LoadImg(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public LoadImg(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
    public void setUrl(String url) {
        if (StringUtils.isEmpty(url)) return;
        Uri uri = Uri.parse(url);
        setImageURI(uri);
    }

    /**
     * packName
     * @param resId
     */
    public void setResource(int resId) {
        Uri uri = Uri.parse("res://" + "" + "/" + resId);
        setImageURI(uri);
    }

    public void setFile(File file) {
        if (file == null || !file.exists()) return;
        Uri uri = Uri.parse("file://" + file.getAbsolutePath());
        setImageURI(uri);
    }
}
