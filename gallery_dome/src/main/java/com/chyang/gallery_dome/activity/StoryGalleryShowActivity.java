package com.chyang.gallery_dome.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.chyang.gallery_dome.R;
import com.chyang.gallery_dome.ui.StoryGalleryShowView;

public class StoryGalleryShowActivity extends AppCompatActivity  implements MediaPlayer.OnCompletionListener {

    private static final long SLIDESHOW_DELAY = 3000; // 3 seconds
    private StoryGalleryShowView sgsGalleryShow;

    private static int index = 0;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            index++;
            if(index == 20) {
                index = 1;
            }
            int  id;
            if(index < 10) {
                id = getResources().getIdentifier("imag00" + index, "mipmap", getPackageName());
            } else {
                id = getResources().getIdentifier("imag0" + index, "mipmap", getPackageName());
            }
            Bitmap mBitmap = BitmapFactory.decodeResource(getResources(), id);
            if(mBitmap != null) {
                sgsGalleryShow.starShowBitmap(mBitmap);
            }
            mHandler.sendEmptyMessageDelayed(0, SLIDESHOW_DELAY);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_gallery_show);
        sgsGalleryShow = (StoryGalleryShowView) findViewById(R.id.sgs_story_show);
        Uri rawUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.sintel);
        sgsGalleryShow.setVideoURI(rawUri);
        sgsGalleryShow.start();
        sgsGalleryShow.setOnCompletionListener(StoryGalleryShowActivity.this);
        sgsGalleryShow.requestFocus();
        mHandler.sendEmptyMessage(0);

    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        sgsGalleryShow.stopPlayback();
        index = 1;
        mHandler.sendEmptyMessage(0);
    }
}
