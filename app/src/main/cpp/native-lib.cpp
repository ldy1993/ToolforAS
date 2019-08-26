#include <jni.h>
#include <string>
#include <android/log.h>

#define  LOG     "ldy-jni-log"// 这个是自定义的LOG的标识  
#define  LOGD(...)    __android_log_print(ANDROID_LOG_DEBUG,LOG,__VA_ARGS__)// 定义LOGD类型  
#define LOGI(...)  __android_log_print(ANDROID_LOG_INFO,LOG,__VA_ARGS__) // 定义LOGI类型  
#define LOGW(...)  __android_log_print(ANDROID_LOG_WARN,LOG,__VA_ARGS__) // 定义LOGW类型  
#define  LOGE(...)    __android_log_print(ANDROID_LOG_ERROR,LOG,__VA_ARGS__) // 定义LOGE类型  
#define LOGF(...)  __android_log_print(ANDROID_LOG_FATAL,LOG,__VA_ARGS__) // 定义LOGF类型
extern "C"
JNIEXPORT jstring JNICALL
Java_com_example_function_jni_jniTest_jniTestClass_stringFromJNI(
        JNIEnv* env,
        jobject /* this */) {
    std::string hello = "Hello from 算法库";
    LOGD("hello");
    LOGE( "刘东阳！！！");
    return env->NewStringUTF(hello.c_str());
}
