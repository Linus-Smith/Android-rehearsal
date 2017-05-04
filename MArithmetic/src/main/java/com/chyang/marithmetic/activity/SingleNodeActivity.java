package com.chyang.marithmetic.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.chyang.marithmetic.R;

import java.util.LinkedList;
import java.util.Random;

public class SingleNodeActivity extends AppCompatActivity implements View.OnClickListener {

    static {
        System.loadLibrary("native-lib");
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_node);
        findViewById(R.id.bt_add).setOnClickListener(this);
        findViewById(R.id.bt_print).setOnClickListener(this);
        single_node_init();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_add:
                l_Insert(0, 3);
                break;
            case R.id.bt_print:
                break;
        }
    }

    public native String getString();

    public native void single_node_init();

    public native void l_Insert(int i, int e);

}
