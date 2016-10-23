package com.chyang.gallery_dome.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.chyang.gallery_dome.R;
import com.chyang.gallery_dome.ui.SceneShowView;

import java.util.ArrayList;
import java.util.List;

public class SceneShowActivity extends AppCompatActivity {

    private static final long SLIDESHOW_DELAY = 3000; // 3 seconds

    private SceneShowView mSceneShowView;
    private static int index = 0;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            index++;
            if(index == 20) {
                index = 1;
            }

           int  id ;
            if(index < 10) {
                id = getResources().getIdentifier("imag00" + index, "mipmap", getPackageName());
            } else {
                id = getResources().getIdentifier("imag0" + index, "mipmap", getPackageName());
            }
            Bitmap mBitmap = BitmapFactory.decodeResource(getResources(), id);
            if(mBitmap != null) {
                mSceneShowView.next(mBitmap, 0);
            }
            mHandler.sendEmptyMessageDelayed(0, SLIDESHOW_DELAY);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scene_switch);
        mSceneShowView = (SceneShowView) findViewById(R.id.sv);
        mHandler.sendEmptyMessage(0);
    }
}
