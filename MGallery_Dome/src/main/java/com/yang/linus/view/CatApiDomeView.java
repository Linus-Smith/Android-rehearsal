package com.yang.linus.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by linusyang on 16-12-8.
 */

public class CatApiDomeView extends View {

    private Paint mPaint;

    public CatApiDomeView(Context context) {
        this(context, null);
    }

    public CatApiDomeView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CatApiDomeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

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
    }
}
