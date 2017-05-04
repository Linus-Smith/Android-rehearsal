//
// Created by Linus on 2017/3/30.
//

#include <jni.h>
#include <string>
#include <assert.h>
#include <stdio.h>
#include <Android/log.h>

#ifndef ANDROID_REHEARSAL_NODE_H
#define ANDROID_REHEARSAL_NODE_H

#ifdef __cplusplus
extern "C" {
#endif

static const char *classPathName = "com/chyang/marithmetic/activity/SingleNodeActivity";



jstring native_hello(JNIEnv *env, jobject thiz) {
    return env->NewStringUTF("ddd");
}


void single_node_init(JNIEnv * env, jobject  thzz);
void l_Insert(JNIEnv * env, jobject thzz,  int i, const int e );

class CNode {
public:
    int data;
    CNode *next;

    CNode() {
        next = NULL;
    }
};


class CList {
private:
    CNode *head;   //头节点
    CNode *end;   //尾节点

    bool checkBorder(int index); //检查边界
public:
    int size ;

    CList();

    void Display() const; //显示链表
    ~CList();       //销毁链表
    int getLen() const;   //获取链表长度
    bool isEmpty() const; //判断链表是否为空
    bool Find(const int e) const; //在链不表中查找某个值
    CNode *GetNode();  //返回第1个节点
    void Insert(int i, const int e);    //在第i个位置插入元素
    void Delete(const int e);    //删除一个元素
    void Reverse();            //链表逆置

};


static JNINativeMethod gMethods[] = {
        {"getString", "()Ljava/lang/String;", (void *) native_hello},//绑定
        {"single_node_init","()V", (void *)single_node_init},
        {"l_Insert","(II)V", (void *)l_Insert}
};


/*
* 为某一个类注册本地方法
*/
static int registerNativeMethods(JNIEnv *env, const char *className, JNINativeMethod *gMethods,
                                 int numMethods) {
    jclass clazz;

    clazz = env->FindClass(className);
    if (clazz == NULL) {
        return JNI_FALSE;
    }
    if (env->RegisterNatives(clazz, gMethods, numMethods) < 0) {
        return JNI_FALSE;
    }

    return JNI_TRUE;
}


/*
* 为所有类注册本地方法
*/
static int registerNatives(JNIEnv *env) {

    return registerNativeMethods(env, classPathName, gMethods,
                                 sizeof(gMethods) / sizeof(gMethods[0]));
}

/*
* System.loadLibrary("lib")时调用
* 如果成功返回JNI版本, 失败返回-1
*/
JNIEXPORT jint JNICALL JNI_OnLoad(JavaVM *vm, void *reserved) {
    JNIEnv *env = NULL;
    jint result = -1;

    if (vm->GetEnv((void **) &env, JNI_VERSION_1_6) != JNI_OK) {
        return -1;
    }
    assert(env != NULL);

    if (!registerNatives(env)) {//注册
        return -1;
    }
    //成功
    result = JNI_VERSION_1_6;

    return result;
}

#ifdef __cplusplus
}
#endif


#endif //ANDROID_REHEARSAL_NODE_H
