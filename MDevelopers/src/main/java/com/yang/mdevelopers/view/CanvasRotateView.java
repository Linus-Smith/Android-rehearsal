package com.yang.mdevelopers.view;

import android.content.Context;
import android.content.pm.PackageItemInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.InputDevice;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by linusyang on 16-12-23.
 */

public class CanvasRotateView extends View {

    private Paint mPaint;
    private int rotate = 0;
    private Bitmap mBitmap;

    public CanvasRotateView(Context context) {
        this(context, null, 0);
    }

    public CanvasRotateView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CanvasRotateView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();
        mBitmap = BitmapFactory.decodeResource(getResources(), android.R.mipmap.sym_def_app_icon);
    }


    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int activy = event.getAction();
         switch (activy) {
             case MotionEvent.ACTION_DOWN:

                 break;
             case MotionEvent.ACTION_MOVE:
                 rotate+= 1;
                 invalidate();
                 break;
         }
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        canvas.save();
//        mPaint.setColor(Color.RED);
//        canvas.drawRect(0,0, 100, 100, mPaint);
//        canvas.restore();
        canvas.save();
        canvas.rotate(rotate, 400, 400);
        mPaint.setColor(Color.RED);
        canvas.drawRect(300,300, 500, 500, mPaint);
        canvas.drawBitmap(mBitmap, 200, 200, mPaint);
        mPaint.setColor(Color.YELLOW);
        System.out.println(rotate+"=====");


        canvas.drawRect(400 , 400, 900, 500, mPaint);
        canvas.restore();
    }

    @Override
    public boolean callOnClick() {
        System.out.println("caasd");
        return super.callOnClick();
    }
}
