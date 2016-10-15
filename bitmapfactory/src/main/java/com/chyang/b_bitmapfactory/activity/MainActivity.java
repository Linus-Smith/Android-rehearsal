package com.chyang.b_bitmapfactory.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.chyang.b_bitmapfactory.R;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener{



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.bt_big_imag).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int mId = v.getId();
        Intent mIntent = new Intent();
        if(R.id.bt_big_imag == mId) {
            mIntent.setClass(this, BigBitmapShow.class);
        }

        if(mIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(mIntent);
        }

    }
}
