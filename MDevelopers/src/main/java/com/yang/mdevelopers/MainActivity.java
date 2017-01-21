package com.yang.mdevelopers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.yang.mdevelopers.activity.CanvasRotate;
import com.yang.mdevelopers.activity.ContentProviderActivity;
import com.yang.mdevelopers.activity.ThreadDomeOne;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.bt_canvas_rotate).setOnClickListener(this);
        findViewById(R.id.bt_provider).setOnClickListener(this);
        findViewById(R.id.bt_thread_one).setOnClickListener(this);
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
        }
        startActivity(mIntent);
    }
}
