import java.util.ArrayList;

public class IdentifierChain implements Node{
 
    private ArrayList<String> identifiers;

    public IdentifierChain(String i){
        identifiers = new ArrayList<String>();
        identifiers.add(i);
    }

    public void addChild(Node n){
        return;
    }

    public void addIdentifier(String i){
        identifiers.add(i);
    }

    public ArrayList<String> getIdentifiers(){ return identifiers; }

    public Node[] getChildren(){
        Node[] children = {};
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
