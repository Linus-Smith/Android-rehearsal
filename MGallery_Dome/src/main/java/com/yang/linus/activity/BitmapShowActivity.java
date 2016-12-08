package com.yang.linus.activity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.yang.linus.R;
import com.yang.linus.view.BitmapSceneView;

public class BitmapShowActivity extends AppCompatActivity implements BitmapSceneView.OnCompletionListener , View.OnClickListener{

    private BitmapSceneView mBitmapSceneView;
    private Button btPrepare;
    private Button btStart;
    private Button btRestartr;
    private Button btPause;
    private Button btStop;
    private Button btPlayState;
    private SeekBar mSeekBar;

    private TextView tvCuTime;
    private TextView tvDuTime;

    private int bitmapIndex = 1;

    private Handler mHandler = new Handler(){
        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            tvCuTime.setText(mBitmapSceneView.getProgressTime() +" ");
            mSeekBar.setProgress(mBitmapSceneView.getProgressTime(), true);
            mHandler.sendEmptyMessageDelayed(0, 200);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitmap_show);
        //init
        mBitmapSceneView = (BitmapSceneView) findViewById(R.id.bsv_show);
        btPrepare = (Button) findViewById(R.id.bt_prepare);
        btStart = (Button) findViewById(R.id.bt_start);
        btRestartr = (Button) findViewById(R.id.bt_restart);
        btPause = (Button)findViewById(R.id.bt_pause);
        btStop = (Button)findViewById(R.id.bt_stop);
        btPlayState = (Button)findViewById(R.id.bt_play_state);
        tvCuTime = (TextView) findViewById(R.id.tv_cu_time);
        tvDuTime = (TextView) findViewById(R.id.tv_du_time);
        mSeekBar = (SeekBar) findViewById(R.id.seek_bar);
        //setListener
        mBitmapSceneView.setOnCompletionListener(this);
        btPrepare.setOnClickListener(this);
        btStart.setOnClickListener(this);
        btRestartr.setOnClickListener(this);
        btPause.setOnClickListener(this);
        btStop.setOnClickListener(this);
        btPlayState.setOnClickListener(this);
        setSeekChangeListener();


        mBitmapSceneView.setCanvasHeight(1080);
        mBitmapSceneView.setCanvasWidth(600);

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onCompletion() {
        mBitmapSceneView.prepare(getBitmap(), 3000,1000, 0);
        tvDuTime.setText(mBitmapSceneView.getDurationTime()+"");
        mSeekBar.setMax(mBitmapSceneView.getDurationTime());
        mSeekBar.setProgress(0,true);
        mBitmapSceneView.start();
    }



    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_prepare:
                handlerPrepareClick();
                break;
            case R.id.bt_start:
                handlerStartClick();
                break;
            case R.id.bt_restart:
                handlerRestartClick();
                break;
            case R.id.bt_pause:
                handlerPauseClick();
                break;
            case R.id.bt_stop:
                handlerStopClick();
                break;
            case R.id.bt_play_state:
                handlerPlayStateClick();
                break;
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    private void handlerPrepareClick() {
        mBitmapSceneView.prepare(getBitmap(), 3000, 1000, 0);
        tvDuTime.setText(mBitmapSceneView.getDurationTime()+" ");
        mSeekBar.setMax(mBitmapSceneView.getDurationTime());
        mSeekBar.setProgress(0,true);

    }

    private void handlerStartClick() {
        mBitmapSceneView.start();
        mHandler.sendEmptyMessage(0);
    }

    private void handlerRestartClick() {
        mBitmapSceneView.restart();
        mHandler.sendEmptyMessage(0);
    }

    private void handlerPauseClick() {
        mBitmapSceneView.pause();
        mHandler.removeMessages(0);
    }

    private void handlerStopClick() {
        mBitmapSceneView.stop();
        mHandler.removeMessages(0);
    }

    private void handlerPlayStateClick() {
        int playState = mBitmapSceneView.getPlayState();
    }

    private void setSeekChangeListener() {
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                mBitmapSceneView.seekTo(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }


    private Bitmap getBitmap() {
        if(bitmapIndex == 5) {
            bitmapIndex = 1;
        }
        int bitmapId = getResources().getIdentifier("image"+bitmapIndex, "mipmap", getPackageName());
        BitmapFactory.Options mOptions = new BitmapFactory.Options();
        mOptions.inSampleSize = 5;
        Bitmap mBitmap = BitmapFactory.decodeResource(getResources(), bitmapId, mOptions);
        bitmapIndex++;
        return mBitmap;
    }
}
