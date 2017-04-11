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

#ifndef	_arith_h_
#define	_arith_h_

#ifndef	_conf_h_
#include	"conf.h"
#endif

extern NUMBER a_one,a_two;

/*
 * Prototypes
 */

#ifdef  __cplusplus
extern "C" {
#endif

#if defined(_PROTOTYPES) || !defined(_NO_PROTO)

void a_add( NUMBER*, NUMBER*, NUMBER* );
void a_assign( NUMBER*, NUMBER* );
int	a_cmp( NUMBER*, NUMBER* );
void a_div( NUMBER*, NUMBER*, NUMBER*, NUMBER* );
void a_div2( NUMBER* );
void a_ggt( NUMBER*, NUMBER*, NUMBER* );
void a_imult( NUMBER*, INT, NUMBER* );
void a_mult( NUMBER*, NUMBER*, NUMBER* );
void a_sub( NUMBER*, NUMBER*, NUMBER* );
void m_init( NUMBER*, NUMBER* );
void m_add( NUMBER*, NUMBER*, NUMBER* );
void m_mult( NUMBER*, NUMBER*, NUMBER* );
void m_exp( NUMBER*, NUMBER*, NUMBER* );
int	n_bits( NUMBER*, int);
void n_div( NUMBER*, NUMBER*, NUMBER*, NUMBER* );
int	n_cmp( INT*, INT*, int );
int	n_mult( INT*, INT, INT*, int );
int	n_sub( INT*, INT*, INT*, int, int );
int	n_bitlen( NUMBER* );

#else

void a_add();
void a_assign();
int	a_cmp();
void a_div();
void a_div2();
void a_ggt();
void a_imult();
void a_mult();
void a_sub();
void m_init();
void m_add();
void m_mult();
void m_exp();
int	n_bits();
void n_div();
int	n_cmp();
int	n_mult();
int	n_sub();
int	n_bitlen();

#endif

#ifdef  __cplusplus
}
#endif

#endif
