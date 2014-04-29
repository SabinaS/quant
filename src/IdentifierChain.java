import java.util.LinkedList;

public class IdentifierChain implements Node{
 
    private LinkedList<Node> identifiers;

    public IdentifierChain(Node i){
        identifiers = new LinkedList<Node>();
        identifiers.add(i);
    }

    public void addChild(Node n){
        identifiers.add(n);
    }

    public Node[] getChildren(){
        Node[] children = new Node[identifiers.size()];
        for(int i = 0; i < children.size(); i++){
            children[i] = identifiers.get(i);
        }
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
