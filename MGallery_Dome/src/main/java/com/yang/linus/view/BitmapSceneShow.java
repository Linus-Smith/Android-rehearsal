package com.yang.linus.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.Log;
import com.yang.linus.Utils.Util;

import java.util.Random;

/**
 * Created by linus.yang on 2016/10/22.
 */

public class BitmapSceneShow implements VIPlayControl{

    private static boolean DEBUG = true;
    private static final String TAG = "BitmapSceneShowView";

    public interface LinkSceneListener {
        void refreshView();
        void onAnCompletion();
    }


    private static final float SCALE_SPEED = 0.20f;
    private static final float MOVE_SPEED = SCALE_SPEED;

    private static final int TRANSITION_DURATION = 1000;

    private int width = 0;
    private int height = 0;

    private Context mContext;
    private Paint mPaint;
    private int mCurrentRotation;
    private Bitmap mCurrentBitmap;
    private CanvasAnimation mCurrentAnimation;

    private int mPrevRotation;
    private Bitmap mPrevBitmap;
    private CanvasAnimation mPrevAnimation;

    private final FloatAnimation mTransitionAnimation = new FloatAnimation(0, 1, TRANSITION_DURATION);

    private Random mRandom = new Random();

    private LinkSceneListener mLinkSceneListener;
    private boolean panoramicFlag = false;

    private int mCurrentProgress;

    public BitmapSceneShow(Context context) {
        mContext = context;
        mPaint = new Paint();
    }

