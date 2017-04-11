/*
 * 프로그램 명   : _rsanseed.h
 * 작성일자      : 2003/05/16
 * 작성자        : 임용균
 * 프로그램 유형 : 시스템 프로세스
 * 프로그램 개요 : 암/복호화 처리
 * Copyright (c) 2002, (주) 아이비케이텍
 *
 * 암호화 방법
 * 1. 주어진 key 생성 실행파일을 이용 하여 public.key와 secret.key파일을 생성한다.
 * 2. 생성된 2개의 key파일중 public.key파일 들은 client 프로그램에서 사용 할 수 있도록
 *    ftp, floppy disk등을 이용하여 각각각의 client에 배포한다.
 * 3. 암호화를 하기위해서 public.key혹은 secret.key파일이 존재하는 path를 파라메터로하여
 *    RSAInit()을 호출 한다.
 * 4. 암호화 되기전의 데이터인 평문과 평문의 길이, 암호화된 데이터를 보관할 버퍼와 버퍼의
 *    길이를 파라메터로 하여 EncryptData()를 호출 한다.
 * 5. 암호화를 수행 하면 암호화된 데이터는 암호화 되기전의 데이터길이보다 크게 된다.
 *    암호화된 데이터의 길이 GetEncryptLength()을 사용 하여 한다.
 * 6. 암호화를 수행 하고 나면 암호화된 데이터의 길이를 돌려 준다.
 *
 * 복호화 방법
 * 1. 복호화를 하기위해서 public.key혹은 secret.key파일이 존재하는 path를 파라메터로하여
 *    RSAInit()을 호출 한다.
 * 2. 암호화된 데이터, 데이터의 길이, 복호화된 데이터를 보관할 버퍼와 버퍼의
 *    길이를 파라메터로 하여 DecryptData()를 호출 한다.
 * 3. 복호화를 수행 하고 나면 복호화된 데이터의 길이는 암호화된 데이터의 길이보다 작으므로
 *    암호화된 데이터의 길이와 같은 크기의 버퍼를 사용 하면 된다.
 * 4. 복호화를 수행 하고 나면 암호화 되기전 평문의 길이를 돌려 준다.
 */
/* 프로그램 -시작- */

#ifndef _RSANSEED_H
#define _RSANSEED_H

#ifdef  __cplusplus
extern "C" {
#endif

#define SESSIONKEY_SIZE		66

#if defined(_PROTOTYPES) || !defined(_NO_PROTO)

/*
 * 개요: key파일 초기화
 * IN  :
 *	char *pPath: rsa key path
 * OUT :
 *	0: 성공
 *  음수: 실패
 */
int RSAInit(char *pPath);
/*
 * 개요: 초기화된 key파일을 이용 하여 암호화 과정 수행
 * IN  :
 *	char *pSource:
 *	int nLength:
 *	char *pBuffer:
 *	int nBuffLength:
 * OUT :
 *	0 이상: 성공(암호화된 데이터의 길이)
 *  음수: 실패
 */
int EncryptData(char *pSource, int nLength, char *pBuffer, int nBuffLength);
/*
 * 개요: 초기화된 key파일을 이용 하여 복호화 과정 수행
 * IN  :
 *	char *pEncData:
 *	int nLength:
 *	char *pBuffer:
 *	int nBuffLength:
 * OUT :
 *	0이상 : 성공(복호화된 데이터의 길이)
 *  음수: 실패
 */
int DecryptData(char *pEncData, int nLength, char *pBuffer, int nBuffLength);

/*
 * 개요: 평문을 암호화 했을 때에 생성될 데이터의 길이를 돌려준다.
 * IN  :
 *	int nSourceLength:
 * OUT :
 *	int 암호화된후의 데이터 길이
 */
int GetEncryptLength(int nSourceLength);

#else

int RSAInit();
int EncryptData();
int DecryptData();
int GetEncryptLength(int nSourceLength);

#endif

#ifdef  __cplusplus
}
#endif

#endif	/* #ifndef _RSANSEED_H */

/* 프로그램	-끝- */
