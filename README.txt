************************
* QUANT README, 1.00
************************

If developing at the command line on a
UNIX system:

1.  From top-level directory, run make jar.
2.  cd into /exe
3.  run ./compile [file]
4.  win

(--Aubrey)

Note: Depending on how you linked java_cup and jflex, you may need
to specfically add their jars to the Java CLASSPATH, e.g.:

    export CLASSPATH=./lib/java-cup-11b-runtime.jar:./lib/java-cup-11b.jar:./lib/jflex-1.5.0.jar:.

Otherwise you may see some errors regarding missing class
definitions.
