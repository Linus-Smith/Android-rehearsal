package com.mffmpeg;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mffmpeg.Activity.VideoPlayerActivity;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    static {
        System.loadLibrary("native-lib");
        System.loadLibrary("avcodec");
        System.loadLibrary("avformat");
        System.loadLibrary("avutil");
        System.loadLibrary("swresample");
        System.loadLibrary("swscale");

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//      System.out.println(stringFromJNI());
//        File mFile = new File("/storage/emulated/0/DCIM/sintel.mp4");
//        getVideoInfo(mFile.toString());

        Intent intent = new Intent();
        intent.setClass(this, VideoPlayerActivity.class);
        startActivity(intent);
    }

    public native String stringFromJNI();

    public native void getVideoInfo(String path);
}
