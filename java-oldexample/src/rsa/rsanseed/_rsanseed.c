#include <stdio.h>
#include <fcntl.h>
#include <string.h>
#ifdef WIN32
#include <io.h>
#else
#include <unistd.h>
#endif

#include "conf.h"
#include "_seed.h"
#include "nio.h"
#include "prim.h"
#include "rsa.h"
#include "_rsanseed.h"

#define RSA_KEY_SIZE	16

typedef struct {
	NUMBER n1;
	NUMBER n2;
} RsaKey;

int bInit = 0;
static RsaKey _gRsaKey;

int RSAInit(char *pPath)
{
	int fd;
	char szBuffer[256];
	int n;
	char *p;

	fd = open(pPath, O_RDONLY);
	if(fd < 0) {
		return -1;
	}

	memset(szBuffer, 0, sizeof(szBuffer));
	n = read(fd, szBuffer, sizeof(szBuffer));
	if(n <= 0) {
		close(fd);
		return -1;
	}
	close(fd);

	p = strchr((char *)szBuffer, 'G');
	if(!p) {
		close(fd);
		return -1;
	}
	*p = 0;

	memset(&_gRsaKey, 0, sizeof(_gRsaKey));
	if(num_sget( &_gRsaKey.n1, szBuffer) == 01) {
		return -1;
	}
	if(num_sget( &_gRsaKey.n2, p+1) == 01) {
		return -1;
	}
	return 0;
}

int EncryptData(char *pSource, int nLength, char *pBuffer, int nBuffLength)
{
	int nRet;
	char SeedKeyPrint[STRLEN + 1];
	char EncSeedKeyPrint[STRLEN + 1];
	char SeedKeyPrintHex[STRLEN + 1];
	NUMBER SeedKey;

	/* session key 생성 */
	genprim(RSA_KEY_SIZE, 10, &SeedKey);
	if ( num_sput( &SeedKey, SeedKeyPrint, sizeof(SeedKeyPrint) ) == -1 )
		return -1;
	SeedKeyPrint[RSA_KEY_SIZE] = 0;

	nRet = strlen(SeedKeyPrint);

	/* 생성된 Session Key를 주어진 key file을 이용 하여 암호화 안다. */
	memset(EncSeedKeyPrint, 0, sizeof(EncSeedKeyPrint));
	nRet = rsaEncode(&_gRsaKey.n1, &_gRsaKey.n2, SeedKeyPrint,
				strlen(SeedKeyPrint), EncSeedKeyPrint);

	memset(SeedKeyPrintHex, 0, sizeof(SeedKeyPrintHex));
	BinToHex(EncSeedKeyPrint, SESSIONKEY_SIZE/2, SeedKeyPrintHex);
	nRet = strlen(SeedKeyPrintHex);
	memcpy(pBuffer, SeedKeyPrintHex, strlen(SeedKeyPrintHex));

	/* 생성된 Session Key를 암호화 하는데 사용 하게 하기 위해 설정 한다. */
	SetKey(SeedKeyPrint, strlen(SeedKeyPrint));
	/* 설정된 Session Key를 이용 하여 암호화 한다. */
	nRet = SeedEnc(pBuffer+nRet, pSource, nLength);

	return nRet+SESSIONKEY_SIZE;
}

int DecryptData(char *pEncData, int nLength, char *pBuffer, int nBuffLength)
{
	int nRet;
	char SeedKeyPrint[ STRLEN + 1 ];
	char EncSeedKeyPrint[ STRLEN + 1 ];
	char SeedKeyPrintHex[ STRLEN + 1 ];

	memset(SeedKeyPrintHex, 0, sizeof(SeedKeyPrintHex));
	memcpy(SeedKeyPrintHex, pEncData, SESSIONKEY_SIZE);

	memset(EncSeedKeyPrint, 0, sizeof(EncSeedKeyPrint));
	HexToBin(SeedKeyPrintHex, SESSIONKEY_SIZE, EncSeedKeyPrint);

	nRet = rsaDecode(&_gRsaKey.n1, &_gRsaKey.n2, EncSeedKeyPrint,
				strlen(EncSeedKeyPrint), SeedKeyPrint);

	SetKey(SeedKeyPrint, strlen(SeedKeyPrint));
	nRet = SeedDec(pBuffer, pEncData+SESSIONKEY_SIZE, nBuffLength-SESSIONKEY_SIZE);

	return nRet;
}

int GetEncryptLength(int nSourceLength)
{
	int nEncLength = 0;
	nEncLength = nSourceLength / 16;	/* 8 Byte 씩 암호화 하기 때문에 8로 나눈다. */
	nEncLength += 1;					/* 8 Byte로 나눈 나머지가 있을 경우를 대비 하여 1을 더한다. */
	nEncLength *= 16;					/* 8 Byte가 암호화 되면 2배가 되기 때문에 16을 곱한다. */
	nEncLength += 4;					/* 원 자료의 길이를 보관 하기 위해서 4 Byte를 사용 한다. */
	nEncLength *= 2;					/* 암호화 되고 나면 Binary 데이터가 생성 되기 때문에 Hexa코드로 변환 하기 위해서 2르 곱한다. */
	nEncLength += SESSIONKEY_SIZE;		/* 암호화에 사용된 Session를 보관하기 위해서 SESSIONKEY_SIZE길이를 더한다. */

	return nEncLength;
}

