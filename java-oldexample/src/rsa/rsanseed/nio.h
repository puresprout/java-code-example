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

#ifndef	_NIO_H
#define	_NIO_H

#ifndef _arith_h_
#include	"arith.h"
#endif

/*
 * Prototypes
 */

#ifdef  __cplusplus
extern "C" {
#endif

#if defined(_PROTOTYPES) || !defined(_NO_PROTO)

int	num_sput(NUMBER*, char*, int);
int	num_fput(NUMBER*, FILE*);
int	num_sget(NUMBER*, char*);
int	num_fget(NUMBER*, FILE*);

#else

int	num_sput();
int	num_fput();
int	num_sget();
int	num_fget();

#endif

#ifdef  __cplusplus
}
#endif

#endif	/* _NIO_H */

