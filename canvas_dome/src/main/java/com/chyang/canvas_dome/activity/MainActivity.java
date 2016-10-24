package com.chyang.canvas_dome.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.chyang.canvas_dome.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.bt_draw_all1).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        Intent mIntent = new Intent();
        if(id == R.id.bt_draw_all1) {
            mIntent.setClass(this, DrawAllDomeActivity1.class);
        }
        startActivity(mIntent);
    }
}
