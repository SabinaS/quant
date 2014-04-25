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
        String java = "import java.util.ArrayList;"+
		      "public class "+programName+"{\n"+
                      "    public ArrayList<String> rateName1;\n"+
                      "    public ArrayList<String> rateName2;\n"+
                      "    public ArrayList<Double> rateNum;\n"+
                      "    public " + programName + "() {\n"+
                      "         rateName1 = new ArrayList<String>();\n" +
                      "         rateName2 = new ArrayList<String>();\n" +
                      "         rateNum = new ArrayList<Double>();\n" +
                      "    }\n"+
                      "    public void registerRate(String u1, String u2, Double rate) {\n"+
                      "        rateName1.add(u1);\n" +
                      "        rateName2.add(u2);\n" +
                      "        rateNum.add(rate);\n" +
                      "    }\n"+
                      "    public double findRate(String u1, String u2){\n"+
                      "        for(int i = 0; i < rateName1.size(); i++) {\n"+
                      "            if(rateName1.get(i).equals(u1)) {\n"+
                      "                if(rateName2.get(i).equals(u2)) {\n"+
                      "                    return rateNum.get(i);" +
                      "                }\n"+
                      "            }\n"+
                      "            if(rateName2.get(i).equals(u1)) {\n"+
                      "                if(rateName1.get(i).equals(u2)) {\n"+
                      "                    return 1.0 / (rateNum.get(i));"+
                      "                }\n"+
                      "            }\n"+
                      "        }\n"+
                      "        return 1;\n"+
                      "    }\n"+
                      "   public static void main(String[] args) {\n"+
                          sc.getFactoryStructure(programName) + "\n"+
                          components[MAIN_BLOCK_INDEX] + "\n"+
                          "}\n"+
                          StaticCode.VALUE_STRUCTURE+"\n"+
                          getUnitStructure(programName)+"\n"+
                      "}";
        return java;
    }
           public static String getUnitStructure(String pname){
                return 
                "class UNIT{\n"+
                "    public String v = \"\";\n"+
                "    public "+pname+" fact;"+
                "    public UNIT(String u,"+pname+" f){ v = u; fact = f;}\n"+
                "    public String toString(){"+
                "        return v.length()>0 ? \" \"+v : \"\"; }"+
                "}";
            }

    /** Static constant container for portions of the translated
      * program not affected by AST structure.
      * @author Aubrey
      */
    private class StaticCode{
        public String getFactoryStructure(String n){
            return n + " factory = new " + n + "();\n";
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
            "        double mult = 1;\n"+
            "        if(!vu.v.equals(n.vu.v)) { mult = vu.fact.findRate(vu.v, n.vu.v); }\n"+
            "        int nm = n.m == 0 && m==0 ? 0 : 1;\n"+
            "        double nr = tv+nv*mult;\n"+
            "        UNIT ur = vu;\n"+
            "        if(nm == 0) return new NUMVAL((int) nr,ur);\n"+
            "        else return new NUMVAL(nr, ur);\n"+
            "    }\n"+
            "    public NUMVAL VALMINUS(NUMVAL n){\n"+
            "        double tv = vi + vr;\n"+
            "        double nv = n.vi + n.vr;\n"+
            "        double mult = 1;\n"+
            "        if(!vu.v.equals(n.vu.v)) { mult = vu.fact.findRate(vu.v, n.vu.v); }\n"+
            "        int nm = n.m == 0 && m==0 ? 0 : 1;\n"+
            "        double nr = tv-nv*mult;\n"+
            "        UNIT ur = vu;\n"+
            "        if(nm == 0) return new NUMVAL((int) nr, ur);\n"+
            "        else return new NUMVAL(nr,ur);\n"+
            "    }\n"+
            "    public NUMVAL VALTIMES(NUMVAL n){\n"+ //add squared functionality??
            "        double tv = vi + vr;\n"+
            "        double nv = n.vi + n.vr;\n"+
            "        int nm = n.m == 0 && m==0 ? 0 : 1;\n"+
            "        UNIT ur = new UNIT(vu.v +  \"*\" + n.vu.v, vu.fact);"+
            "        if(nm == 0) return new NUMVAL((int)(tv*nv), ur);\n"+
            "        else return new NUMVAL(tv*nv,ur);\n"+
            "    }\n"+
            "    public NUMVAL VALDIVIDE(NUMVAL n){\n"+
            "        double tv = vi + vr;\n"+
            "        double nv = n.vi + n.vr;\n"+
            "        int nm = n.m == 0 && m==0 ? 0 : 1;\n"+
            "        UNIT ur = new UNIT(vu.v +  \"/\" + n.vu.v, vu.fact);"+
            "        if(nm == 0) return new NUMVAL((int)(tv/nv), ur);\n"+
            "        else return new NUMVAL(tv/nv, ur);\n"+
            "    }\n"+
            "    public boolean VALLT(NUMVAL n){\n"+
            "        double tv = vi + vr;\n"+
            "        double nv = n.vi + n.vr;\n"+
            "        double mult = 1;\n"+
            "        if(!vu.v.equals(n.vu.v)) { mult = vu.fact.findRate(vu.v, n.vu.v); }\n"+
            "        return (tv<nv*mult);\n"+
            "    }\n"+
            "    public boolean VALGT(NUMVAL n){\n"+
            "        double tv = vi + vr;\n"+
            "        double nv = n.vi + n.vr;\n"+
            "        double mult = 1;\n"+
            "        if(!vu.v.equals(n.vu.v)) { mult = vu.fact.findRate(vu.v, n.vu.v); }\n"+
            "        return (tv>nv*mult);\n"+
            "    }\n"+
            "    public boolean VALEQ(NUMVAL n){\n"+
            "        double tv = vi + vr;\n"+
            "        double nv = n.vi + n.vr;\n"+
            "        double mult = 1;\n"+
            "        if(!vu.v.equals(n.vu.v)) { mult = vu.fact.findRate(vu.v, n.vu.v); }\n"+
            "        return (tv==nv*mult);\n"+
            "    }\n"+
            "    public boolean VALNEQ(NUMVAL n){\n"+
            "        double tv = vi + vr;\n"+
            "        double nv = n.vi + n.vr;\n"+
            "        double mult = 1;\n"+
            "        if(!vu.v.equals(n.vu.v)) { mult = vu.fact.findRate(vu.v, n.vu.v); }\n"+
            "        return (tv!=nv*mult);\n"+
            "    }\n"+
            "}";

    }
}
