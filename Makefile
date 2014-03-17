cup = /home/aubrey/cup/java-cup-11b.jar 
jflex = /home/aubrey/jflex/jflex-1.5.0/lib/jflex-1.5.0.jar

.PHONY: lexer
lexer:
	java -jar $(cup) -nowarn tokens.cup
	rm -rf parser.java
	java -jar $(jflex) lexer.jflex
clean:
	rm -rf parser.java sym.java QuantLexer.java
	
