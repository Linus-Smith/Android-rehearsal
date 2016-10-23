package com.chyang.gallery_dome.activity;

import android.animation.ValueAnimator;
import android.graphics.Color;
import android.graphics.PointF;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.RecyclerView;
import android.widget.ViewAnimator;

import com.chyang.gallery_dome.R;
import com.chyang.gallery_dome.adapter.GalleryAutoScrollerAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GalleryAutoScroller extends AppCompatActivity {

    private RecyclerView rvGallery;
    private LinearLayoutManager manager;


    private Handler mHandler = new Handler() {
        private  int  i =  3;
        @Override
        public void handleMessage(Message msg) {

            rvGallery.scrollBy(i, 0);
            //rvGallery.setTranslationX(i);
            mHandler.sendEmptyMessage(0);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_auto_scroller);
        rvGallery = (RecyclerView) findViewById(R.id.rv_gallery);
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
         mHandler.sendEmptyMessage(0 );
    }


    public int getRandomColor() {
        Random random = new Random();
        return Color.rgb(random.nextInt(256), random.nextInt(256),
                random.nextInt(256));
    }
}
