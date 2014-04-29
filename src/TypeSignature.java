/**
 * Semantic node representing a type signature.
 *
 * @author Aubrey
 */

public class TypeSignature implements Node{

    private Node typeRef;
    private Node identifierChain;
    private SymbolRecord symrecord;

    public TypeSignature(Node t, Node i, SymbolRecord s){
        typeRef = t;
        identifierChain = i;
        symrecord = s;
        // TODO:  symbol table manipulation
    }

    public void addChild(Node n){
        return;
    }

    public Node[] getChildren(){
        Node[] children = { typeRef, identifierChain };
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
