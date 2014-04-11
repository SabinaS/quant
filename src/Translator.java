/**
 * Handles translation of an AST generated
 * from Quant code into Java as an intermediate
 * form.
 *
 * @author Aubrey
 */

public class Translator{

    /** Translation directive for default placement. */
    public static final String IN_PLACE = "IN PLACE";
  
    /** Translation directive for placement after a sibling. */
    public static final String AFTER_SIBLING = "AFTER SIBLING";

    /** Root-level translation directive for placement into main(). */
    public static final String IN_MAIN = "IN MAIN";

    /** Index of the main block within a translated line block. */
    public static final int MAIN_BLOCK_INDEX = 0;

    public Node AST;

    /**
     * Constructs a translator object.
     * 
     * @param head of AST for the Quant program.
     */
    public Translator(Node a){
        AST = a;
    }

    /**
     * Returns a full translation for the
     * AST.
     *
     * @author Aubrey
     */
    public String getJava(String programName){
        String[] components = AST.translate();
        StaticCode sc = new StaticCode();
        String java = "public class "+programName+"{\n"+
                      "public static void main(String[] args){\n"+
                      sc.getFactoryStructure(programName)+"\n"+
                      components[MAIN_BLOCK_INDEX] + "\n"+
                      "}\n"+
                      StaticCode.VALUE_STRUCTURE+"\n"+
                      StaticCode.UNIT_STRUCTURE+"\n"+
                      "}";
        return java;
    }

    /** Static constant container for portions of the translated
      * program not affected by AST structure.
      * @author Aubrey
      */
    private class StaticCode{
        public String getFactoryStructure(String n){
            return n+" factory = new "+n+"();\n";
        }
        public static final String VALUE_STRUCTURE = 
            "class NUMVAL{\n"+
            "    public int vi = 0;\n"+
            "    public double vr = 0;\n"+
            "    public int m = 0;\n"+
            "    public UNIT vu;\n"+
            "    public NUMVAL(int v, UNIT u){ vi = v; m = 0; vu = u;}\n"+
            "    public NUMVAL(double v, UNIT u){ vr = v; m = 1; vu = u;}\n"+
            "    public String toString(){"+
            "        return (m == 0 ? \"\"+vi : \"\"+vr)" +
            "        +vu.toString(); }\n"+
            "    public NUMVAL VALPLUS(NUMVAL n){\n"+
            "        double tv = vi + vr;\n"+
            "        double nv = n.vi + n.vr;\n"+
            "        int nm = n.m == 0 && m==0 ? 0 : 1;\n"+
	    "        if(nm == 0) return new NUMVAL((int)(tv+nv),vu);\n"+
            "        else return new NUMVAL(tv+nv,vu);\n"+
            "    }\n"+
            "    public NUMVAL VALMINUS(NUMVAL n){\n"+
            "        double tv = vi + vr;\n"+
            "        double nv = n.vi + n.vr;\n"+
            "        int nm = n.m == 0 && m==0 ? 0 : 1;\n"+
	    "        if(nm == 0) return new NUMVAL((int)(tv-nv),vu);\n"+
            "        else return new NUMVAL(tv-nv,vu);\n"+
            "    }\n"+
            "    public NUMVAL VALTIMES(NUMVAL n){\n"+
            "        double tv = vi + vr;\n"+
            "        double nv = n.vi + n.vr;\n"+
            "        int nm = n.m == 0 && m==0 ? 0 : 1;\n"+
	    "        if(nm == 0) return new NUMVAL((int)(tv*nv),vu);\n"+
            "        else return new NUMVAL(tv*nv,vu);\n"+
            "    }\n"+
            "    public NUMVAL VALDIVIDE(NUMVAL n){\n"+
            "        double tv = vi + vr;\n"+
            "        double nv = n.vi + n.vr;\n"+
            "        int nm = n.m == 0 && m==0 ? 0 : 1;\n"+
	    "        if(nm == 0) return new NUMVAL((int)(tv/nv),vu);\n"+
            "        else return new NUMVAL(tv/nv,vu);\n"+
            "    }\n"+
            "}";
        public static final String UNIT_STRUCTURE =
            "class UNIT{\n"+
            "    public String v = \"\";\n"+
            "    public UNIT(String u){ v = u; }\n"+
            "    public String toString(){"+
            "        return v.length()>0 ? \" \"+v : \"\"; }"+
            "}";
    }
}
