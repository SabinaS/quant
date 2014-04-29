public class TypeRef implements Node{

    private Node identifier;
    private SymbolRecord symrecord;
  
    public void TypeRef(Node i, SymbolRecord s){
        identifier = i;
        symrecord = s;
    }

    public void addChild(Node n){
        return;
    }

    public Node[] getChildren(){
        Node[] children = {identifier};
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
