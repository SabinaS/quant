import java.io.*;
import java_cup.runtime.*;

/**
 * WIP Compiler for Quant.
 * (Currently just attempts to generate
 * a decorated syntax tree from an input
 * file.) -- Aubrey
 */

public class Compiler {

    public static void main(String[] args) {
        try {
            parser qParser = new parser (
                new QuantLexer(new FileReader(args[0])));
            qParser.parse();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
