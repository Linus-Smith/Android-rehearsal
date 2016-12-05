package com.yang.linus.view;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.animation.Interpolator;

import com.yang.linus.Utils.Util;

/**
 * create by linus.yang 2016/12/3
 */
public abstract class CanvasAnimation {


    public static final int PLAY_STATE_START = 1;
    public static final int PLAY_STATE_PAUSE = 2;
    public static final int PLAY_STATE_STOP = 3;

    private int  mPlayState = PLAY_STATE_STOP;

    protected int mWidth;
    protected int mHeight;
    protected Bitmap mBitmap;
    protected float mProgress;
    private static final long ANIMATION_START = -1;
    private static final long NO_ANIMATION = -2;

    private  long mStartTime = NO_ANIMATION;
    private int mCurrentDurationTime = 0;
    private int mDuration;
    private Interpolator mInterpolator;


    public CanvasAnimation(Bitmap bitmap , int rotation) {
        if(bitmap == null) throw new NullPointerException("bitmap == null");
          mBitmap = bitmap;
        if (((rotation / 90) & 0x01) == 0) {
            mWidth = bitmap.getWidth();
            mHeight = bitmap.getHeight();
        } else {
            mWidth = bitmap.getHeight();
            mHeight = bitmap.getWidth();
        }
    }

    public void start() {
        if(mPlayState == PLAY_STATE_STOP) {
            mStartTime = getCurrentTime();
            mPlayState = PLAY_STATE_START;
        } else if( mPlayState == PLAY_STATE_PAUSE ) {
            mStartTime = getCurrentTime() -  mCurrentDurationTime;
            mPlayState = PLAY_STATE_START;
            calculate(mStartTime);
        }
    }

    public void restart() {
        mStartTime = System.currentTimeMillis();
        mPlayState = PLAY_STATE_START;
        calculate(mStartTime);
    }

    public void pause() {
        if(mPlayState == PLAY_STATE_START) {
            mPlayState = PLAY_STATE_PAUSE;
        }
    }

    public void seekTo(int durationT) {
        if(durationT > mDuration && durationT < 0) return;
        if(mPlayState == PLAY_STATE_START) {
            mStartTime = getCurrentTime() -  durationT;
        } else {
            mCurrentDurationTime = durationT;
            mStartTime = getCurrentTime() -  durationT;
            calculate(getCurrentTime());
        }

    }

    public int getPlayState() {
        return mPlayState;
    }

    public int getProgressTime() {
        return mCurrentDurationTime;
    }

    public int getDurationTime() {
        return mDuration;
    }


    public void stop() {
        if(mPlayState != PLAY_STATE_STOP) {
            mPlayState = PLAY_STATE_STOP;
            mStartTime = NO_ANIMATION;
            mCurrentDurationTime = 0;
        }
    }



    public void setInterpolator(Interpolator interpolator) {
        mInterpolator = interpolator;
    }

    public void setDuration(int duration) {
        mDuration = duration;
    }

    public boolean isCompletion() {
        return mCurrentDurationTime == mDuration &&  mPlayState != PLAY_STATE_STOP;
    }

    public boolean calculate(long currentTimeMillis) {
        if(mPlayState == PLAY_STATE_STOP || mPlayState == PLAY_STATE_PAUSE) return false;
            int elapse = (int) (currentTimeMillis - mStartTime);
            mCurrentDurationTime = elapse > mDuration ? mDuration : elapse;
            float x = Util.clamp((float) elapse / mDuration, 0f, 1f);
            Interpolator i = mInterpolator;
            onCalculate(i != null ? i.getInterpolation(x) : x);
            return true;
    }


    protected abstract void onCalculate(float progress);

    public abstract int getCanvasSaveFlags();

    private long getCurrentTime() {
        return System.currentTimeMillis();
    }

    public abstract void apply(Canvas canvas);

}
