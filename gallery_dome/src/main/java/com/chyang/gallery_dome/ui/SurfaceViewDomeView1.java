package com.chyang.gallery_dome.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by caihuiyang on 2016/10/23.
 */

public class SurfaceViewDomeView1 extends SurfaceView implements SurfaceHolder.Callback{

    private SurfaceHolder holder;
    private MyThread myThread;

    public SurfaceViewDomeView1(Context context) {
        this(context, null);
    }

    public SurfaceViewDomeView1(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SurfaceViewDomeView1(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        holder = getHolder();
        holder.addCallback(this);
        myThread = new MyThread(holder);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        myThread.isRun = true;
        myThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        myThread.isRun = false;
    }


    class MyThread extends  Thread {

        public SurfaceHolder holder;
        public boolean isRun = true;

        public  MyThread(SurfaceHolder holder) {
            this.holder = holder;
        }

        @Override
        public void run() {
            super.run();
            int count = 0;
            while(isRun) {
                Canvas c = null;
                try {
                    synchronized (holder) {
                        c = holder.lockCanvas();
                        c.drawColor(Color.BLACK);
                        Paint p = new Paint();
                        p.setColor(Color.WHITE);
                        Rect r = new Rect(100, 50, 300, 250);
                        c.drawRect(r, p);
                        c.drawText("这是"+(count++)+"秒", 100, 310, p);
                    }
                }catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if(c != null) {
                        holder.unlockCanvasAndPost(c);
                    }
                }
            }
        }
    }
}
