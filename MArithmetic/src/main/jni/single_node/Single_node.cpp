//
// Created by Linus on 2017/3/30.
//
#include "Single_node.h"



CList::CList():size(0){
}

void CList::Insert(int i, const int e) {
    if(head == NULL && i == 0) {
        head = new CNode();
        head->data = e;
        size++;
    } else {
        if(checkBorder(i)) {
            CNode *q;
            for(int j = 0; j <= i; j++ ) {
                __android_log_print(ANDROID_LOG_DEBUG, "linus", "到了循环里面:%d", j);
                if(j == 0)
                    q = head->next;
                else
                    q = q->next;
                if(j == i) {
                    if(q->next == NULL) {
                        __android_log_print(ANDROID_LOG_DEBUG, "linus", "是空的");
                    }
                }

            }
        }
    }
}

bool CList::checkBorder(int index) {
    if (index < 0 || index > size) {
        __android_log_print(ANDROID_LOG_DEBUG, "linus", "位置错误：index:%d   size: %d", index, size);
        return false;
    } else {
        return true;
    }
}


CList * mList;

void single_node_init(JNIEnv * env, jobject  thzz){
    mList = new CList();
}

void l_Insert(JNIEnv * env, jobject thzz, int i, const int e){
    mList->Insert(i, e);
}