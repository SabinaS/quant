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
integer		= ( [\-])?[0-9]+
rat_num		= ( [\-])?[0-9]+\.[0-9]+

plus		= (\+|Plus|plus)
minus		= (\-|Minus|minus)
times		= (\*|Times|times)
divide		= (\\|divided by|over)

period		= \.
lparen		= \(
rparen		= \)

is_a		= ( is a )
is		= ( is )
print		= (print |Print )
set		= (Set |set )
to		= ( to )

whitespace	= [ \n\t]

identifier	= [A-Za-z]+

%%
/**
 * Lexical rules (--Aubrey)
 */
{integer}	{ return getSymbol(sym.INTEGER_LITERAL, new Integer(yytext())); }
{rat_num}	{ return getSymbol(sym.RATIONAL_LITERAL, new Double(yytext())); }
{plus}		{ return getSymbol(sym.PLUS); }
{minus}		{ return getSymbol(sym.MINUS); }
{times}		{ return getSymbol(sym.TIMES); }
{divide}	{ return getSymbol(sym.DIVIDE); }
{string}	{ return getSymbol(sym.STRING_LITERAL, yytext()); }
{print}		{ return getSymbol(sym.PRINT); }
{is}		{ return getSymbol(sym.IS); }
{is_a}		{ return getSymbol(sym.IS_A); }
{set}		{ return getSymbol(sym.SET); }
{to}		{ return getSymbol(sym.TO); }
{lparen}	{ return getSymbol(sym.LPAREN); }
{rparen}	{ return getSymbol(sym.RPAREN); }
{period}	{ return getSymbol(sym.PERIOD); }
{whitespace}    { /* Ignore. */ }
{identifier}	{ // Will need to add symbol table management.
		  return getSymbol(sym.IDENTIFIER, yytext());
		}
.               { return getSymbol(sym.INVALID); }
