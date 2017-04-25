package com.yang.linus.view;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Linus on 2017/4/19.
 */

public class CrackingRecyclerView extends RecyclerView {

    public CrackingRecyclerView(Context context) {
        super(context);
    }

    public CrackingRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CrackingRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthSpec, int heightSpec) {
        super.onMeasure(widthSpec, heightSpec);
    }

    @Override
    protected void measureChild(View child, int parentWidthMeasureSpec, int parentHeightMeasureSpec) {
        super.measureChild(child, parentWidthMeasureSpec, parentHeightMeasureSpec);
    }

    @Override
    protected void measureChildren(int widthMeasureSpec, int heightMeasureSpec) {
        super.measureChildren(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void measureChildWithMargins(View child, int parentWidthMeasureSpec, int widthUsed, int parentHeightMeasureSpec, int heightUsed) {
        super.measureChildWithMargins(child, parentWidthMeasureSpec, widthUsed, parentHeightMeasureSpec, heightUsed);
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        return super.onTouchEvent(e);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

//
//    @Override
//    public int getPaddingLeft() {
//        return getWidth() / 2;
//    }
//
//    @Override
//    public int getPaddingRight() {
//        return getWidth() / 2;
//    }

    @Override
    public void onDraw(Canvas c) {
        super.onDraw(c);
//        requestLayout();
//        invalidate();
    }
}
