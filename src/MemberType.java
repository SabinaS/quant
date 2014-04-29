public class MemberType implements Node{

    SymbolRecord symrecord;
    Node typeIdentifier;
    Node inheritsFrom;    

    public MemberType(Node t, Node i, SymbolRecord s){
        symrecord = s;
        typeIdentifier = t;
        inheritsFrom = i;
        // TODO:  symbol table management
    }

    public void addChild(Node n){
        return;
    }

    public Node[] getChildren(){
        if(inheritsFrom == null){
            Node[] children = { typeIdentifier };
            return children;
        }
        Node[] children = {typeIdentifier, inheritsFrom};
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
