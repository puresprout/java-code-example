#include <stdio.h>
#include "Encrypt.h"
#include "Decrypt.h"

JNIEXPORT jint JNICALL Java_Encrypt_RSAInit(JNIEnv *env, jobject obj, jstring str)
{
	int debug = 1;
	
	const char *convertedStr;
	int rtnVal;

	convertedStr = (*env)->GetStringUTFChars(env, str, 0);

	if (debug)
		printf("%s\n", convertedStr);
	rtnVal = RSAInit(convertedStr);

	(*env)->ReleaseStringUTFChars(env, str, convertedStr);

	return rtnVal;
}

JNIEXPORT jint JNICALL Java_Decrypt_RSAInit(JNIEnv *env, jobject obj, jstring str)
{
	int debug = 1;
	
	const char *convertedStr;
	int rtnVal;

	convertedStr = (*env)->GetStringUTFChars(env, str, 0);

	if (debug)
		printf("%s\n", convertedStr);
	rtnVal = RSAInit(convertedStr);

	(*env)->ReleaseStringUTFChars(env, str, convertedStr);

	return rtnVal;
}

JNIEXPORT jint JNICALL Java_Encrypt_EncryptData(JNIEnv *env, jobject obj, jbyteArray src, jint srcLength, jbyteArray buffer, jint bufferLength)
{
	int rtnVal;
	jbyte *srcBody = (*env)->GetByteArrayElements(env, src, 0);
	jbyte *bufferBody = (*env)->GetByteArrayElements(env, buffer, 0);

	rtnVal = EncryptData(srcBody, srcLength, bufferBody, bufferLength);

	(*env)->ReleaseByteArrayElements(env, src, srcBody, 0);
	(*env)->ReleaseByteArrayElements(env, buffer, bufferBody, 0);

	return rtnVal;
}

JNIEXPORT jint JNICALL Java_Decrypt_DecryptData(JNIEnv *env, jobject obj, jbyteArray src, jint srcLength, jbyteArray buffer, jint bufferLength)
{
	int rtnVal;
	jbyte *srcBody = (*env)->GetByteArrayElements(env, src, 0);
	jbyte *bufferBody = (*env)->GetByteArrayElements(env, buffer, 0);

	rtnVal = DecryptData(srcBody, srcLength, bufferBody, bufferLength);

	(*env)->ReleaseByteArrayElements(env, src, srcBody, 0);
	(*env)->ReleaseByteArrayElements(env, buffer, bufferBody, 0);

	return rtnVal;
}

