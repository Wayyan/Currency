//
// Created by User on 1/11/2022.
//

#include <jni.h>
#include <string>

extern "C"
JNIEXPORT jstring JNICALL
Java_com_wayyan_currency_network_AuthModel_accessKey(
        JNIEnv *env,
        jobject,
        jstring build_type) {
    const char *buildTypeChar = env->GetStringUTFChars(build_type, nullptr);
    std::string buildTypeStr = std::string(buildTypeChar);
    std::string accessKey;
    if (buildTypeStr == "release") {
        accessKey = "25db38f8069057d3622c79baeae7c477";
    } else {
        accessKey = "25db38f8069057d3622c79baeae7c477";
    }
    env->ReleaseStringUTFChars(build_type, buildTypeChar);
    return env->NewStringUTF(accessKey.c_str());
}