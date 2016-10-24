package com.chyang.gallery_dome.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.widget.VideoView;

import com.chyang.gallery_dome.utils.BitmapShowThread;

/**
 * Created by caihuiyang on 2016/10/24.
 */

public class StoryGalleryShowView extends VideoView {

    private final static String TAG = "StoryGalleryShowView";
    private final static boolean DEBUG = true;
    private SurfaceHolder mSgsHolder;
    private BitmapShowThread bstShowThread;

    public StoryGalleryShowView(Context context) {
        this(context, null, 0);

    }

    public StoryGalleryShowView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StoryGalleryShowView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mSgsHolder = getHolder();
        mSgsHolder.addCallback(mCallback);
        bstShowThread = new BitmapShowThread(mSgsHolder, 480, 800);

    }

    public void starShowBitmap(Bitmap bitmap) {
        if(!bstShowThread.isAlive()) bstShowThread.start();
        bstShowThread.setRunState(true);
        bstShowThread.next(bitmap, 0);

    }



    private SurfaceHolder.Callback mCallback = new SurfaceHolder.Callback() {
        @Override
        public void surfaceCreated(SurfaceHolder holder) {

        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {

        }
    };

}
