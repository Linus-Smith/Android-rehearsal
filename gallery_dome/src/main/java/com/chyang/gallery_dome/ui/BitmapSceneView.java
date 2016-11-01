package com.chyang.gallery_dome.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.chyang.gallery_dome.utils.BitmapSceneShow;

/**
 * Created by chyang on 2016/10/31.
 */

public class BitmapSceneView extends ImageView implements BitmapSceneShow.LinkSceneListener {

    public interface OnCompletionListener {
        void  onCompletion();
    }

    private BitmapSceneShow mSceneShow;
    private Bitmap mCurrentBitmap;
    private OnCompletionListener mOnCompletionListener;
    private boolean isPlayer = false;



    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(mOnCompletionListener != null) mOnCompletionListener.onCompletion();
        }
    };


    public BitmapSceneView(Context context) {
        this(context, null);
    }

    public BitmapSceneView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BitmapSceneView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mSceneShow = new BitmapSceneShow();
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

    public void setShowBitmap(Bitmap bitmap) {
        mCurrentBitmap = bitmap;
        isPlayer = false;
        setImageBitmap(mCurrentBitmap);
        invalidate();
    }

    public void showNextScene(Bitmap bitmap , int playDuration , int transitionDuration , int rotation) {
        mHandler.sendEmptyMessageDelayed(0,playDuration);
        isPlayer = true;
        mSceneShow.next(bitmap, playDuration, transitionDuration, rotation);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if(isPlayer) {
            mSceneShow.onDraw(canvas);
        } else {
            super.onDraw(canvas);
        }
    }

    @Override
    public void refreshView() {
        invalidate();
    }
}
