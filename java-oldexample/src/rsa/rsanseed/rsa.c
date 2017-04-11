/*******************************************************************************
*									       *
*	Copyright (c) Martin Nicolay,  22. Nov. 1988			       *
*									       *
*	Wenn diese (oder sinngemaess uebersetzte) Copyright-Angabe enthalten   *
*	bleibt, darf diese Source fuer jeden nichtkomerziellen Zweck weiter    *
*	verwendet werden.						       *
*									       *
*	martin@trillian.megalon.de					       *
*									       *
*******************************************************************************/
#include	<stdio.h>
#include	<string.h>
#include	<memory.h>

#include	"arith.h"
#include	"nio.h"
#include	"rsa.h"

#define	ENCODE	"rsaencode"
#define	DECODE	"rsadecode"

#if LOCAL
char *prog;
#endif

int	clear_siz;			/* clear-text blocksize		*/
int	enc_siz;			/* encoded blocksize		*/
					/* clear_siz < enc_siz		*/

void do_crypt( char *s, char *d, int len, NUMBER *e )
{
	static char hex[] = "0123456789ABCDEF";
	NUMBER n;
	char buf[ STRLEN + 1 ];
	char *ph;
	int i,c;

	int l = MAXLEN;
	l = STRLEN;
	ph = buf + STRLEN;
	ph[1] = '\0';

	for (i=len; i; i--) {
		c = *s++;
		*ph-- = hex[ (c >> 4) & 0xF ];
		*ph-- = hex[ c & 0xF ];
	}
	ph++;

	num_sget( &n, ph );

	m_exp( &n, e, &n );

	num_sput( &n, buf, STRLEN +1 );

	ph = buf + (i=strlen(buf)) -1;

	for (; len; len--) {
		if (i-- > 0) {
			c = (strchr( hex, *ph ) - hex) << 4;
			ph--;
		}
		else
			c=0;
		if (i-- > 0) {
			c |= strchr( hex, *ph ) - hex;
			ph--;
		}

		*d++ = c;
	}
}


int get_clear( char *p )
{
	int n;

	n = fread( p, 1, clear_siz, stdin );

	if (n <= 0)
		return(0);

	memset( p + n, 0, enc_siz - n );

	return(1);
}

int get_enc( char *p )
{
	int n;

	n = fread( p, 1, enc_siz, stdin );

	if (n != enc_siz)
		return(0);

	return(1);
}

int put_clear( char *p )
{
	int n;

	n = fwrite( p, 1, clear_siz, stdout );

	if (n != clear_siz)
		return(0);

	return(1);
}

int put_enc( char *p )
{
	int n;

	n = fwrite( p, 1, enc_siz, stdout );

	if (n != enc_siz)
		return(0);

	return(1);
}

