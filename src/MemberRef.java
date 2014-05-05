import java.util.ArrayList;

public class MemberRef implements Node{

    Node objIdent;
    String member;
    String type;
    String initChain = "";    
    SymbolRecord symrecord;

    boolean isDefined = true;
    boolean funcRef = false;
    
    public MemberRef(Node o, String m, String t, SymbolRecord sr){
        objIdent = o;
        member = m;
        type = t;
        symrecord = sr;
    }

    public String getName(){
        return ((Variable) objIdent).getName()+"."+member;
    }

    public void addChild(Node o){
        return;
    }

    public Node[] getChildren(){
        Node[] children = {objIdent};
        return children;
    }

    public String[] translate(){
        String[] translation;
        translation = new String[2];
        translation[0] = Translator.IN_PLACE;
        if(!funcRef)
            translation[1] = ((Variable) objIdent).getName()+"."+member;
        else{
            String ttype = symrecord.getType(((Variable) objIdent).getName());
            DefinedType tobj = symrecord.getTypeObj(ttype);
            int index = tobj.funcs.indexOf(member);
            String tr = "";
            String pref = ((Variable) objIdent).getName() + ".";
            ArrayList<String> parts = tobj.funcContents.get(index);
            if(type.equals("String")){
                for(int i = 0; i < parts.size(); i+=3){
                    if(parts.size() < 3){ tr = pref+parts.get(i); continue; }
                    if(! parts.get(i+1).equals("+")) { System.out.println("Woops!  You can only add"
                                                         +" strings!"); System.exit(0); }
                    tr += pref+parts.get(i)+".toString()"+parts.get(i+1)+pref+parts.get(i+2)+
                               ".toString()";
                }
            }
            if(type.equals("NUMVAL")){
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
            }
            translation[1] = tr;
        }
        return translation;
    }

    public String chain(){
        if(type.equals("String")||type.equals("NUMVAL")) return initChain;
        else if(!symrecord.isDeclared(getName())){
            symrecord.setDeclared(getName(),1);
            return initChain+getName()+" = factory.new "+type+"(factory);\n";
        }
        return initChain;
    }

    public String getSemanticRepresentation(){
        return "";
    }

}
