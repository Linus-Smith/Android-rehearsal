//
// Created by caihui yang on 2017/1/24.
//

#include "jni_dome.h"

jstring native_hello(JNIEnv* env, jobject thiz)
{
   return (*env)->NewStringUTF(env, "动态注册JNI");
}