    public void setLinkSceneListener(LinkSceneListener linkSceneListener) {
        mLinkSceneListener = linkSceneListener;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public float getCurrentProgress() {
        return mCurrentProgress;
    }


    @Override
    public void prepare(Bitmap bitmap, int playDuration, int transitionDuration, int rotation) {
        if (mLinkSceneListener == null) throw new NullPointerException("Link null");
        if (mPrevBitmap != null) {
            mPrevBitmap.recycle();
        }
        panoramicFlag = false;
        mPrevBitmap = mCurrentBitmap;
        mPrevAnimation = mCurrentAnimation;
        mPrevRotation = mCurrentRotation;

        mCurrentRotation = rotation;
        mCurrentBitmap = resizeImage(bitmap, width, height);

        if (panoramicFlag) {
            mCurrentAnimation = new PanoramicAnimation(mCurrentBitmap, playDuration, rotation);
        } else {
            mCurrentAnimation = new SlideshowAnimation(mCurrentBitmap, playDuration , mRandom, rotation);
        }

        mLinkSceneListener.refreshView();
    }

    @Override
    public void start() {
        checkoutIsPrepare();
        mTransitionAnimation.start();
        mCurrentAnimation.start();
        mLinkSceneListener.refreshView();
    }

    @Override
    public void restart() {
        checkoutIsPrepare();
        mCurrentAnimation.restart();
    }

    @Override
    public void pause() {
        checkoutIsPrepare();
        mCurrentAnimation.pause();
    }

    @Override
    public void stop() {
        checkoutIsPrepare();
        mCurrentAnimation.stop();
    }

    @Override
    public void seekTo(int durationT) {
        checkoutIsPrepare();
        mCurrentAnimation.seekTo(durationT);
    }

    @Override
    public int getPlayState() {
        checkoutIsPrepare();
        return mCurrentAnimation.getPlayState();
    }

    @Override
    public int getProgressTime() {
        checkoutIsPrepare();
        return mCurrentAnimation.getProgressTime();
    }

    @Override
    public int getDurationTime() {
        checkoutIsPrepare();
        return mCurrentAnimation.getDurationTime();
    }


    private void checkoutIsPrepare() {
        if(mCurrentAnimation == null) throw new RuntimeException("Please set data , call prepare");
    }


    @Deprecated
    public void next(Bitmap bitmap, int playDuration, int transitionDuration, int rotation) {
        prepare(bitmap, playDuration, transitionDuration, rotation);
        start();
    }

    public Bitmap resizeImage(Bitmap bitmap, int w, int h) {
        Bitmap BitmapOrg = bitmap;
        int width = BitmapOrg.getWidth();
        int height = BitmapOrg.getHeight();
        int newWidth = w;
        int newHeight = h;

        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        float scale = 0;
        if ((double) width / height > 16d / 9 || (double) height / width > 16d / 9) {
            panoramicFlag = true;
        } else {
            panoramicFlag = false;
        }

        if (panoramicFlag) {
            scale = Math.max(scaleWidth, scaleHeight);
        } else {
            bitmap = Util.getScaledBitmap(bitmap, w, h);
            return  bitmap;
        }

        Matrix matrix = new Matrix();
        matrix.postScale(scale, scale);
        // if you want to rotate the Bitmap
        // matrix.postRotate(45);
        Bitmap resizedBitmap = Bitmap.createBitmap(BitmapOrg, 0, 0, width,
                height, matrix, true);
        return resizedBitmap;

    }


    public void onDraw(Canvas canvas) {
        if (mLinkSceneListener == null) return;
        long animTime = System.currentTimeMillis();
        boolean requestRender = mTransitionAnimation.calculate(animTime);
        float alpha = mPrevBitmap == null ? 1f : mTransitionAnimation.get();
        if (mPrevAnimation != null && alpha != 1f) {
            canvas.save();
            int color = (int) (255 * (1f - alpha));
            mPaint.setAlpha(color);
            mPrevAnimation.apply(canvas);
            canvas.rotate(mPrevRotation, 0, 0);
            canvas.restore();
        }
        if (mCurrentAnimation != null) {
            requestRender |= mCurrentAnimation.calculate(animTime);
            canvas.save();
            int color = (int) (255 * alpha);
            mPaint.setAlpha(color);
            mCurrentAnimation.apply(canvas);
            canvas.rotate(mCurrentRotation, 0, 0);
            canvas.restore();
            if(mCurrentAnimation.isCompletion()) {
                mCurrentAnimation.stop();
                mLinkSceneListener.onAnCompletion();
            }
        }

        if (requestRender) mLinkSceneListener.refreshView();
    }


    private class SlideshowAnimation extends CanvasAnimation {

        private final PointF mMovingVector;

        public SlideshowAnimation(Bitmap bitmap, int playDuration , Random random , int rotation) {
            super(bitmap, rotation);
            if (DEBUG) Log.d(TAG, "animation Width:" + width + "  height:" + height);
            mMovingVector = new PointF(
                    MOVE_SPEED * mWidth * (random.nextFloat() - 0.5f),
                    MOVE_SPEED * mHeight * (random.nextFloat() - 0.5f));
            setDuration(playDuration);
        }

        @Override
        public void apply(Canvas canvas) {
            int viewWidth = width;
            int viewHeight = height;

            float initScale = Math.min((float)
                    viewWidth / mWidth, (float) viewHeight / mHeight);
            float scale = initScale * (1 + SCALE_SPEED * mProgress);

            float centerX = viewWidth / 2 + mMovingVector.x * mProgress;
            float centerY = viewHeight / 2 + mMovingVector.y * mProgress;
            canvas.translate(centerX, centerY);
            canvas.scale(scale, scale);

            canvas.drawBitmap(mBitmap, -mBitmap.getWidth() / 2, -mBitmap.getHeight() / 2, mPaint);

        }


        @Override
        public int getCanvasSaveFlags() {
            return Canvas.MATRIX_SAVE_FLAG;
        }

        @Override
        protected void onCalculate(float progress) {
            mProgress = progress;
        }
    }

    private class PanoramicAnimation extends CanvasAnimation {

        public PanoramicAnimation(Bitmap bitmap, int playDuration , int rotation) {
            super(bitmap, rotation);
            if (DEBUG) Log.d(TAG, "animation Width:" + width + "  height:" + height);
            setDuration((int) (playDuration * (1 - mProgress)));
        }

        @Override
        public void apply(Canvas canvas) {
            int viewWidth = width;
            int viewHeight = height;
            Matrix matrix = new Matrix();
            float xScale = (float) viewWidth / mWidth;
            float yScale = (float) viewHeight / mHeight;

            if (xScale > yScale) {
                float centerY = (viewHeight - mHeight) * mProgress;
                matrix.setTranslate(0, centerY);
            } else {
                float centerX = (viewWidth - mWidth) * mProgress;
                matrix.setTranslate(centerX, 0);
            }
            canvas.drawBitmap(mCurrentBitmap, matrix, mPaint);
        }

        @Override
        public int getCanvasSaveFlags() {
            return Canvas.MATRIX_SAVE_FLAG;
        }

        @Override
        protected void onCalculate(float progress) {
            mProgress = progress;
        }
    }

}
