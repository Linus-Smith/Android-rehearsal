package com.yang.linus.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.ImageView;




/**
 * Created by linus.yang on 2016/10/31.
 */

public class BitmapSceneView extends ImageView implements BitmapSceneShow.LinkSceneListener , VIPlayControl {

    public interface OnCompletionListener {
        void onCompletion();
    }

    private BitmapSceneShow mSceneShow;
    private OnCompletionListener mOnCompletionListener;


    public BitmapSceneView(Context context) {
        this(context, null);
    }

    public BitmapSceneView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BitmapSceneView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mSceneShow = new BitmapSceneShow(context);
        mSceneShow.setLinkSceneListener(this);
    }

    public void setOnCompletionListener(OnCompletionListener onCompletionListener) {
        mOnCompletionListener = onCompletionListener;
    }

    public void setCanvasWidth(int canvasWidth) {
        mSceneShow.setWidth(canvasWidth);
    }

    public void setCanvasHeight(int canvasHeight) {
        mSceneShow.setHeight(canvasHeight);
    }

    @Deprecated
    public void showNextScene(Bitmap bitmap, int playDuration, int transitionDuration, int rotation) {
        mSceneShow.next(bitmap, playDuration, transitionDuration, rotation);
    }

    @Override
    public void prepare(Bitmap bitmap, int playDuration, int transitionDuration, int rotation) {
        mSceneShow.prepare(bitmap, playDuration, transitionDuration, rotation);
    }

    @Override
    public void start() {
        mSceneShow.start();
    }

    @Override
    public void restart() {
        mSceneShow.restart();
    }

    @Override
    public void pause() {
       mSceneShow.pause();
    }

    @Override
    public void stop() {
        mSceneShow.stop();
    }

    @Override
    public void seekTo(int durationT) {
        mSceneShow.seekTo(durationT);
    }

    @Override
    public int getPlayState() {
        return mSceneShow.getPlayState();
    }

    @Override
    public int getProgressTime() {
        return mSceneShow.getProgressTime();
    }

    @Override
    public int getDurationTime() {
        return mSceneShow.getDurationTime();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mSceneShow.onDraw(canvas);
    }

    @Override
    public void refreshView() {
        invalidate();
    }

    @Override
    public void onAnCompletion() {
        if (mOnCompletionListener != null) mOnCompletionListener.onCompletion();
    }
}
