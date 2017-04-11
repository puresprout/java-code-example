/*
 * 프로그램 명   : _seed.h
 * 작성일자      : 2002/06/29
 * 작성자        : 임용균
 * 프로그램 유형 : 시스템 프로세스 공통 class
 * 프로그램 개요 : 암호화
 * Copyright (C) 2002, (주) 아이비케이텍
 */
/* 프로그램 -시작- */

#ifndef _SEED_H
#define _SEED_H

#ifdef  __cplusplus
extern "C" {
#endif

#if defined(_PROTOTYPES) || !defined(_NO_PROTO)

int SeedEnc(char *dest, char *sour, int sour_len);
int SeedDec(char *dest, char *sour, int maxlen);
void SetKey(char *pKey, int nLength);

#else

int SeedEnc();
int SeedDec();
void SetKey();

#endif

#ifdef  __cplusplus
}
#endif

#endif
