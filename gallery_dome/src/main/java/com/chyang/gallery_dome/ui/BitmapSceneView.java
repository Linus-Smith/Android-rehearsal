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

    private boolean isPlaying = false;
    private BitmapSceneShow mSceneShow;
    private OnCompletionListener mOnCompletionListener;



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

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        mSceneShow.setWidth(getWidth());
        mSceneShow.setHeight(getHeight());

    }

    public void showNextScene(Bitmap bitmap , int playDuration , int transitionDuration , int rotation) {
        mHandler.sendEmptyMessageDelayed(0,playDuration);
        mSceneShow.next(bitmap, playDuration, transitionDuration, rotation);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mSceneShow.onDraw(canvas);
    }

    @Override
    public void refreshView() {
        invalidate();
    }
}
