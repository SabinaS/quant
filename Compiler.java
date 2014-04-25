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

        // Root of the abstract syntax tree.
        Node ASTRoot = null;
        Translator translator;

        // Use our constructed parser to construct
        // and return the head of our AST.
        try {
            parser qParser = new parser (
                new QuantLexer(new FileReader(args[0])));
            //qParser.debug_parse();
            ASTRoot = (Node) qParser.parse().value;
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        // Construct a translator for the given AST.
        translator = new Translator(ASTRoot);

        String target = args[0].substring(0,args[0].indexOf(".qnt"));
        String java = translator.getJava(target);
        
        // Generate the bytecode (Aubrey)
        try{
            String intermediateFile = target + ".java";
            PrintWriter writer = new PrintWriter(intermediateFile);
            writer.println(java);
            writer.close();

            Process p = Runtime.getRuntime().exec("javac "+intermediateFile);
            p.waitFor(); // Wait for the process to terminate

            File file = new File(intermediateFile);
            //file.delete();
        } catch(Exception e){ e.printStackTrace(); }
    }
}
