package com.chyang.b_bitmapfactory.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Path;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.chyang.b_bitmapfactory.R;
import com.chyang.b_bitmapfactory.ui.BitmapCanvasView;

public class BitmapCanvasActivity extends AppCompatActivity implements View.OnClickListener{

    private BitmapCanvasView mBitmapCanvasView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitmap_canvas);
        mBitmapCanvasView = (BitmapCanvasView) findViewById(R.id.bcv);
        findViewById(R.id.bt_x).setOnClickListener(this);
        findViewById(R.id.bt_y).setOnClickListener(this);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ag);
        mBitmapCanvasView.setBitmap(bitmap);
        Path path = new Path();
    }

    @Override
    public void onClick(View v) {
        int mId = v.getId();
        if(mId == R.id.bt_x) {
            mBitmapCanvasView.setDrawX(100);
        } else if(mId == R.id.bt_y) {
            mBitmapCanvasView.setDrawY(100);
        }
    }
}
