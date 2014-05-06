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
%cupdebug

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

plus      = (\+|Plus|plus)
minus		  = (\-|Minus|minus)
times		  = (\*|Times|times|multiplied by )
divide		= (\/|divided by|over)
and		    = (\&\&|and)
or		    = (\|\||or)

connector	= (\,|now)
semicolon	= (\;)
colon		  = (\:|\-\-)
period		= \.
lparen		= \(
rparen		= \)
qmark	        = \?

what		  = (What|what)

converted	  = (converted)
total		  = (total)
of		  = (of)
its		  = (Its |its |his |her )
a		  = (a |A |an |An )
is		    = (is)
is_c		  = (Is)
are		  = (are)
if		    = (if|If)
then		  = (then|Then)
else		  = (else|Else)
while		  = (while|While)
print		  = (print |Print )
set		    = (Set |set )
to		    = ( to )
than		  = (than)
less_than	= (\<|less |smaller )
greater_than	 = (\>|greater |more |bigger )
equal_to	     = (\=|equals |equal )
nequal_to	     = (\!\=)
not		         = (not)
there          = (There |there )
in		         = (in)
for		         = (for)
every 		     = (every)
has		     = (has)
with		     = (with)
having		     = (having)
apo_s		     = (\'s )
comment		   = \([^\(\)]*\)

whitespace	   = [ \n\t]

identifier	   = [A-Za-z]+

%%
/**
 * Lexical rules (--Aubrey)
 */
{integer}	    { return getSymbol(sym.INTEGER_LITERAL, new Integer(yytext())); }
{rat_num}	    { return getSymbol(sym.RATIONAL_LITERAL, new Double(yytext())); }
{plus}		    { return getSymbol(sym.PLUS); }
{colon}		    { return getSymbol(sym.COLON); }
{minus}		    { return getSymbol(sym.MINUS); }
{times}		    { return getSymbol(sym.TIMES); }
{divide}	    { return getSymbol(sym.DIVIDE); }
{its}		    { return getSymbol(sym.ITS); }
{converted}		{ return getSymbol(sym.CONVERTED); }
{total}		    { return getSymbol(sym.TOTAL); }
{of}		    { return getSymbol(sym.OF); }
{if}		      { return getSymbol(sym.IF); }
{then}		    { return getSymbol(sym.THEN); }
{else}		    { return getSymbol(sym.ELSE); }
{what}		    { return getSymbol(sym.WHAT); }
{while}		    { return getSymbol(sym.WHILE); }
{less_than}	  { return getSymbol(sym.LESS_THAN); }
{greater_than} { return getSymbol(sym.GREATER_THAN); }
{equal_to}	  { return getSymbol(sym.EQUAL_TO); }
{nequal_to}	  { return getSymbol(sym.NEQUAL_TO); }
{string}	    { return getSymbol(sym.STRING_LITERAL, yytext()); }
{print}		    { return getSymbol(sym.PRINT); }
{is}		      { return getSymbol(sym.IS); }
{is_c}		      { return getSymbol(sym.IS_C); }
{in}		      { return getSymbol(sym.IN); }
{a}		    { return getSymbol(sym.A); }
{has}		    { return getSymbol(sym.HAS); }
{with}		    { return getSymbol(sym.WITH); }
{having}	    { return getSymbol(sym.HAVING); }
{set}		      { return getSymbol(sym.SET); }
{to}		      { return getSymbol(sym.TO); }
{than}		    { return getSymbol(sym.THAN); }
{there}		    { return getSymbol(sym.THERE); }
{are}         { return getSymbol(sym.ARE); }
{not}		      { return getSymbol(sym.NOT); }
{lparen}	    { return getSymbol(sym.LPAREN); }
{rparen}	    { return getSymbol(sym.RPAREN); }
{connector}	  { return getSymbol(sym.CONNECTOR); }
{semicolon}	  { return getSymbol(sym.SEMICOLON); }
{and}		      { return getSymbol(sym.AND); }
{or}		      { return getSymbol(sym.OR); }
{in}		      { return getSymbol(sym.IN); }
{for}		      { return getSymbol(sym.FOR); }
{every}		    { return getSymbol(sym.EVERY); }
{apo_s}		    { return getSymbol(sym.APO_S); }
{period}	    { return getSymbol(sym.PERIOD); }
{qmark}		    { return getSymbol(sym.QMARK); }
{whitespace}  { /* Ignore. */ }
{comment}	{ /* Ignore */ }
{identifier}	{ // Will need to add symbol table management.
		  return getSymbol(sym.IDENTIFIER, yytext());
		}
.               { return getSymbol(sym.INVALID); }
