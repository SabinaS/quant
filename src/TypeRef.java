public class TypeRef implements Node{

    private String identifier;
    private SymbolRecord symrecord;
  
    public TypeRef(String i, SymbolRecord s){
        identifier = i;
        symrecord = s;
    }

    public void addChild(Node n){
        return;
    }

    public Node[] getChildren(){
        Node[] children = {};
        return children;
    }

    public String getType(){ return identifier; }

    public String[] translate(){
        String[] translation = {""};
        return translation;
    }

    public String getSemanticRepresentation(){
        return "";
    }

}
