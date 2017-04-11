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

#ifndef	_prim_h_
#define	_prim_h_

#ifndef _arith_h_
#include	"arith.h"
#endif

#ifdef  __cplusplus
extern "C" {
#endif

#if defined(_PROTOTYPES) || !defined(_NO_PROTO)

int	p_prim( NUMBER*, int );
void inv( NUMBER*, NUMBER*, NUMBER* );
void genprim(int len, int prob,	NUMBER *prim);
int genrsa(NUMBER *p1, NUMBER *p2, NUMBER *n, NUMBER *d, NUMBER *e);

#else

int	p_prim();
void inv();
void genprim();
int genrsa();

#endif

#ifdef  __cplusplus
}
#endif

#endif
