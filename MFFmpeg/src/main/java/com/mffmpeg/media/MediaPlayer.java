package com.mffmpeg.media;

/**
 * Created by linus on 2017/10/2.
 */

public class MediaPlayer {

    static {
        System.loadLibrary("native-lib");
        System.loadLibrary("avcodec");
        System.loadLibrary("avformat");
        System.loadLibrary("avutil");
        System.loadLibrary("swresample");
        System.loadLibrary("swscale");

    }

    public MediaPlayer() {
        init();

    }

    private native void init();
}
