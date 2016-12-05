package com.yang.linus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.yang.linus.activity.BitmapShowActivity;
import com.yang.linus.android_rehearsal.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.bt_anim).setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        Intent mIntent = new Intent();
        switch (view.getId()) {
            case R.id.bt_anim:
                mIntent.setClass(this, BitmapShowActivity.class);
                break;
        }

        startActivity(mIntent);
    }
}
