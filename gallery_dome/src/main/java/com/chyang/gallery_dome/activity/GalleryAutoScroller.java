package com.chyang.gallery_dome.activity;

import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.VideoView;

import com.chyang.gallery_dome.R;
import com.chyang.gallery_dome.adapter.GalleryAutoScrollerAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GalleryAutoScroller extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView rvGallery;
    private LinearLayoutManager manager;


    private Handler mHandler = new Handler() {
        private  int  i =  1;
        @Override
        public void handleMessage(Message msg) {

            rvGallery.scrollBy(i, 0);
            mHandler.sendEmptyMessage(0);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_gallery_auto_scroller);
        rvGallery = (RecyclerView) findViewById(R.id.rv_gallery);
        rvGallery.setVisibility(View.VISIBLE);
        findViewById(R.id.bu).setOnClickListener(this);
         manager =  new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvGallery.setLayoutManager(manager);
        List<Integer> mColors = new ArrayList<>();
        for(int i = 0; i < 100; i++) {
            mColors.add(getRandomColor());
        }

        GalleryAutoScrollerAdapter mGalleryAutoScrollerAdapter = new GalleryAutoScrollerAdapter(this);
        mGalleryAutoScrollerAdapter.setData(mColors);

        rvGallery.setAdapter(mGalleryAutoScrollerAdapter);
         mHandler.sendEmptyMessage(0);
    }


    public int getRandomColor() {
        Random random = new Random();
        return Color.rgb(random.nextInt(256), random.nextInt(256),
                random.nextInt(256));
    }

    @Override
    public void onClick(View v) {
        System.out.println(rvGallery.getScrollX()+"====onClick=---");

    }
}
