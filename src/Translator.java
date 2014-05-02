/**
 * Handles translation of an AST generated
 * from Quant code into Java as an intermediate
 * form.
 *
 * @author Aubrey
 */

import java.util.ArrayList;

public class Translator{


    public static final String BEFORE = "BEFORE";

    /** Translation directive for default placement. */
    public static final String IN_PLACE = "IN PLACE";

    /** Translation directive for placement after a sibling. */
    public static final String AFTER_SIBLING = "AFTER SIBLING";

    /** Translation directive for placement of a translation as a nested class. */
    public static final String NEST_CLASS = "NESTED CLASS";

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

    public String translateTypes(ArrayList<DefinedType> types, String pname){
        String tr = "";
        for(int i = 0; i < types.size(); i++){
            DefinedType type = types.get(i);
            if(type.linksToType(type.typeName)){
                System.out.println("Hold on!  You're saying that a "+type.typeName+" has "+
                                      "to contain itself!  Never-ending "+type.typeName+"s!");
                System.exit(0);
            }
     
            String typeBlock = "class "+type.typeName+"{\n"+
                                "\t"+pname+" factory;\n"+
                                "\tpublic "+type.typeName+"("+pname+" f){\n\tfactory = f;\n";
            for(int j = 0; j < type.fields.size(); j++){
                if(type.fieldValues.get(j).length() > 0){
                    typeBlock+="\t"+type.fields.get(j)+" = "+type.fieldValues.get(j)+";\n";
                } else{
                    if(type.fieldTypes.get(j).equals("Object"))
                        typeBlock+="\t"+type.fields.get(j)+" = new "+
                            type.fieldTypes.get(j)+"();\n";
                    else if(type.fieldTypes.get(j).equals("NUMVAL"))
                        typeBlock+="\t"+type.fields.get(j)+" = new "+
                            type.fieldTypes.get(j)+"(0, new UNIT(\"\",factory));\n";
                    else if(type.fieldTypes.get(j).equals("String"))
                        typeBlock+="\t"+type.fields.get(j)+" = \"\";\n";
                    else if(type.fieldTypes.get(j).contains("__COL"))
                        typeBlock+="\t"+type.fields.get(j)+" = new "+
                            type.fieldTypes.get(j)+"(factory,0);\n";
                    else{
                        typeBlock+="\t"+type.fields.get(j)+" = new "+
                            type.fieldTypes.get(j)+"(factory);\n";

                    }
                }
            } 
            typeBlock+= "}\n";
            for(int j = 0; j < type.fields.size(); j++){
                typeBlock+="\tpublic "+type.fieldTypes.get(j)+" "+
                               type.fields.get(j) +";\n";
            }
            typeBlock +="}\n";
            // Collections
            typeBlock +="class "+type.typeName+"__COL{\n"+
                        "    public ArrayList<"+type.typeName+"> col;\n"+
                        "    public "+pname+" factory;\n"+
                        "    public "+type.typeName+"__COL("+pname+" f, int ct){\n"+
                        "        factory = f;\n"+
                        "        col = new ArrayList<"+type.typeName+">();\n"+
                        "        for(int i = 0; i < ct; i++){\n"+
                        "            col.add(new "+type.typeName+"(factory));\n"+
                        "        }}\n"+
                        "    public NUMVAL count(){ return new NUMVAL(col.size(), new UNIT(\""+type.typeName+"s\",factory)); }\n";
                        
            for(int j = 0; j < type.fields.size(); j++){
                String fType = type.fieldTypes.get(j);
                if(fType.equals("NUMVAL")){
                    typeBlock+="public NUMVAL total"+type.fields.get(j)+"(){\n"+
                               "    NUMVAL tot = new NUMVAL(0, new UNIT(\"\",factory));\n"+
                               "    for(int i = 0; i < col.size(); i++){\n"+
                               "        tot.VALPLUS(col.get(i)."+type.fields.get(j)+");\n}\n"+
                               "    return tot;\n"+
                               "}";
                } if(fType.contains("__COL")){
                    typeBlock+="public NUMVAL total"+type.fields.get(j)+"(){\n"+
                               "    NUMVAL tot = "+type.fields.get(j)+".count();\n"+
                               "    for(int i = 0; i < col.size(); i++){\n"+
                               "        tot.VALPLUS(col.get(i)."+type.fields.get(j)+".count());\n}\n"+
                               "    return tot;\n"+
                               "}";

                }
            }
            typeBlock += "\n}\n";
            tr += typeBlock;
        }
        return tr;
    }

