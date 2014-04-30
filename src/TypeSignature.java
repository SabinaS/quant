/**
 * Semantic node representing a type signature.
 *
 * @author Aubrey
 */

public class TypeSignature implements Node{

    Node typeRef;
    private Node stypeRef;
    private Node identifierChain;
    private SymbolRecord symrecord;

    public TypeSignature(Node t, Node i, SymbolRecord s){
        typeRef = t;
        identifierChain = i;
        symrecord = s;
        stypeRef = null;
        // TODO:  symbol table manipulation
    }
  
    public TypeSignature(Node t, Node st, Node i, SymbolRecord sr){
        typeRef = t;
        stypeRef = st;
        identifierChain = i;
        symrecord = sr;
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
