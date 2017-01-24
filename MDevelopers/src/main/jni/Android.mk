LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_LDLIBS := -llog

LOCAL_MODULE := jni_dome

LOCAL_SRC_FILES := jni_dome.c

include $(BUILD_SHARED_LIBRARY)