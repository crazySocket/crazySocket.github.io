#include "Test_NativeCounter.h"

int i = 0;

extern int increase(int *);

JNIEXPORT jint JNICALL Java_Test_00024NativeCounter_get
(JNIEnv *env, jobject this) {
  return i;
}

JNIEXPORT jint JNICALL Java_Test_00024NativeCounter_increment
(JNIEnv *env, jobject this) {
  return increase(&i);
  //return __sync_add_and_fetch(&i, 1);
}
