#include <jni.h>
#include <string>
/*
*此方法弄出来so有问题，有空请教大神。搞不定
*/

extern "C"
JNIEXPORT jstring JNICALL
Java_com_example_jni_jniTest_jniTestClass_stringFromJNI(
        JNIEnv* env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}