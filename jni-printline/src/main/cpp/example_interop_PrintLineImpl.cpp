/**
 * Copyright (c) 2022 by Kristoffer Paulsson <kristoffer.paulsson@talenten.se>.
 *
 * This software is available under the terms of the MIT license. Parts are licensed
 * under different terms if stated. The legal terms are attached to the LICENSE file
 * and are made available on:
 *
 *      https://opensource.org/licenses/MIT
 *
 * SPDX-License-Identifier: MIT
 *
 * Contributors:
 *      Kristoffer Paulsson - initial implementation
 */
#include <jni.h>
#include "printline.h"

#ifndef _Included_example_interop_PrintLine
#define _Included_example_interop_PrintLine
#ifdef __cplusplus
extern "C" {
#endif

static const char *JNIT_CLASS = "example/interop/PrintLine";

/*
 * Class:     example_interop_PrintLine
 * Method:    posix_print
 * Signature: (Ljava/lang/String;)Z
 */
static jboolean posix_print(JNIEnv * env, jclass thisClass, jstring message){
    const char *buf = (*env)->GetStringUTFChars(env, message, 0);
    print_line(buf);
    (*env)->ReleaseStringUTFChars(env, message, buf);
    return JNI_TRUE;
}

static JNINativeMethod funcs[] = {
	{ "posix_print", "(Ljava/lang/String;)Z", (void *)&posix_print }
};

#define CURRENT_JNI JNI_VERSION_1_6

JNIEXPORT jint JNICALL JNI_OnLoad(JavaVM* vm, void* reserved)
{
	JNIEnv *env;
	jclass  cls;
	jint    res;

	(void)reserved;

	if ((*vm)->GetEnv(vm, (void **)&env, CURRENT_JNI) != JNI_OK)
		return -1;

	cls = (*env)->FindClass(env, JNIT_CLASS);
	if (cls == NULL)
		return -1;

	res = (*env)->RegisterNatives(env, cls, funcs, sizeof(funcs)/sizeof(*funcs));
	if (res != 0)
		return -1;

	return CURRENT_JNI;
}

JNIEXPORT void JNICALL JNI_OnUnload(JavaVM *vm, void *reserved)
{
	JNIEnv *env;
	jclass  cls;

	(void)reserved;

	if ((*vm)->GetEnv(vm, (void **)&env, CURRENT_JNI) != JNI_OK)
		return;

	cls = (*env)->FindClass(env, JNIT_CLASS);
	if (cls == NULL)
		return;

	(*env)->UnregisterNatives(env, cls);
}


#ifdef __cplusplus
}
#endif
#endif