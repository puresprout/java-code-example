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

#ifndef	_rnd_h_
#define	_rnd_h_

#ifndef _arith_h_
#include	"arith.h"
#endif

#ifdef  __cplusplus
extern "C" {
#endif

void	gen_number( int, NUMBER* );
void	init_rnd( void );

#ifdef  __cplusplus
}
#endif

#endif
