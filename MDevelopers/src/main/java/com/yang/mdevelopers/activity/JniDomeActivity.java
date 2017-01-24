package com.yang.mdevelopers.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.yang.mdevelopers.R;

public class JniDomeActivity extends AppCompatActivity {

    static {
        System.loadLibrary("jni_dome");
    }

    private TextView mTvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jni_dome);
        mTvTitle = (TextView) findViewById(R.id.tv_title);
        mTvTitle.setText(getString());
    }


    public native String getString();

}