    /**
     * Returns a full translation for the
     * AST.
     *
     * @author Aubrey
     */
    public String getJava(String programName){
        String[] components = AST.translate();
        ArrayList<DefinedType> userTypes = ((LineBlock) AST).symrecord.objTypes;
        String typesBlock = translateTypes(userTypes,programName);
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
                      "        rateName1.add(new UNIT(u1,this).toString());\n" +
                      "        rateName2.add(new UNIT(u2,this).toString());\n" +
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
                          typesBlock+"\n"+
                      "}";
        return java;
    }

           public static String getUnitStructure(String pname){
                return 
                "class UNIT{\n"+
                "    public ArrayList<String> numerator;\n"+
                "    public ArrayList<String> denominator;\n"+
                "    public "+pname+" fact;\n"+
                "    public UNIT(String u,"+pname+" f){"+
	        "        numerator = new ArrayList<String>();\n"+
                "        denominator = new ArrayList<String>();\n"+
                "        if(u!=null)  numerator.add(u);\n"+
                "        fact = f;}\n"+
             
                "    public boolean isType(){\n"+
                "        if(numerator.size()==0 || numerator.get(0).equals(\"\")){ return false; }\n"+
                "        try{\n"+
                "            Class.forName(\""+pname+"$\"+numerator.get(0));\n"+
                "            return true;\n"+
                "        } catch(Exception e){ return false; }}\n"+
                "    public UNIT mult(UNIT u){\n"+
                "        UNIT nu = new UNIT(null,fact);\n"+
                "        for(String s : numerator){ if(s.length() > 0) nu.numerator.add(s); }\n"+
                "        for(String s : u.numerator){ if(s.length() > 0) nu.numerator.add(s);}\n"+
                "        for(String s :denominator){ if(s.length() > 0){ nu.denominator.add(s); "+
                "            if(nu.numerator.contains(s)){"+
                "               nu.numerator.remove(s);"+
                "               nu.denominator.remove(s); }}}\n"+
                "        for(String s:u.denominator){ if(s.length() > 0){ nu.denominator.add(s); "+
                "            if(nu.numerator.contains(s)){"+
                "               nu.numerator.remove(s);"+
                "               nu.denominator.remove(s); }}}\n"+

                "        return nu; }\n"+
                "    public UNIT div(UNIT u){\n"+
                "        UNIT nu = new UNIT(null,fact);\n"+
                "        for(String s : numerator){ if(s.length() > 0) nu.numerator.add(s); }\n"+
                "        for(String s : u.denominator){ if(s.length() > 0) nu.numerator.add(s);}\n"+
                "        for(String s :denominator){ if(s.length() > 0){ nu.denominator.add(s); "+
                "            if(nu.numerator.contains(s)){"+
                "               nu.numerator.remove(s);"+
                "               nu.denominator.remove(s); }}}\n"+
                "        for(String s:u.numerator){ if(s.length() > 0){ nu.denominator.add(s); "+
                "            if(nu.numerator.contains(s)){"+
                "               nu.numerator.remove(s);"+
                "               nu.denominator.remove(s); }}}\n"+

                "        return nu; }\n"+ 
                "    public String toString(){"+
                "        if(numerator.size()==0&&denominator.size()==0) "+
                "            return \"\";\n"+
                "        if(numerator.size()==1&&denominator.size()==0) "+
                "            return numerator.get(0);\n"+
                "        String strForm = \"(\";\n"+
                "        for(String s:numerator) strForm=strForm+s+\"*\";"+
                "        strForm = strForm.substring(0,strForm.length()-1) +"+
                "        \")\";"+
                "        if(denominator.size()>0){\n"+
                "            strForm += \"/(\";\n"+
                "            for(String s:denominator)\n"+
                "                strForm += s+\"*\";\n"+
                "            strForm = strForm.substring(0,strForm.length()-1)"+
                "                + \")\";"+
                "        }\n"+
                "        return strForm; }"+
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
            "        String unit = vu.toString();\n"+
            "        if(vu.isType()&&unit.charAt(unit.length()-1)!='s'&&(vi+vr) != 1) unit = unit + \"s\";"+
            "        return (m == 0 ? \"\"+vi : \"\"+vr)" +
            "        +\" \"+unit; }\n"+
            "    public NUMVAL rawCt(){\n"+
            "        return new NUMVAL(vi, new UNIT(\"\",vu.fact));\n"+
            "    }\n"+
            "    public NUMVAL VALPLUS(NUMVAL n){\n"+
            "        double tv = vi + vr;\n"+
            "        double nv = n.vi + n.vr;\n"+
            "        double mult = 1;\n"+
            "        if(!vu.toString().equals(n.vu.toString())) {\n"+
    	    "        mult = vu.fact.findRate(vu.toString(), n.vu.toString());\n"+
    	    "        }\n;"+
            "        int nm = n.m == 0 && m==0 ? 0 : 1;\n"+
            "        double nr = tv+nv*mult;\n; "+
            "        UNIT ur = vu;\n"+
            "        if(nm == 0) return new NUMVAL((int) nr,ur);\n"+
            "        else return new NUMVAL(nr, ur);\n"+
            "    }\n"+
            "    public NUMVAL VALMINUS(NUMVAL n){\n"+
            "        double tv = vi + vr;\n"+
            "        double nv = n.vi + n.vr;\n"+
            "        double mult = 1;\n"+
            "        if(!vu.toString().equals(n.vu.toString())) {\n"+
    	    "        mult = vu.fact.findRate(vu.toString(), n.vu.toString());\n"+
    	    "        }\n"+
            "        int nm = n.m == 0 && m==0 ? 0 : 1;\n"+
            "        double nr = tv-nv*mult;\n"+
            "        if(nr < 0 && vu.isType()) nr = 0;\n"+
            "        UNIT ur = vu;\n"+
            "        if(nm == 0) return new NUMVAL((int) nr, ur);\n"+
            "        else return new NUMVAL(nr,ur);\n"+
            "    }\n"+
            "    public NUMVAL VALTIMES(NUMVAL n){\n"+
            "        double tv = vi + vr;\n"+
            "        double nv = n.vi + n.vr;\n"+
            "        int nm = n.m == 0 && m==0 ? 0 : 1;\n"+
            "        double rate = vu.fact.findRate(vu.toString(),n.vu.toString());\n"+
            "        UNIT ur = rate == 1 ? vu.mult(n.vu) : vu.mult(vu);"+
            "        if(nm == 0) return new NUMVAL((int)(tv*nv*rate), ur);\n"+
            "        else return new NUMVAL(tv*nv*rate,ur);\n"+
            "    }\n"+
            "    public NUMVAL VALDIVIDE(NUMVAL n){\n"+
            "        double tv = vi + vr;\n"+
            "        double nv = n.vi + n.vr;\n"+
            "        int nm = n.m == 0 && m==0 ? 0 : 1;\n"+
            "        double rate = vu.fact.findRate(vu.toString(),n.vu.toString());\n"+
            "        UNIT ur = rate == 1 ? vu.div(n.vu) : vu.div(vu);"+
            "        if(nm == 0) return new NUMVAL((int)(tv/(nv*rate)), ur);\n"+
            "        else return new NUMVAL(tv/(nv*rate), ur);\n"+
            "    }\n"+
            "    public boolean VALLT(NUMVAL n){\n"+
            "        double tv = vi + vr;\n"+
            "        double nv = n.vi + n.vr;\n"+
            "        double mult = 1;\n"+
            "        if(!vu.toString().equals(n.vu.toString())) { mult = vu.fact.findRate(vu.toString(), n.vu.toString()); }\n"+
            "        return (tv<nv*mult);\n"+
            "    }\n"+
            "    public boolean VALGT(NUMVAL n){\n"+
            "        double tv = vi + vr;\n"+
            "        double nv = n.vi + n.vr;\n"+
            "        double mult = 1;\n"+
            "        if(!vu.toString().equals(n.vu.toString())) {\n"+
            "        mult = vu.fact.findRate(vu.toString(), n.vu.toString());\n"+
            "        }\n"+
            "        return (tv>nv*mult);\n"+
            "    }\n"+
            "    public boolean VALEQ(NUMVAL n){\n"+
            "        double tv = vi + vr;\n"+
            "        double nv = n.vi + n.vr;\n"+
            "        double mult = 1;\n"+
            "        if(!vu.toString().equals(n.vu.toString())) {\n"+
    	    "        mult = vu.fact.findRate(vu.toString(), n.vu.toString());\n"+
    	    "        }\n"+
            "        return (tv==nv*mult);\n"+
            "    }\n"+
            "    public boolean VALNEQ(NUMVAL n){\n"+
            "        double tv = vi + vr;\n"+
            "        double nv = n.vi + n.vr;\n"+
            "        double mult = 1;\n"+
            "        if(!vu.toString().equals(n.vu.toString())) {\n"+
    	    "        mult = vu.fact.findRate(vu.toString(), n.vu.toString());\n"+
    	    "        }\n"+
            "        return (tv!=nv*mult);\n"+
            "    }\n"+
            "}";

    }
}
