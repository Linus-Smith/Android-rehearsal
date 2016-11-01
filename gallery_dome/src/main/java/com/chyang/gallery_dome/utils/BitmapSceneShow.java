package com.chyang.gallery_dome.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.Log;

import com.chyang.gallery_dome.anim.AnimationTime;
import com.chyang.gallery_dome.anim.CanvasAnimation;
import com.chyang.gallery_dome.anim.FloatAnimation;

import java.util.Random;

/**
 * Created by linus.yang on 2016/10/22.
 */

public class BitmapSceneShow {

    private static boolean DEBUG = true;
    private static final String TAG = "BitmapSceneShowView";

   public interface LinkSceneListener {
       void refreshView();
    }


    private static final float SCALE_SPEED = 0.20f ;
    private static final float MOVE_SPEED = SCALE_SPEED;

    private static final int SLIDESHOW_DURATION = 3000;
    private static final int TRANSITION_DURATION = 1000;

    private int width = 0;
    private int height = 0;

    private Paint mPaint;
    private int mCurrentRotation;
    private Bitmap mCurrentBitmap;
    private SlideshowAnimation mCurrentAnimation;

    private int mPrevRotation;
    private Bitmap mPrevBitmap;
    private SlideshowAnimation mPrevAnimation;

    private final FloatAnimation mTransitionAnimation = new FloatAnimation(0, 1, TRANSITION_DURATION);

    private Random mRandom = new Random();

    private LinkSceneListener mLinkSceneListener;

    public BitmapSceneShow() {
        mPaint = new Paint();
    }

    public void  setLinkSceneListener(LinkSceneListener linkSceneListener) {
        mLinkSceneListener = linkSceneListener;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }


    public void next(Bitmap bitmap , int playDuration , int transitionDuration , int rotation) {
        if(mLinkSceneListener == null) return;
        mTransitionAnimation.start();

        if (mPrevBitmap != null) {
            mPrevBitmap.recycle();
        }

        bitmap = resizeImage(bitmap, width, height);

        mPrevBitmap = mCurrentBitmap;
        mPrevAnimation = mCurrentAnimation;
        mPrevRotation = mCurrentRotation;

        mCurrentRotation = rotation;
        mCurrentBitmap = bitmap;
        if (((rotation / 90) & 0x01) == 0) {
            mCurrentAnimation = new SlideshowAnimation(
                    mCurrentBitmap.getWidth(), mCurrentBitmap.getHeight(),
                    mRandom);
        } else {
            mCurrentAnimation = new SlideshowAnimation(
                    mCurrentBitmap.getHeight(), mCurrentBitmap.getWidth(),
                    mRandom);
        }
        mCurrentAnimation.start();
        mLinkSceneListener.refreshView();
    }

    public Bitmap resizeImage(Bitmap bitmap, int w, int h) {

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


    public void onDraw(Canvas canvas) {
        if(mLinkSceneListener == null) return;
        AnimationTime.update();
        long animTime = AnimationTime.get();
        boolean requestRender = mTransitionAnimation.calculate(animTime);
        float alpha = mPrevBitmap == null ? 1f : mTransitionAnimation.get();
        if (mPrevBitmap != null && alpha != 1f) {
            requestRender |= mPrevAnimation.calculate(animTime);
            canvas.save();
             int color = (int) (255 * (1f - alpha));
            mPaint.setAlpha(color);
            mPrevAnimation.apply(canvas);
            canvas.rotate(mPrevRotation, 0, 0);
            canvas.drawBitmap(mPrevBitmap , -mPrevBitmap.getWidth() / 2, -mPrevBitmap.getHeight() / 2,mPaint);
            canvas.restore();
        }
        if (mCurrentBitmap != null) {
            requestRender |= mCurrentAnimation.calculate(animTime);
            canvas.save();
            int color = (int) (255 *  alpha);
            mPaint.setAlpha(color);
            mCurrentAnimation.apply(canvas);
            canvas.rotate(mCurrentRotation, 0, 0);
            canvas.drawBitmap(mCurrentBitmap ,-mCurrentBitmap.getWidth() / 2, -mCurrentBitmap.getHeight() / 2,mPaint);
            canvas.restore();
        }

        if (requestRender) mLinkSceneListener.refreshView();
    }




    private class SlideshowAnimation extends CanvasAnimation {
        private final int mWidth;
        private final int mHeight;

        private final PointF mMovingVector;
        private float mProgress;

        public SlideshowAnimation(int width, int height, Random random) {
            mWidth = width;
            mHeight = height;
            if(DEBUG) Log.d(TAG, "animation Width:"+width +"  height:"+height);
            mMovingVector = new PointF(
                    MOVE_SPEED * mWidth * (random.nextFloat() - 0.5f),
                    MOVE_SPEED * mHeight * (random.nextFloat() - 0.5f));
            setDuration(SLIDESHOW_DURATION);
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
