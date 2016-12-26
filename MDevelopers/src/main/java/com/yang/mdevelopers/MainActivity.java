package com.yang.mdevelopers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.yang.mdevelopers.activity.CanvasRotate;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.bt_canvas_rotate).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent mIntent = new Intent();
        switch (v.getId()) {
            case R.id.bt_canvas_rotate:
                mIntent.setClass(this, CanvasRotate.class);
                break;
        }
        startActivity(mIntent);
    }
}
