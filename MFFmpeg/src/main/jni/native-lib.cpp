#include <jni.h>
#include <string>
#include <android/log.h>
#include <libswscale/swscale.h>
extern "C" {
#include "libavformat/avformat.h"
}

extern "C"
JNIEXPORT jstring JNICALL
Java_com_mffmpeg_MainActivity_stringFromJNI (
        JNIEnv *env,
        jobject /* this */) {
    //av_register_all();
    std::string hello = "Hello from C++";
    AVStream * m;
    return env->NewStringUTF(hello.c_str());
}


char * Jstring2CStr( JNIEnv * env, jstring jstr )
{
    char * rtn = NULL;
    jclass clsstring = env->FindClass("java/lang/String");
    jstring strencode = env->NewStringUTF("GB2312");
    jmethodID mid = env->GetMethodID(clsstring, "getBytes", "(Ljava/lang/String;)[B");
    jbyteArray barr= (jbyteArray)env->CallObjectMethod(jstr,mid,strencode);
    jsize alen = env->GetArrayLength(barr);
    jbyte * ba = env->GetByteArrayElements(barr,JNI_FALSE);
    if(alen > 0)
    {
        rtn = (char*)malloc(alen+1); //new char[alen+1];
        memcpy(rtn,ba,alen);
        rtn[alen]=0;
    }
    env->ReleaseByteArrayElements(barr,ba,0);

    return rtn;
}

static double r2d(AVRational r) {
    return r.num == 0 || r.den == 0 ? 0 : (double)r.num / (double)r.den;
}

extern "C"
JNIEXPORT
void JNICALL Java_com_mffmpeg_MainActivity_getVideoInfo(JNIEnv * env, jobject  obj, jstring path)
{
    av_register_all();

    AVFormatContext *ic = NULL;
    char * cpath = Jstring2CStr(env, path);
    int re = avformat_open_input(&ic, cpath, NULL, NULL);
    if(re != 0) {
        char buf[1024] = {0};
        av_strerror(re, buf, sizeof(buf));
        __android_log_print(ANDROID_LOG_DEBUG, "linus", "avformat_open_input  error: %s", buf);
        avformat_close_input(&ic);
        return;
    }

    int videoStream = 0;
    AVCodecContext * videoContext = NULL;
    for(int i = 0; i < ic->nb_streams; i++) {
        AVCodecContext * tempCodecCo = ic->streams[i]->codec;
        if(tempCodecCo->codec_type == AVMEDIA_TYPE_VIDEO) {
            videoContext = tempCodecCo;
            videoStream = i;

            AVCodec * code = avcodec_find_decoder(videoContext->codec_id);
            if(!code) {
                __android_log_print(ANDROID_LOG_DEBUG, "linus", "codec open error");
                return;
            }

            int err = avcodec_open2(videoContext, code, NULL);
            if(err != 0) {
                char buff[1024] = {0};
                av_strerror(err, buff, sizeof(buff));
                return;
            }
            __android_log_print(ANDROID_LOG_DEBUG, "linus", "Open codec success");
        }
    }

    AVFrame * yuv = av_frame_alloc();
    for(;;) {
        AVPacket pkt;
        re = av_read_frame(ic, &pkt);
        if(re != 0) break;
        if(pkt.stream_index != videoStream) {
            av_packet_unref(&pkt);
            continue;
        }
        int pts = pkt.pts * r2d(ic->streams[pkt.stream_index]->time_base) * 1000;
        int re = avcodec_send_packet(videoContext, &pkt);
        if(re != 0) {
            av_packet_unref(&pkt);
            continue;
        }
        re = avcodec_receive_frame(videoContext, yuv);
        if(re != 0) {
            av_packet_unref(&pkt);
            continue;
        }


        __android_log_print(ANDROID_LOG_DEBUG, "linus", "[D]  pts %d", pts);
        av_packet_unref(&pkt);

    }

}

