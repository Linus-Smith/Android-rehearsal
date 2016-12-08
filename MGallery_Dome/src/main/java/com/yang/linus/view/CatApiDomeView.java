package com.yang.linus.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by linusyang on 16-12-8.
 */

public class CatApiDomeView extends View {

    public CatApiDomeView(Context context) {
        super(context);
    }

    public CatApiDomeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CatApiDomeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        System.out.println("onMeasure");
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        System.out.println("onLayout====");
    }
}
