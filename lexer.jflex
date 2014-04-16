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
less_than	= (\<|is less than|are less than|less than)
greater_than	= (\>|is greater than|are greater than|greater than)
equal_to	= (\=|equals|is equal to|are equal to)
and		= (\&\&|and)
or		= (\|\||or)

connector	= (\,|\, and|;|; and)
period		= \.
lparen		= \(
rparen		= \)

is_a		= ( is a )
is		= ( is )
if		= (if|If)
then		= (then|Then)
else		= (else|Else)
while		= (while|While)
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
{less_than}	{ return getSymbol(sym.LESS_THAN); }
{greater_than}	{ return getSymbol(sym.GREATER_THAN); }
{equal_to}	{ return getSymbol(sym.EQUAL_TO); }
{string}	{ return getSymbol(sym.STRING_LITERAL, yytext()); }
{print}		{ return getSymbol(sym.PRINT); }
{is}		{ return getSymbol(sym.IS); }
{is_a}		{ return getSymbol(sym.IS_A); }
{set}		{ return getSymbol(sym.SET); }
{to}		{ return getSymbol(sym.TO); }
{lparen}	{ return getSymbol(sym.LPAREN); }
{rparen}	{ return getSymbol(sym.RPAREN); }
{connector}	{ return getSymbol(sym.CONNECTOR); }
{and}		{ return getSymbol(sym.AND); }
{or}		{ return getSymbol(sym.OR); }
{period}	{ return getSymbol(sym.PERIOD); }
{whitespace}    { /* Ignore. */ }
{identifier}	{ // Will need to add symbol table management.
		  return getSymbol(sym.IDENTIFIER, yytext());
		}
.               { return getSymbol(sym.INVALID); }
