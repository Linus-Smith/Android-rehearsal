/*
 * Copyright (C) 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.yang.mdevelopers.view;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.v4.widget.EdgeEffectCompat;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.GestureDetector.OnDoubleTapListener;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;

import com.yang.mdevelopers.R;
import com.yang.mdevelopers.Utils.ImageScaleValue;


public class ImageShow extends View implements OnGestureListener,
        ScaleGestureDetector.OnScaleGestureListener,
        OnDoubleTapListener {

    private static final String LOGTAG = "ImageShow";
    private static final boolean ENABLE_ZOOMED_COMPARISON = false;

    protected Paint mPaint = new Paint();

    private GestureDetector mGestureDetector = null;
    private ScaleGestureDetector mScaleGestureDetector = null;

    protected Rect mImageBounds = new Rect();
    private boolean mOriginalDisabled = false;
    private boolean mTouchShowOriginal = false;
    private long mTouchShowOriginalDate = 0;
    private final long mTouchShowOriginalDelayMin = 200; // 200ms


    private int mShadowMargin = 0; // not scaled, fixed in the asset

    private Point mTouchDown = new Point();
    private Point mTouch = new Point();
    private boolean mFinishedScalingOperation = false;

    private boolean mZoomIn = false;
    Point mOriginalTranslation = new Point();
    float mOriginalScale;
    float mStartFocusX, mStartFocusY;

    private EdgeEffectCompat mEdgeEffect = null;
    private static final int EDGE_LEFT = 1;
    private static final int EDGE_TOP = 2;
    private static final int EDGE_RIGHT = 3;
    private static final int EDGE_BOTTOM = 4;
    private int mCurrentEdgeEffect = 0;
    private int mEdgeSize = 100;

    private static final int mAnimationSnapDelay = 200;
    private static final int mAnimationZoomDelay = 400;
    private ValueAnimator mAnimatorScale = null;
    private ValueAnimator mAnimatorTranslateX = null;
    private ValueAnimator mAnimatorTranslateY = null;


    //new
    private Bitmap image;
    private ImageScaleValue mImageScaleValue;


    private enum InteractionMode {
        NONE,
        SCALE,
        MOVE
    }

    InteractionMode mInteractionMode = InteractionMode.NONE;


    public ImageShow(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setupImageShow(context);
    }

    public ImageShow(Context context, AttributeSet attrs) {
        super(context, attrs);
        setupImageShow(context);

    }

    public ImageShow(Context context) {
        super(context);
        setupImageShow(context);
    }

    private void setupImageShow(Context context) {
        image = BitmapFactory.decodeResource(context.getResources(), R.mipmap.timg);
        mImageScaleValue = new ImageScaleValue();

        Resources res = context.getResources();
        setupGestureDetector(context);
        mEdgeEffect = new EdgeEffectCompat(context);
        mEdgeSize = res.getDimensionPixelSize(R.dimen.activity_horizontal_margin);
    }

    public void setupGestureDetector(Context context) {
        mGestureDetector = new GestureDetector(context, this);
        mScaleGestureDetector = new ScaleGestureDetector(context, this);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int parentWidth = MeasureSpec.getSize(widthMeasureSpec);
        int parentHeight = MeasureSpec.getSize(heightMeasureSpec);

        setMeasuredDimension(parentWidth, parentHeight);
    }

    @Override
    public void onDraw(Canvas canvas) {
        mPaint.reset();
        mPaint.setAntiAlias(true);
        mPaint.setFilterBitmap(true);

        mImageScaleValue.setImageShowSize(getWidth(), getHeight());

        canvas.save();

        drawImageAndAnimate(canvas, image);

        canvas.restore();

        if (!mEdgeEffect.isFinished()) {
            canvas.save();
            float dx = (getHeight() - getWidth()) / 2f;
            if (getWidth() > getHeight()) {
                dx = -(getWidth() - getHeight()) / 2f;
            }
            if (mCurrentEdgeEffect == EDGE_BOTTOM) {
                canvas.rotate(180, getWidth() / 2, getHeight() / 2);
            } else if (mCurrentEdgeEffect == EDGE_RIGHT) {
                canvas.rotate(90, getWidth() / 2, getHeight() / 2);
                canvas.translate(0, dx);
            } else if (mCurrentEdgeEffect == EDGE_LEFT) {
                canvas.rotate(270, getWidth() / 2, getHeight() / 2);
                canvas.translate(0, dx);
            }
            if (mCurrentEdgeEffect != 0) {
                mEdgeEffect.draw(canvas);
            }
            canvas.restore();
            invalidate();
        } else {
            mCurrentEdgeEffect = 0;
        }

    }


    public void drawImageAndAnimate(Canvas canvas,
                                    Bitmap image) {
        if (image == null) {
            return;
        }

        Matrix m = mImageScaleValue.computeImageToScreen(image, 0, false);
        if (m == null) {
            return;
        }

        canvas.save();

        RectF d = new RectF(0, 0, image.getWidth(), image.getHeight());
        m.mapRect(d);
        d.roundOut(mImageBounds);
        canvas.drawBitmap(image, m, mPaint);
        canvas.restore();
    }



    public boolean scaleInProgress() {
        return mScaleGestureDetector.isInProgress();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        int action = event.getAction();
        action = action & MotionEvent.ACTION_MASK;

        mGestureDetector.onTouchEvent(event);
        boolean scaleInProgress = scaleInProgress();
        mScaleGestureDetector.onTouchEvent(event);
        if (mInteractionMode == InteractionMode.SCALE) {
            return true;
        }
        if (!scaleInProgress() && scaleInProgress) {
            // If we were scaling, the scale will stop but we will
            // still issue an ACTION_UP. Let the subclasses know.
            mFinishedScalingOperation = true;
        }

        int ex = (int) event.getX();
        int ey = (int) event.getY();
        if (action == MotionEvent.ACTION_DOWN) {
            mInteractionMode = InteractionMode.MOVE;
            mTouchDown.x = ex;
            mTouchDown.y = ey;
            mTouchShowOriginalDate = System.currentTimeMillis();

            mImageScaleValue.setOriginalTranslation(mImageScaleValue.getTranslation());
        }

        if (action == MotionEvent.ACTION_MOVE && mInteractionMode == InteractionMode.MOVE) {
            mTouch.x = ex;
            mTouch.y = ey;

            float scaleFactor = mImageScaleValue.getScaleFactor();
            if (scaleFactor > 1 && (!ENABLE_ZOOMED_COMPARISON || event.getPointerCount() == 2)) {
                float translateX = (mTouch.x - mTouchDown.x) / scaleFactor;
                float translateY = (mTouch.y - mTouchDown.y) / scaleFactor;

                Point originalTranslation = mImageScaleValue.getOriginalTranslation();
                Point translation = mImageScaleValue.getTranslation();

                translation.x = (int) (originalTranslation.x + translateX);
                translation.y = (int) (originalTranslation.y + translateY);
                mImageScaleValue.setTranslation(translation);
                mTouchShowOriginal = false;
            } else if (enableComparison() && !mOriginalDisabled
                    && (System.currentTimeMillis() - mTouchShowOriginalDate
                    > mTouchShowOriginalDelayMin)
                    && event.getPointerCount() == 1) {
                mTouchShowOriginal = true;
            }
        }

        if (action == MotionEvent.ACTION_UP
                || action == MotionEvent.ACTION_CANCEL
                || action == MotionEvent.ACTION_OUTSIDE) {
            mInteractionMode = InteractionMode.NONE;
            mTouchShowOriginal = false;
            mTouchDown.x = 0;
            mTouchDown.y = 0;
            mTouch.x = 0;
            mTouch.y = 0;
            if (mImageScaleValue.getScaleFactor() <= 1) {
                mImageScaleValue.setScaleFactor(1);
                mImageScaleValue.resetTranslation();
            }
        }

        float scaleFactor = mImageScaleValue.getScaleFactor();
        Point translation = mImageScaleValue.getTranslation();
        constrainTranslation(translation, scaleFactor);
        mImageScaleValue.setTranslation(translation);

        invalidate();
        return true;
    }

    private void startAnimTranslation(int fromX, int toX,
                                      int fromY, int toY, int delay) {
        if (fromX == toX && fromY == toY) {
            return;
        }
        if (mAnimatorTranslateX != null) {
            mAnimatorTranslateX.cancel();
        }
        if (mAnimatorTranslateY != null) {
            mAnimatorTranslateY.cancel();
        }
        mAnimatorTranslateX = ValueAnimator.ofInt(fromX, toX);
        mAnimatorTranslateY = ValueAnimator.ofInt(fromY, toY);
        mAnimatorTranslateX.setDuration(delay);
        mAnimatorTranslateY.setDuration(delay);
        mAnimatorTranslateX.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Point translation = mImageScaleValue.getTranslation();
                translation.x = (Integer) animation.getAnimatedValue();
                mImageScaleValue.setTranslation(translation);
                invalidate();
            }
        });
        mAnimatorTranslateY.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Point translation = mImageScaleValue.getTranslation();
                translation.y = (Integer) animation.getAnimatedValue();
                mImageScaleValue.setTranslation(translation);
                invalidate();
            }
        });
        mAnimatorTranslateX.start();
        mAnimatorTranslateY.start();
    }

    private void applyTranslationConstraints() {

        float scaleFactor = mImageScaleValue.getScaleFactor();
        Point translation = mImageScaleValue.getTranslation();
        int x = translation.x;
        int y = translation.y;
        constrainTranslation(translation, scaleFactor);

        if (x != translation.x || y != translation.y) {
            startAnimTranslation(x, translation.x,
                    y, translation.y,
                    mAnimationSnapDelay);
        }
    }

    protected boolean enableComparison() {
        return true;
    }

    @Override
    public boolean onDoubleTap(MotionEvent arg0) {
        mZoomIn = !mZoomIn;
        float scale = 1.0f;
        final float x = arg0.getX();
        final float y = arg0.getY();
        if (mZoomIn) {
            scale = mImageScaleValue.getMaxScaleFactor();
        }

        if (scale != mImageScaleValue.getScaleFactor()) {
            if (mAnimatorScale != null) {
                mAnimatorScale.cancel();
            }
            mAnimatorScale = ValueAnimator.ofFloat(mImageScaleValue.getScaleFactor(),
                    scale
            );
            float translateX = (getWidth() / 2 - x);
            float translateY = (getHeight() / 2 - y);
            Point translation = mImageScaleValue.getTranslation();
            int startTranslateX = translation.x;
            int startTranslateY = translation.y;
            if (scale != 1.0f) {
                translation.x = (int) (mOriginalTranslation.x + translateX);
                translation.y = (int) (mOriginalTranslation.y + translateY);
            } else {
                translation.x = 0;
                translation.y = 0;
            }
            constrainTranslation(translation, scale);

            startAnimTranslation(startTranslateX, translation.x,
                    startTranslateY, translation.y,
                    mAnimationZoomDelay);
            mAnimatorScale.setDuration(mAnimationZoomDelay);
            mAnimatorScale.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    mImageScaleValue.setScaleFactor((Float) animation.getAnimatedValue());
                    invalidate();
                }
            });
            mAnimatorScale.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    applyTranslationConstraints();
                    invalidate();
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });
            mAnimatorScale.start();
        }
        return true;
    }

    private void constrainTranslation(Point translation, float scale) {
        int currentEdgeEffect = 0;
        if (scale <= 1) {
            mCurrentEdgeEffect = 0;
            mEdgeEffect.finish();
            return;
        }
        Matrix originalToScreen = mImageScaleValue.originalImageToScreen();
        Rect originalBounds = mImageScaleValue.getOriginalBounds();
        RectF screenPos = new RectF(originalBounds);
        originalToScreen.mapRect(screenPos);

        boolean rightConstraint = screenPos.right < getWidth() - mShadowMargin;
        boolean leftConstraint = screenPos.left > mShadowMargin;
        boolean topConstraint = screenPos.top > mShadowMargin;
        boolean bottomConstraint = screenPos.bottom < getHeight() - mShadowMargin;

        if (screenPos.width() > getWidth()) {
            if (rightConstraint && !leftConstraint) {
                float tx = screenPos.right - translation.x * scale;
                translation.x = (int) ((getWidth() - mShadowMargin - tx) / scale);
                currentEdgeEffect = EDGE_RIGHT;
            } else if (leftConstraint && !rightConstraint) {
                float tx = screenPos.left - translation.x * scale;
                translation.x = (int) ((mShadowMargin - tx) / scale);
                currentEdgeEffect = EDGE_LEFT;
            }
        } else {
            float tx = screenPos.right - translation.x * scale;
            float dx = (getWidth() - 2 * mShadowMargin - screenPos.width()) / 2f;
            translation.x = (int) ((getWidth() - mShadowMargin - tx - dx) / scale);
        }

        if (screenPos.height() > getHeight()) {
            if (bottomConstraint && !topConstraint) {
                float ty = screenPos.bottom - translation.y * scale;
                translation.y = (int) ((getHeight() - mShadowMargin - ty) / scale);
                currentEdgeEffect = EDGE_BOTTOM;
            } else if (topConstraint && !bottomConstraint) {
                float ty = screenPos.top - translation.y * scale;
                translation.y = (int) ((mShadowMargin - ty) / scale);
                currentEdgeEffect = EDGE_TOP;
            }
        } else {
            float ty = screenPos.bottom - translation.y * scale;
            float dy = (getHeight() - 2 * mShadowMargin - screenPos.height()) / 2f;
            translation.y = (int) ((getHeight() - mShadowMargin - ty - dy) / scale);
        }

        if (mCurrentEdgeEffect != currentEdgeEffect) {
            if (mCurrentEdgeEffect == 0 || currentEdgeEffect != 0) {
                mCurrentEdgeEffect = currentEdgeEffect;
                mEdgeEffect.finish();
            }
            mEdgeEffect.setSize(getWidth(), mEdgeSize);
        }
        if (currentEdgeEffect != 0) {
            mEdgeEffect.onPull(mEdgeSize);
        }
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent arg0) {
        return false;
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent arg0) {
        return false;
    }

    @Override
    public boolean onDown(MotionEvent arg0) {
        return false;
    }

    @Override
    public boolean onFling(MotionEvent startEvent, MotionEvent endEvent, float arg2, float arg3) {

        if (endEvent.getPointerCount() == 2) {
            return false;
        }
        return true;
    }

    @Override
    public void onLongPress(MotionEvent arg0) {
    }

    @Override
    public boolean onScroll(MotionEvent arg0, MotionEvent arg1, float arg2, float arg3) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent arg0) {
    }

    @Override
    public boolean onSingleTapUp(MotionEvent arg0) {
        return false;
    }

    @Override
    public boolean onScale(ScaleGestureDetector detector) {

        float scaleFactor = mImageScaleValue.getScaleFactor();

        scaleFactor = scaleFactor * detector.getScaleFactor();
        if (scaleFactor > mImageScaleValue.getMaxScaleFactor()) {
            scaleFactor = mImageScaleValue.getMaxScaleFactor();
        }
        if (scaleFactor < 1.0f) {
            scaleFactor = 1.0f;
        }
        mImageScaleValue.setScaleFactor(scaleFactor);
        scaleFactor = mImageScaleValue.getScaleFactor();
        float focusx = detector.getFocusX();
        float focusy = detector.getFocusY();
        float translateX = (focusx - mStartFocusX) / scaleFactor;
        float translateY = (focusy - mStartFocusY) / scaleFactor;
        Point translation = mImageScaleValue.getTranslation();
        translation.x = (int) (mOriginalTranslation.x + translateX);
        translation.y = (int) (mOriginalTranslation.y + translateY);
        mImageScaleValue.setTranslation(translation);
        invalidate();
        return true;
    }

    @Override
    public boolean onScaleBegin(ScaleGestureDetector detector) {

        Point pos = mImageScaleValue.getTranslation();
        mOriginalTranslation.x = pos.x;
        mOriginalTranslation.y = pos.y;
        mOriginalScale = mImageScaleValue.getScaleFactor();
        mStartFocusX = detector.getFocusX();
        mStartFocusY = detector.getFocusY();
        mInteractionMode = InteractionMode.SCALE;
        return true;
    }

    @Override
    public void onScaleEnd(ScaleGestureDetector detector) {
        mInteractionMode = InteractionMode.NONE;
        if (mImageScaleValue.getScaleFactor() < 1) {
            mImageScaleValue.setScaleFactor(1);
            invalidate();
        }
    }

    public boolean didFinishScalingOperation() {
        if (mFinishedScalingOperation) {
            mFinishedScalingOperation = false;
            return true;
        }
        return false;
    }

}
