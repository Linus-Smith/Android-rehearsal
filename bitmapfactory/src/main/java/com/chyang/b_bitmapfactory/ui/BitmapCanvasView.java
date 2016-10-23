package com.chyang.b_bitmapfactory.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by caihuiyang on 2016/10/23.
 */

public class BitmapCanvasView extends View {

    private Bitmap mBitmap;
    private Paint mPaint;

    private float drawX = 0.0f;
    private float drawy = 0.0f;



    public BitmapCanvasView(Context context) {
        this(context, null);
    }

    public BitmapCanvasView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BitmapCanvasView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();
    }

    public void setBitmap(Bitmap bitmap) {
        mBitmap = bitmap;
    }

    public void setDrawX(float x) {
        drawX = x;
        invalidate();
    }

    public void setDrawY(float y) {
        drawy = y;
        invalidate();
    }


    public  Bitmap resizeImage(Bitmap bitmap, int w, int h) {

        Bitmap BitmapOrg = bitmap;
        int width = BitmapOrg.getWidth();
        int height = BitmapOrg.getHeight();
        int newWidth = w;
        int newHeight = h;

        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;

        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        // if you want to rotate the Bitmap
        // matrix.postRotate(45);
        Bitmap resizedBitmap = Bitmap.createBitmap(BitmapOrg, 0, 0, width,
                height, matrix, true);
        return resizedBitmap;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        if(mBitmap != null) {
            canvas.save();
            canvas.translate(drawX,drawy);
            canvas.drawBitmap(mBitmap, 0, 0, mPaint);
            System.out.println(drawX+"======"+drawy);
            canvas.restore();
        }
        invalidate();
    }
}
