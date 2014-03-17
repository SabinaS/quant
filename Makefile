cup = /home/aubrey/cup/java-cup-11b.jar 

.PHONY: lexer
lexer:
	java -jar $(cup) -nowarn tokens.cup
	rm -rf parser.java
clean:
	rm -rf parser.java sym.java
	
