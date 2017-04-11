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
#include	<stdlib.h>

#include	"arith.h"
#include	"prim.h"
#include	"nio.h"
#include	"rnd.h"

#if LOCAL
char *prog;
#endif

NUMBER a_three,a_four;

#if LOCAL
void usage()
{
	fprintf(stderr,"usage: %s digits [probability]\n",prog);
	exit (1);
}
#endif

#if LOCAL_TEST
main( argc, argv )
char **argv;
{
	NUMBER prim;
	int len,i,prob;

	prog = argv[0];

	if ( argc < 2 || 3 < argc )
		usage();

	len = atoi( argv[1] );
	if (argc > 2)
		prob = atoi( argv[2] );
	else
		prob = 10;

	a_add( &a_one, &a_two, &a_three );
	a_add( &a_two, &a_two, &a_four );

	init_rnd();

	do {
		gen_number( len, &prim );
	} while ( !prim.n_len );

	a_mult( &prim, &a_two, &prim );
	a_mult( &prim, &a_three, &prim );
	a_add( &prim, &a_one, &prim );

	for (i=1 ;; i++) {
		if (p_prim( &prim, prob ))
			break;
		if (i % 2)
			a_add( &prim, &a_four, &prim );
		else
			a_add( &prim, &a_two, &prim );
	}
	fprintf(stderr,"%d cycles\n",i);

	num_fput( &prim, stdout );
}
#else
void genprim(int len, int prob, 	NUMBER *prim)
{
	int i;

	a_add( &a_one, &a_two, &a_three );
	a_add( &a_two, &a_two, &a_four );
	init_rnd();

	do {
		gen_number( len, prim );
	} while ( !prim->n_len );

	a_mult( prim, &a_two, prim );
	a_mult( prim, &a_three, prim );
	a_add( prim, &a_one, prim );

	for (i=1 ;; i++) {
		if (p_prim( prim, prob ))
			break;
		if (i % 2)
			a_add( prim, &a_four, prim );
		else
			a_add( prim, &a_two, prim );
	}
/*
	fprintf(stderr,"%d cycles\n",i);

	if ( num_sput( &prim, n_print, STRLEN + 1 ) == EOF )
		return(EOF);
*/
	return;
}

#endif