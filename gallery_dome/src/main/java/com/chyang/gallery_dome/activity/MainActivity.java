package com.chyang.gallery_dome.activity;

import android.content.Intent;
import android.graphics.Canvas;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ScrollView;

import com.chyang.gallery_dome.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.bt_gallery_scroller).setOnClickListener(this);
        findViewById(R.id.bt_scene_switch).setOnClickListener(this);
        findViewById(R.id.bt_sufaceview_dome1).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        Intent mIntent = new Intent();
        if(id == R.id.bt_gallery_scroller) {
            mIntent.setClass(this, GalleryAutoScroller.class);
        } else if(id == R.id.bt_scene_switch) {
            mIntent.setClass(this, SceneShowActivity.class);
        } else if(id == R.id.bt_sufaceview_dome1) {
            mIntent.setClass(this, SurfaceViewDome1.class);
        }
        startActivity(mIntent);
    }
}
