package com.yang.mdevelopers.Utils;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;

/**
 * Created by caihuiyang on 2017/3/5.
 */

public class ImageScaleValue {

    private static final boolean DISABLEZOOM = false;
    private Point mImageShowSize = new Point();
    private Point mTranslation = new Point();
    private Point mOriginalTranslation = new Point();
    private Rect mOriginalBounds  = new Rect(0, 0, 1080, 1920); //TODO
    private float mMaxScaleFactor = 3.0f; // TODO: base this on the current view / image
    private float mScaleFactor = 1.0f;
    private int mShadowMargin = 0; // not scaled, fixed in the asset



    public void setImageShowSize(int w, int h) {
        if (mImageShowSize.x != w || mImageShowSize.y != h) {
            mImageShowSize.set(w, h);
            float maxWidth = mOriginalBounds.width() / (float) w;
            float maxHeight = mOriginalBounds.height() / (float) h;
            mMaxScaleFactor = Math.max(3.f, Math.max(maxWidth, maxHeight));
        }
    }

    public float getMaxScaleFactor() {
        if (DISABLEZOOM) {
            return 1;
        }
        return mMaxScaleFactor;
    }

    public void setMaxScaleFactor(float maxScaleFactor) {
        mMaxScaleFactor = maxScaleFactor;
    }

    public Rect getOriginalBounds() {
        return mOriginalBounds;
    }

    public void setOriginalBounds(Rect r) {
        mOriginalBounds = r;
    }

    public Point getTranslation() {
        return mTranslation;
    }

    public void setTranslation(Point translation) {
        if (DISABLEZOOM) {
            mTranslation.x = 0;
            mTranslation.y = 0;
            return;
        }
        mTranslation.x = translation.x;
        mTranslation.y = translation.y;
    }

    public float getScaleFactor() {
        return mScaleFactor;
    }

    public void setScaleFactor(float scaleFactor) {
        if (DISABLEZOOM) {
            return;
        }
        if (scaleFactor == mScaleFactor) {
            return;
        }
        mScaleFactor = scaleFactor;
     //   invalidatePartialPreview();
    }

    public Point getOriginalTranslation() {
        return mOriginalTranslation;
    }

    public void setOriginalTranslation(Point originalTranslation) {
        if (DISABLEZOOM) {
            return;
        }
        mOriginalTranslation.x = originalTranslation.x;
        mOriginalTranslation.y = originalTranslation.y;
    }


    public void resetTranslation() {
        mTranslation.x = 0;
        mTranslation.y = 0;
        //  needsUpdatePartialPreview();
    }


    public Matrix originalImageToScreen() {
        return computeImageToScreen(null, 0, true);
    }

    public Matrix computeImageToScreen(Bitmap bitmapToDraw,
                                       float rotate,
                                       boolean applyGeometry) {



        if (getOriginalBounds() == null
                || mImageShowSize.x == 0
                || mImageShowSize.y == 0) {
            return null;
        }

        Matrix m = null;
        float scale = 1f;
        float translateX = 0;
        float translateY = 0;

        if (applyGeometry) {
            m =   getCropSelectionToScreenMatrix(null,
                    mOriginalBounds.width(), mOriginalBounds.height(),
                    mImageShowSize.x, mImageShowSize.y);
        } else if (bitmapToDraw != null) {
            m = new Matrix();
            RectF size = new RectF(0, 0,
                    bitmapToDraw.getWidth(),
                    bitmapToDraw.getHeight());
            scale = mImageShowSize.x / size.width();
//            if (size.width() < size.height()) {
//                scale = mImageShowSize.y / size.height();
//            }

            translateX = (mImageShowSize.x - (size.width() * scale)) / 2.0f;
            translateY = (mImageShowSize.y - (size.height() * scale)) / 2.0f;

        } else {
            return null;
        }

        Point translation = getTranslation();
        m.postScale(scale, scale);
        m.postRotate(rotate, mImageShowSize.x / 2.0f, mImageShowSize.y / 2.0f);
        m.postTranslate(translateX, translateY);
        m.postTranslate(mShadowMargin, mShadowMargin);
        m.postScale(getScaleFactor(), getScaleFactor(),
                mImageShowSize.x / 2.0f,
                mImageShowSize.y / 2.0f);
        m.postTranslate(translation.x * getScaleFactor(),
                translation.y * getScaleFactor());
        return m;
    }



    public static Matrix getCropSelectionToScreenMatrix(RectF outCrop,
                                                        int bitmapWidth, int bitmapHeight, int viewWidth, int viewHeight) {
        Matrix m = getFullGeometryMatrix(bitmapWidth, bitmapHeight);
        RectF crop = getTrueCropRect(bitmapWidth, bitmapHeight);
        float scale = scale(crop.width(), crop.height(), viewWidth, viewHeight);
        m.postScale(scale, scale);
        scaleRect(crop, scale);
        m.postTranslate(viewWidth / 2f - crop.centerX(), viewHeight / 2f - crop.centerY());
        if (outCrop != null) {
            crop.offset(viewWidth / 2f - crop.centerX(), viewHeight / 2f - crop.centerY());
            outCrop.set(crop);
        }
        return m;
    }


    private static Matrix getFullGeometryMatrix(int bitmapWidth,
                                                int bitmapHeight) {
        float centerX = bitmapWidth / 2f;
        float centerY = bitmapHeight / 2f;
        Matrix m = new Matrix();
        m.setTranslate(-centerX, -centerY);
        //  m.postRotate(holder.straighten + holder.rotation.value());
        return m;
    }


    public static RectF getTrueCropRect(int bitmapWidth, int bitmapHeight) {
        RectF sNilRect = new RectF(0, 0, 1, 1);
        RectF r = new RectF(sNilRect);
        findScaledCrop(r, bitmapWidth, bitmapHeight);
        float s =  0;
        Matrix m1 = getFullGeometryMatrix(bitmapWidth, bitmapHeight);
        m1.mapRect(r);
        return r;
    }

    public static float scale(float oldWidth, float oldHeight, float newWidth, float newHeight) {
        if (oldHeight == 0 || oldWidth == 0 || (oldWidth == newWidth && oldHeight == newHeight)) {
            return 1;
        }
        return Math.min(newWidth / oldWidth, newHeight / oldHeight);
    }

    public static void scaleRect(RectF r, float scale) {
        r.set(r.left * scale, r.top * scale, r.right * scale, r.bottom * scale);
    }


    /**
     * Takes a crop rect contained by [0, 0, 1, 1] and scales it by the height
     * and width of the image rect.
     */
    public static void findScaledCrop(RectF crop, int bitmapWidth, int bitmapHeight) {
        crop.left *= bitmapWidth;
        crop.top *= bitmapHeight;
        crop.right *= bitmapWidth;
        crop.bottom *= bitmapHeight;
    }

}
