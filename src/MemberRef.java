public class MemberRef implements Node{

    Node objIdent;
    String member;
    String type;
    String initChain = "";    
    SymbolRecord symrecord;

    boolean isDefined = true;
    
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
        translation[1] = ((Variable) objIdent).getName()+"."+member;
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
