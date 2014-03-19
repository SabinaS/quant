************************
* QUANT README, 0.01
************************

If developing at the command line on a
UNIX system:

1.  Pre-compiler compilation:
	make lexparse
2.  Compiler compilation:
	make compiler
3.  2-in-1:
	make all
4.  To run the compiler:
	./compile.sh [filename]

(--Aubrey)

Note: Depending on how you linked java_cup and jflex, you may need
to specfically add their jars to the Java CLASSPATH, e.g.:

    export CLASSPATH=./lib/java-cup-11b-runtime.jar:./lib/java-cup-11b.jar:./lib/jflex-1.5.0.jar:.

Otherwise you may see some errors regarding missing class
definitions.
