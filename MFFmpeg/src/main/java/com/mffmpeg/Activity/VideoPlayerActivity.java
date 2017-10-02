package com.mffmpeg.Activity;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.mffmpeg.R;
import com.mffmpeg.media.MediaPlayer;

public class VideoPlayerActivity extends AppCompatActivity implements SurfaceHolder.Callback2{

    private SurfaceView mSurfaceView;
    private SurfaceHolder mSurfaceHolder;
    private MediaPlayer mMediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);
        mSurfaceView = (SurfaceView) findViewById(R.id.sfView);
        mSurfaceHolder = mSurfaceView.getHolder();
        mSurfaceHolder.addCallback(this);

        mMediaPlayer = new MediaPlayer();
    }


    @Override
    public void surfaceRedrawNeeded(SurfaceHolder holder) {

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

        Canvas canvas = holder.lockCanvas();
        Paint mPaint = new Paint();
        mPaint.setColor(Color.RED);
        canvas.drawRect(0, 0, 300, 300 , mPaint);
        holder.unlockCanvasAndPost(canvas);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }
}
