package com.chyang.gallery_dome.activity;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.widget.VideoView;

import com.chyang.gallery_dome.R;

public class VideoViewDome1 extends AppCompatActivity implements SurfaceHolder.Callback, MediaPlayer.OnCompletionListener {

    private VideoView mVideoView;
    private SurfaceHolder mSurfaceHolder;
    private MyThread myThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_view_dome1);
        mVideoView = (VideoView) findViewById(R.id.vv);
        mSurfaceHolder = mVideoView.getHolder();
        mSurfaceHolder.addCallback(this);
        Uri rawUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.sintel);
        mVideoView.setVideoURI(rawUri);
        mVideoView.start();
        mVideoView.setOnCompletionListener(this);
        mVideoView.requestFocus();
        myThread = new MyThread(mSurfaceHolder);
    }


    @Override
    public void onCompletion(MediaPlayer mp) {
        System.out.println("播放完毕！");
        mVideoView.stopPlayback();
        myThread.start();
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        myThread.isRun = true;
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
            System.out.println("run------"+isRun);
            while(isRun) {
                Canvas c = null;
                System.out.println("while------"+isRun+"==="+count);
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
