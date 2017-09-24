package com.yang.mdevelopers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import com.yang.mdevelopers.activity.CanvasRotate;
import com.yang.mdevelopers.activity.ContentProviderActivity;
import com.yang.mdevelopers.activity.FragmentDomeActivity;
import com.yang.mdevelopers.activity.JniDomeActivity;
import com.yang.mdevelopers.activity.JobServiceDomeActivity;
import com.yang.mdevelopers.activity.PhotoPreviewActivity;
import com.yang.mdevelopers.activity.ScaleGestureActivity;
import com.yang.mdevelopers.activity.ThreadDomeOne;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.bt_canvas_rotate).setOnClickListener(this);
        findViewById(R.id.bt_provider).setOnClickListener(this);
        findViewById(R.id.bt_thread_one).setOnClickListener(this);
        findViewById(R.id.bt_jni_dome).setOnClickListener(this);
        findViewById(R.id.bt_scale_gesture).setOnClickListener(this);
        findViewById(R.id.bt_fragment).setOnClickListener(this);
        findViewById(R.id.bt_photo_preview).setOnClickListener(this);
        findViewById(R.id.bt_job_service).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent mIntent = new Intent();
        switch (v.getId()) {
            case R.id.bt_canvas_rotate:
                mIntent.setClass(this, CanvasRotate.class);
                break;
            case R.id.bt_provider:
                mIntent.setClass(this, ContentProviderActivity.class);
                break;
            case R.id.bt_thread_one:
                mIntent.setClass(this, ThreadDomeOne.class);
                break;
            case R.id.bt_jni_dome:
                mIntent.setClass(this, JniDomeActivity.class);
                break;
            case R.id.bt_scale_gesture:
                mIntent.setClass(this, ScaleGestureActivity.class);
                break;
            case R.id.bt_fragment:
                mIntent.setClass(this, FragmentDomeActivity.class);
                break;
            case R.id.bt_photo_preview:
                mIntent.setClass(this, PhotoPreviewActivity.class);
                break;
            case R.id.bt_job_service:
                mIntent.setClass(this, JobServiceDomeActivity.class);
                break;
        }
        startActivity(mIntent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        System.out.println("来了哈哈");
        return super.onCreateOptionsMenu(menu);
    }
}
