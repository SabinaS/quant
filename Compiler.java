import java.io.*;
import java_cup.runtime.*;
import java.util.ArrayList;
import java.util.Scanner;
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

        String linked = "";
        try{
            Scanner loader;
            for(int i = 1; i < args.length; i++){
                loader = new Scanner(new File(args[i]));
                while(loader.hasNextLine()){
                    linked = linked+loader.nextLine() + "\n";
                }
            }
            loader = new Scanner(new File(args[0]));
            while(loader.hasNextLine()){ 
                linked = linked+loader.nextLine() + "\n";
            }
        } catch(Exception e) {}

        // Use our constructed parser to construct
        // and return the head of our AST.
        try {
            parser qParser = new parser (
                new QuantLexer(
                    new ByteArrayInputStream(linked.getBytes("UTF-8"))));
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

            File dir = new File(System.getProperty("user.dir"));
            ArrayList<String> classes = new ArrayList<String>();
            if(dir.isDirectory()){
                String[] flist = dir.list();
                for(String s : flist){
                    if(s.contains(".class"))
                        {classes.add(s);}
                }
            }

            String command = "jar cfve "+target+".fun "+target;
            for(String s: classes){
                command += " "+s;
            }
            Process p2 = Runtime.getRuntime().exec(command);
            p2.waitFor();
            // delete the class files
            for(String s : classes){ new File(s).delete(); }
            // delete intermediate file
            File file = new File(intermediateFile);
            file.delete();
        } catch(Exception e){ e.printStackTrace(); }
    }
}
