package com.chyang.gallery_dome.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.VideoView;

import com.chyang.gallery_dome.R;

public class SurfaceViewDome1 extends AppCompatActivity {


    private SurfaceView sfView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surface_view_dome1);
        sfView = (SurfaceView) findViewById(R.id.sf);
        SurfaceHolder mSurfaceHolder = sfView.getHolder();
        VideoView mVideoView = new VideoView(this);
        SurfaceHolder mSurfaceHolderss = mVideoView.getHolder();

    }


    @Override
    protected void onResume() {
        super.onResume();
    }

}


