/**
 * Lexical analyzer definition for Quant
 * using JFlex (--Aubrey)
 */

import java_cup.runtime.*;

%%

/* OPTION %cupsym 'name' to name the generated CUP class
 */

/* OPTION %cupdebug to get symbol names printed with line, column
 * and matched text for input files
 */

%cup
%cupdebug            /* turn on character counting now, for debugging */
%line
%debug
%column
%unicode              /* turn on debugging for now */
%class QuantLexer
%{

/*
 Methods to return symbols, along with position (line, column)
 and value (where appropriate). (--Aubrey)
 */

 /**
  * Return a vanilla symbol (token ID only).
  */
 Symbol getSymbol(int tokenID){
     return new Symbol(tokenID, yyline, yycolumn);
 }
 Symbol getSymbol(int tokenID, Object value){
     return new Symbol(tokenID, yyline, yycolumn, value);
 }

%}

/**
 * Pattern definitions (--Aubrey)
 */

string		= \"[^\"]*\"

period		= \.

print		= (print|Print)

whitespace	= [ \n\t]

%%
/**
 * Lexical rules (--Aubrey)
 */

{string}	{ return getSymbol(sym.STRING_LITERAL, yytext()); }
{print}		{ return getSymbol(sym.PRINT); }
{period}	{ return getSymbol(sym.PERIOD); }
{whitespace}    { /* Ignore. */ }
.               { return getSymbol(sym.INVALID); }
