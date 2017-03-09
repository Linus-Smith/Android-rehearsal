package com.yang.mdevelopers.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.yang.mdevelopers.R;
import com.yang.mdevelopers.view.ImageShow;
import com.yang.mdevelopers.view.ZoomImageView;

public class ScaleGestureActivity extends AppCompatActivity {


    private ZoomImageView pvView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scale_gesture);
        pvView = (ZoomImageView) findViewById(R.id.pv_view);
        Bitmap mBitmap= BitmapFactory.decodeResource(getResources(), R.mipmap.timg);
        pvView.setImageBitmap(mBitmap);
    }
}
