#include "JNITest.h"
#include <stdio.h>

JNIEXPORT jlong JNICALL Java_JNITest_ToDouble(JNIEnv * env, jobject jobj, jint val)
{
	long result;

	result = val * val;

	return result;
}

JNIEXPORT jstring JNICALL Java_JNITest_Concat(JNIEnv * env, jobject jobj, jobjectArray array)
{
	char buffer[1024];
	
	int arraySize = (*env)->GetArrayLength(env, array);
	int strSize;
	int totalSize = 0;
	int cnt;
	jstring str;
	char *convertedStr;

	for (cnt = 0; cnt < arraySize; cnt++)
	{
		str = (jstring) (*env)->GetObjectArrayElement(env, array, cnt);
		strSize = (*env)->GetStringLength(env, str);
		convertedStr = (*env)->GetStringUTFChars(env, str, 0);
		strcpy(&buffer[totalSize], convertedStr);
		totalSize = totalSize + strSize;	
	}
	return (*env)->NewStringUTF(env, buffer);


}

