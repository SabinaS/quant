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
%cupdebug
%line
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
 public Symbol getSymbol(int tokenID){
     return new Symbol(tokenID, yyline, yycolumn);
 }
 public Symbol getSymbol(int tokenID, Object value){
     return new Symbol(tokenID, yyline, yycolumn, value);
 }

%}

/**
 * Pattern definitions (--Aubrey)
 */

string		= \"[^\"]*\"
integer		= [\-]?[0-9]+
rat_num		= [\-]?[0-9]+\.[0-9]+

period		= \.

print		= (print|Print)

whitespace	= [ \n\t]

identifier	= [A-Za-z]+

%%
/**
 * Lexical rules (--Aubrey)
 */

{integer}	{ return getSymbol(sym.INTEGER_LITERAL, new Integer(yytext())); }
{rat_num}	{ return getSymbol(sym.RATIONAL_LITERAL, new Double(yytext())); }
{string}	{ return getSymbol(sym.STRING_LITERAL, yytext()); }
{print}		{ return getSymbol(sym.PRINT); }
{period}	{ return getSymbol(sym.PERIOD); }
{whitespace}    { /* Ignore. */ }
{identifier}	{ // Will need to add symbol table management.
		  return getSymbol(sym.IDENTIFIER, yytext());
		}
.               { return getSymbol(sym.INVALID); }
