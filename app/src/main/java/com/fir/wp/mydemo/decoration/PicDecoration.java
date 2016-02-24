package com.fir.wp.mydemo.decoration;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * 作者：fir on 16/2/18 10:09
 * 邮箱：446893043@qq.com
 * 公司：北京微宝
 */
public class PicDecoration extends RecyclerView.ItemDecoration{

    private Drawable mDrawable;
    private int space;

    public PicDecoration(Context context, int resId,int space) {
        //在这里我们传入作为Divider的Drawable对象
        mDrawable = context.getResources().getDrawable(resId);
        this.space=space;
    }

    /**
     * 分割线
     * @param c
     * @param parent
     */
    @Override
    public void onDrawOver(Canvas c, RecyclerView parent) {
//        final int left = parent.getPaddingLeft();
//        final int right = parent.getWidth() - parent.getPaddingRight();
//        final int childCount = parent.getChildCount();
//        for (int i = 0; i < childCount; i++) {
//            final View child = parent.getChildAt(i);
//            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
//                    .getLayoutParams();
//            //以下计算主要用来确定绘制的位置
//            final int top = child.getBottom() + params.bottomMargin;
//            final int bottom = top + mDrawable.getIntrinsicHeight();
//            mDrawable.setBounds(left, top, right, bottom);
//            mDrawable.draw(c);
//        }
    }

    /**
     * item间隔距离
     * @param outRect
     * @param view
     * @param parent
     * @param state
     */
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        if(parent.getChildAdapterPosition(view) != 0)
            outRect.top = space;
    }
}
