# -------------------------------------------------------------------------
# Makefile for Quant:
# 		a low-barrier programming language for elementary problem-solving
# -------------------------------------------------------------------------

cup = ./lib/java-cup-11b.jar
jflex = ./lib/jflex-1.5.0.jar
cuprt = ./lib/java-cup-11b-runtime.jar
home = ./

.PHONY: lexer
lexparse:
	java -jar $(cup) -nowarn parser.cup
	java -jar $(jflex) lexer.jflex

compiler:
	javac -cp $(cuprt):$(cup):$(home) Compiler.java sym.java parser.java QuantLexer.java

all: lexparse compiler

clean:
	rm -f parser.java
	rm -f sym.java
	rm -f QuantLexer.java
	rm -rf *.class
	rm -rf *.java~
