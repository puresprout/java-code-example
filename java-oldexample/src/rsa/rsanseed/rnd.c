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
#include <stdio.h>
#include <time.h>
#ifdef WIN32
#include <process.h>
#else
#include <unistd.h>
#endif

#include "rnd.h"
#include "rand48.h"
#include "nio.h"

void gen_number( int len, NUMBER *n )
{
	char *hex = "0123456789ABCDEF" ;
	char num[ MAXLEN*MAXBIT/4 +1 ];
	char *p;
	int i,l;
	
	p=&num[ sizeof(num) -1];
	*p-- = '\0';
	
	for (l=len; l--; p-- ) {
		i = lrand48() % 16;
		*p = hex[ i ];
	}
	p++;
	
	while (len-- && *p == '0')
		p++;
	
	num_sget( n, p );
}

void init_rnd()
{
	unsigned short seed[3];
	int  r = getpid();
	seed[0] = time((long *)0) & 0xFFFF;
	seed[1] = r & 0xFFFF;
	seed[2] = (time((long *)0) >> 16) & 0xFFFF;

	(void)seed48( seed );
}	

