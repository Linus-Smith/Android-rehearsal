package com.yang.linus.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

import java.util.Random;

/**
 * Created by Linus on 2017/4/25.
 */

public class MyCenterView extends View {

    private Paint mPaint;
    private Rect mLeftR;
    private Rect mRightR;
    private String textL = "报告辉哥，我是左边 ";
    private String textR = "报告辉哥, 我是右边";
    private Random mRandom;

    public MyCenterView(Context context) {
        super(context);
        init();
    }

    public MyCenterView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyCenterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init() {
       mLeftR = new Rect();
        mRightR = new Rect();
        mPaint = new Paint();
        mPaint.setStrokeWidth(10);
        mPaint.setColor(Color.RED);
        mPaint.setTextSize(40);
        mPaint.getTextBounds(textL, 0, textL.length(), mLeftR);
        mPaint.getTextBounds(textR, 0, textR.length(), mRightR);
        mRandom = new Random();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        mPaint.setColor(Color.argb(mRandom.nextInt(255),mRandom.nextInt(255), mRandom.nextInt(255), mRandom.nextInt(255)));
        canvas.drawLine(getWidth() / 2,getTop(), getWidth() / 2, getBottom(), mPaint);
        canvas.restore();
        canvas.save();
        mPaint.setColor(Color.argb(mRandom.nextInt(255),mRandom.nextInt(255), mRandom.nextInt(255), mRandom.nextInt(255)));
        canvas.rotate(mRandom.nextInt(360),getWidth() / 4 ,   getHeight() / 2);
        canvas.drawText(textL,getWidth() / 4  - mLeftR.width() / 2 , getHeight() / 2 -  mLeftR.height() / 2, mPaint);
        canvas.restore();
        canvas.save();
        mPaint.setColor(Color.argb(mRandom.nextInt(255),mRandom.nextInt(255), mRandom.nextInt(255), mRandom.nextInt(255)));
        canvas.rotate(mRandom.nextInt(360),getWidth() / 2 + (getWidth() / 4  ),  getHeight() / 2 );
        canvas.drawText(textR, getWidth() / 2 + (getWidth() / 4  - mRightR.width() / 2 )  , getHeight() / 2 -  mLeftR.height() / 2, mPaint);
        canvas.restore();

        canvas.save();
        mPaint.setColor(Color.argb(mRandom.nextInt(255),mRandom.nextInt(255), mRandom.nextInt(255), mRandom.nextInt(255)));
        canvas.drawCircle(mRandom.nextInt(getWidth()), mRandom.nextInt(getHeight()), mRandom.nextInt(getHeight() / 6), mPaint);
        canvas.restore();

        canvas.save();
        mPaint.setColor(Color.argb(mRandom.nextInt(255),mRandom.nextInt(255), mRandom.nextInt(255), mRandom.nextInt(255)));
        canvas.drawCircle(mRandom.nextInt(getWidth()), mRandom.nextInt(getHeight()), mRandom.nextInt(getHeight() / 6), mPaint);
        canvas.restore();
        canvas.save();
        mPaint.setColor(Color.argb(mRandom.nextInt(255),mRandom.nextInt(255), mRandom.nextInt(255), mRandom.nextInt(255)));
        canvas.drawCircle(mRandom.nextInt(getWidth()), mRandom.nextInt(getHeight()), mRandom.nextInt(getHeight() / 6), mPaint);
        canvas.restore();
        canvas.save();
        mPaint.setColor(Color.argb(mRandom.nextInt(255),mRandom.nextInt(255), mRandom.nextInt(255), mRandom.nextInt(255)));
        canvas.drawCircle(mRandom.nextInt(getWidth()), mRandom.nextInt(getHeight()), mRandom.nextInt(getHeight() / 6), mPaint);
        canvas.restore();
        canvas.save();
        mPaint.setColor(Color.argb(mRandom.nextInt(255),mRandom.nextInt(255), mRandom.nextInt(255), mRandom.nextInt(255)));
        canvas.drawCircle(mRandom.nextInt(getWidth()), mRandom.nextInt(getHeight()), mRandom.nextInt(getHeight() / 6), mPaint);
        canvas.restore();

        canvas.save();
        mPaint.setColor(Color.argb(mRandom.nextInt(255),mRandom.nextInt(255), mRandom.nextInt(255), mRandom.nextInt(255)));
        canvas.drawCircle(mRandom.nextInt(getWidth()), mRandom.nextInt(getHeight()), mRandom.nextInt(getHeight() / 6), mPaint);
        canvas.restore();

        canvas.save();
        mPaint.setColor(Color.argb(mRandom.nextInt(255),mRandom.nextInt(255), mRandom.nextInt(255), mRandom.nextInt(255)));
        canvas.drawCircle(mRandom.nextInt(getWidth()), mRandom.nextInt(getHeight()), mRandom.nextInt(getHeight() / 6), mPaint);
        canvas.restore();
        canvas.save();
        mPaint.setColor(Color.argb(mRandom.nextInt(255),mRandom.nextInt(255), mRandom.nextInt(255), mRandom.nextInt(255)));
        canvas.drawCircle(mRandom.nextInt(getWidth()), mRandom.nextInt(getHeight()), mRandom.nextInt(getHeight() / 6), mPaint);
        canvas.restore();
        canvas.save();
        mPaint.setColor(Color.argb(mRandom.nextInt(255),mRandom.nextInt(255), mRandom.nextInt(255), mRandom.nextInt(255)));
        canvas.drawCircle(mRandom.nextInt(getWidth()), mRandom.nextInt(getHeight()), mRandom.nextInt(getHeight() / 6), mPaint);
        canvas.restore();
        canvas.save();
        mPaint.setColor(Color.argb(mRandom.nextInt(255),mRandom.nextInt(255), mRandom.nextInt(255), mRandom.nextInt(255)));
        canvas.drawCircle(mRandom.nextInt(getWidth()), mRandom.nextInt(getHeight()), mRandom.nextInt(getHeight() / 6), mPaint);
        canvas.restore();

        postInvalidateDelayed(100);
    }
}
