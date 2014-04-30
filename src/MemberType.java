public class MemberType implements Node{

    SymbolRecord symrecord;
    String identifier;
    String type;    
    String val;

    public MemberType(String i, String t, String v, SymbolRecord s){
        identifier = i;
        type = t;
        val = v;
        symrecord = s;
        // TODO:  symbol table management
    }

    public void addChild(Node n){
        return;
    }

    public Node[] getChildren(){
        Node[] children = { };
        return children;
    }

    public String[] translate(){
        String[] translation = {""};
        return translation;
    }

    public String getSemanticRepresentation(){
        return "";
    }

}