#if LOCAL
main( argc, argv )
char **argv;
{
	char buf[ STRLEN*2 ];
	NUMBER n,e;
	FILE *keys;
	int (*inp)() = 0;
	int (*out)() = 0;

	if ( ! (prog=strrchr( argv[0], '/' )) )
		prog=argv[0];


	if ( ! strcmp( prog, DECODE ) ) {
		inp = get_enc;
		out = put_clear;
	}
	if ( ! strcmp( prog, ENCODE ) ) {
		inp = get_clear;
		out = put_enc;
	}

	if (! inp) {
		fprintf(stderr,"%s: don't know who i am (%s or %s)\n",prog,ENCODE,DECODE);
		exit(1);
	}

	if (argc != 2) {
		fprintf(stderr,"usage: %s keyfile\n",prog);
		exit(1);
	}
	if ( !( keys= fopen(argv[1],"r")) ) {
		perror(argv[1]);
		exit(1);
	}

	num_fget( &n, keys ); getc(keys);
	num_fget( &e, keys );
	if (a_cmp(&n,&e) <= 0 || e.n_len == 0 || getc(keys) != EOF) {
		fprintf(stderr,"%s: corrupt keyfile\n",prog);
		fprintf(stderr,"keyfile format:\n");
		fprintf(stderr,"\t<n in hex>\n");
		fprintf(stderr,"\t<delimiter> (1 char)\n");
		fprintf(stderr,"\t<e/d in hex>\n");
		fprintf(stderr,"white spaces are ignored\n");
		exit(1);
	}

	enc_siz = ( n_bitlen( &n ) + 7 ) / 8;
	clear_siz = enc_siz -1;

	m_init( &n, NUM0P );

	while ( (*inp)( buf ) ) {
		do_crypt( buf, buf, enc_siz, &e );
		if (! (*out)( buf ) ) {
			perror("output");
			exit(1);
		}
	}

	exit(0);
}
#else
int rsaEncode(NUMBER *n, NUMBER *e, char *pStr, int nSize, char *pdStr)
{
	int i = 0;
	char sbuf[ STRLEN*2 ];
	char dbuf[ STRLEN*2 ];
	int nEncLen = 0;

	if (a_cmp(n, e) <= 0 || e->n_len == 0) {
		return -1;
	}

	enc_siz = ( n_bitlen( n ) + 7 ) / 8;
	clear_siz = enc_siz -1;

	m_init( n, NUM0P );

	for(i = 0; i < nSize; ) {
		memset(sbuf, 0, sizeof(sbuf));
		memset(dbuf, 0, sizeof(dbuf));
		memcpy(sbuf, pStr+i, clear_siz);
		do_crypt( sbuf, dbuf, enc_siz, e );
		memcpy(pdStr+nEncLen, dbuf, enc_siz);
		nEncLen += enc_siz;
		i += clear_siz;
	}

	return nEncLen;
}

int rsaDecode(NUMBER *n, NUMBER *e, char *pStr, int nSize, char *pdStr)
{
	int i = 0;
	char sbuf[ STRLEN*2 ];
	char dbuf[ STRLEN*2 ];
	int nDecLen = 0;

	if (a_cmp(n, e) <= 0 || e->n_len == 0) {
/*
		fprintf(stderr,"%s: corrupt keyfile\n",prog);
		fprintf(stderr,"keyfile format:\n");
		fprintf(stderr,"\t<n in hex>\n");
		fprintf(stderr,"\t<delimiter> (1 char)\n");
		fprintf(stderr,"\t<e/d in hex>\n");
		fprintf(stderr,"white spaces are ignored\n");
*/
		return 0;
	}

	enc_siz = ( n_bitlen( n ) + 7 ) / 8;
	clear_siz = enc_siz -1;

	m_init( n, NUM0P );

	for(i = 0; i < nSize; ) {
		memset(sbuf, 0, sizeof(sbuf));
		memset(dbuf, 0, sizeof(dbuf));
		memcpy(sbuf, pStr+i, enc_siz);
		do_crypt( sbuf, dbuf, enc_siz, e );
		memcpy(pdStr+nDecLen, dbuf, clear_siz);
		nDecLen += clear_siz;
		i += enc_siz;
	}
	return nDecLen;
}

int BinToHex(char *sour, int sour_len, char *dest)
{
	int i;
	unsigned char h4;
	unsigned char l4;

	for(i = 0; i < sour_len; i++) {		/* 예) 0x12 -> '1' + '2'로 */
		h4 = (unsigned char)((unsigned char)sour[i] / 16);
		l4 = (unsigned char)((unsigned char)sour[i] % 16);
		if(h4 <= 9) *dest++ = h4 + '0';
		else		*dest++ = h4 - 10 + 'A';
		if(l4 <= 9) *dest++ = l4 + '0';
		else		*dest++ = l4 - 10 + 'A';
	}
	return 0;
}

int HexToBin(char *sour,int maxlen, char *dest)
{
	int i;
	unsigned char h4;
	unsigned char l4;
	unsigned char *pt = (unsigned char *)dest;

	for(i = 0; i < maxlen; i += 2) { /* 예)) '1' + '2' 를 0x12로 */
		h4 = (unsigned char) sour[i];
		l4 = (unsigned char) sour[i+1];
		if(h4 <= '9') h4 = h4 - '0';
		else		  h4 = h4 - 'A' + 10;
		if(l4 <= '9') l4 = l4 - '0';
		else		  l4 = l4 - 'A' + 10;
		*pt ++ = (unsigned char) ((h4*16)+l4);
	}
	return 0;
}


#endif
