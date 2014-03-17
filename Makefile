cup = /home/aubrey/cup/java-cup-11b.jar 
jflex = /home/aubrey/jflex/jflex-1.5.0/lib/jflex-1.5.0.jar

.PHONY: lexer
lexparse:
	java -jar $(cup) -nowarn parser.cup
	java -jar $(jflex) lexer.jflex
clean:
	rm -rf parser.java sym.java QuantLexer.java
	
