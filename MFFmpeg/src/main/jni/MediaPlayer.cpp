//
// Created by linus.yang on 2017/10/2.
//

#include <jni.h>
#include <string>
#include <android/log.h>
extern "C" {
#include "libavformat/avformat.h"
#include "libswscale/swscale.h"
}


extern "C"
JNIEXPORT void  JNICALL
Java_com_mffmpeg_media_MediaPlayer_init(
        JNIEnv *env,
        jobject /* this */) {
    //av_register_all();
    __android_log_print(ANDROID_LOG_DEBUG, "caihui", "laileeeeeeê");
}
