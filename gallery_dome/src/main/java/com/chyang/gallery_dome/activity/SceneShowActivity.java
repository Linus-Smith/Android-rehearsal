package com.chyang.gallery_dome.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.chyang.gallery_dome.R;
import com.chyang.gallery_dome.ui.BitmapSceneView;
import com.chyang.gallery_dome.ui.SceneShowView;

import java.util.ArrayList;
import java.util.List;

public class SceneShowActivity extends AppCompatActivity implements BitmapSceneView.OnCompletionListener, View.OnClickListener{

    private static final long SLIDESHOW_DELAY = 3000; // 3 seconds

    private static int index = 1;
    int  id ;
    private BitmapSceneView mSceneShowView;
    private Button btStart;
    private boolean isPlay = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scene_switch);
        mSceneShowView = (BitmapSceneView) findViewById(R.id.sv);
        mSceneShowView.setOnCompletionListener(this);
        btStart = (Button) findViewById(R.id.bt_start);
        findViewById(R.id.bt_stop).setOnClickListener(this);
        btStart.setOnClickListener(this);

    }

    @Override
    public void onCompletion() {
        Log.d("chyang","onCompletion");
        if(isPlay) {
            startBitmap();
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.bt_start) {
            isPlay = true;
            startBitmap();
        } else {
            isPlay = false;
          //  mSceneShowView.stopShowScene();
        }
    }


    private void startBitmap() {
        if(index < 10) {
            id = getResources().getIdentifier("imag00" + index, "mipmap", getPackageName());
        } else {
            id = getResources().getIdentifier("imag0" + index, "mipmap", getPackageName());
        }

        Bitmap mBitmap = BitmapFactory.decodeResource(getResources(), id);
        if(mBitmap != null) {
            mSceneShowView.showNextScene(mBitmap, 3000,1000, 0);
        }
        index++;
        if(index == 19) {
            index = 1;
        }

    }
}
