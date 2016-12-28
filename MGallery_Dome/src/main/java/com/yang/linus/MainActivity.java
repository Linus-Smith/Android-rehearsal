package com.yang.linus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.yang.linus.activity.BitmapShowActivity;
import com.yang.linus.R;
import com.yang.linus.activity.CatViewApiActivity;
import com.yang.linus.activity.VideoPlayActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.bt_anim).setOnClickListener(this);
        findViewById(R.id.bt_view_api).setOnClickListener(this);
        findViewById(R.id.bt_video_play).setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        Intent mIntent = new Intent();
        switch (view.getId()) {
            case R.id.bt_anim:
                mIntent.setClass(this, BitmapShowActivity.class);
                break;
            case R.id.bt_view_api:
                mIntent.setClass(this, CatViewApiActivity.class);
                break;
            case R.id.bt_video_play:
                mIntent.setClass(this, VideoPlayActivity.class);
                break;
        }

        startActivity(mIntent);
    }
}
