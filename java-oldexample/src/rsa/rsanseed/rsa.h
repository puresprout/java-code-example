#ifndef _RSA_H
#define _RSA_H

#ifdef  __cplusplus
extern "C" {
#endif

#if defined(_PROTOTYPES) || !defined(_NO_PROTO)

void do_crypt( char *s, char *d, int len, NUMBER *e );
int get_clear( char *p );
int get_enc( char *p );
int put_clear( char *p );
int put_enc( char *p );
int rsaEncode(NUMBER *n, NUMBER *e, char *pStr, int nSize, char *pdStr);
int rsaDecode(NUMBER *n, NUMBER *e, char *pStr, int nSize, char *pdStr);
int BinToHex(char *sour, int sour_len, char *dest);
int HexToBin(char *sour,int maxlen, char *dest);

#else

void do_crypt();
int get_clear();
int get_enc();
int put_clear();
int put_enc();
int rsaEncode();
int rsaDecode();
int BinToHex();
int HexToBin();

#endif

#ifdef  __cplusplus
}
#endif

#endif
