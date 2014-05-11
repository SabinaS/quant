# -------------------------------------------------------------------------
# Makefile for Quant:
# 		a low-barrier programming language for elementary problem-solving
# -------------------------------------------------------------------------

cup = ./lib/java-cup-11b.jar
jflex = ./lib/jflex-1.5.0.jar
cuprt = ./lib/java-cup-11b-runtime.jar
home = ./
src = ./src/*
tests = tests.qnt
tests_runnable = tests.fun
test_results = testResults.txt
test_output = testOutput.txt

jar: lexparse compiler
	cp *.class ./exe
	jar cvfe exe/QuantCompiler.jar Compiler -C exe *.class exe/java_cup/runtime/*.class 
	rm exe/*.class
	make clean

lexparse:
	java -jar $(cup) -expect 1 -nopositions -nowarn parser.cup
	java -jar $(jflex) lexer.jflex

compiler:
	javac -d ./ ./src/*.java
	javac -cp $(cuprt):$(src):$(cup):$(home) Compiler.java sym.java parser.java QuantLexer.java

test: jar
	cd exe && javac TestGenerator.java
	cd exe && java TestGenerator
	cd exe && ./compile $(tests) 
	cd exe && ./run $(tests_runnable) > $(test_output)
	cd exe && javac TestChecker.java
	cd exe && java TestChecker $(test_output) $(test_results)

all: lexparse compiler

clean:
	rm -f parser.java
	rm -f sym.java
	rm -f QuantLexer.java
	rm -rf *.class
	rm -rf ./src/*.class
	rm -rf *.java~
	rm -rf exe/$(tests) exe/$(test_results) exe/$(test_output) exe/*.fun
