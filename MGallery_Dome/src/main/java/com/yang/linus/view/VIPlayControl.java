package com.yang.linus.view;

import android.graphics.Bitmap;

/**
 * Created by linusyang on 16-12-4.
 */

public interface VIPlayControl {

    void prepare(Bitmap bitmap, int playDuration, int transitionDuration, int rotation);
    void start();
    void restart();
    void pause();
    void stop();
    void seekTo(int durationT);
    int getPlayState();
    int getProgressTime();
    int getDurationTime();
}
