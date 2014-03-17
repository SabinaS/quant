cup = ./lib/java-cup-11b.jar 
jflex = ./lib/jflex-1.5.0.jar
cuprt = ./lib/java-cup-11b-runtime.jar

.PHONY: lexer
lexparse:
	java -jar $(cup) -nowarn parser.cup
	java -jar $(jflex) lexer.jflex
compiler:
	javac -cp $(cuprt) Compiler.java sym.java parser.java QuantLexer.java
all: lexparse compiler
clean:
	rm -rf parser.java sym.java QuantLexer.java *.class *.java~
	
