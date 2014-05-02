import java.util.ArrayList;

public class Aggregator implements Node{

    int ct;
    String type;
    String member;
    SymbolRecord symrecord;
    boolean funcRef = false;

    public Aggregator(String t, String f, int c, SymbolRecord s, boolean ifr){
        ct = c;
        type = t;
        member = f;
        symrecord = s;
        funcRef = ifr;
    }

    public void addChild(Node n){
        return;
    }

    public Node[] getChildren(){
        Node[] children = {};
        return children;
    }

    public String[] translate(){
        String[] translation;
        translation = new String[2];
        translation[0] = Translator.IN_PLACE;
        if(!funcRef)
            translation[1] = "factory.new "+type+"(factory)."+member;
        else{
            DefinedType tobj = symrecord.getTypeObj(type);
            int index = tobj.funcs.indexOf(member);
            String tr = "";
            String pref = "factory. new "+type+"(factory).";
            ArrayList<String> parts = tobj.funcContents.get(index);
            tr = pref+parts.get(0);
            for(int i = 1; i < parts.size(); i+=2){
                if(parts.size() < 3){ tr = pref+parts.get(i); continue; }
                String op = parts.get(i);
                if(op.equals("+")){ op = ".VALPLUS("; }
                if(op.equals("-")){ op = ".VALMINUS("; }
                if(op.equals("*")){ op = ".VALTIMES("; }
                if(op.equals("/")){ op = ".VALDIVIDE("; }
                tr = "("+tr+")"+op+pref+parts.get(i+1)+")";
            }
            translation[1] = tr;
        }
        translation[1] = "factory.new NUMVAL("+ct+",new UNIT(\"\",factory)).VALTIMES("+translation[1]+")";
        return translation;
    
    }

    public String getSemanticRepresentation(){
        return "NUMVAL";
    }

}
