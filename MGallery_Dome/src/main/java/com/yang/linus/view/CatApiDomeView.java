package com.yang.linus.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.widget.Scroller;

/**
 * Created by linusyang on 16-12-8.
 */

public class CatApiDomeView extends View {

    private Paint mPaint;
    private VelocityTracker mVelocityTracker;

    public CatApiDomeView(Context context) {
        this(context, null);
    }

    public CatApiDomeView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CatApiDomeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();
        mVelocityTracker = VelocityTracker.obtain();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        System.out.println(event.getX()+"==========="+event.getY());
        return true;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        System.out.println("onLayout====");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        ColorMatrix colorMatrix = new ColorMatrix(new float[] {
            1, 0, 0, 0, 0,
            0, 1, 0, 0, 0,
            0, 0, 1, 0, 0,
            0, 0, 0, 1, 0,
        }) ;

        mPaint.setColorFilter(new ColorMatrixColorFilter((colorMatrix)));
        System.out.println("来了哈哈哈哈 ---333");
       // requestLayout();
        invalidate();
    }

    @Override
    public boolean isLayoutRequested() {
        return super.isLayoutRequested();
    }
}
