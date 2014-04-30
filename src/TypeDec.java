/**
 * Explicit type definition for the semantic node 
 * for type declaration
 *
 *@author Ashley
 */
public class TypeDec implements Node{
	
    SymbolRecord symrecord;
    //Type signature of the Type Declatarion- nonnegotiable
    private Node typeSig;
    //Members of the type signature
    private Node typeMems; 
    
    /** Constructs a TypeDec node
     *  @param TS type signature of the type declaration
     *  @param TM type members of the type declaration
     */
    public TypeDec(Node TS, Node TM, SymbolRecord SM)
    {
    	    typeSig = TS;
    	    typeMems = TM;
    	    symrecord = SM;
    }
    
    public void addChild(Node n){
        return;
    }

    public Node[] getChildren(){
        Node[] children;
    	if(typeMems == null){
            children = new Node[1];
            children[0] = typeSig;
        }
    	else{
    	    children = new Node[2];
            children[0] = typeSig; children[1] = typeMems;
        }
        return children;
    }

    public String[] translate(){
        String[] translation = {Translator.IN_PLACE,""};
        return translation;
    }

    public String getSemanticRepresentation(){
        return "";
    }

}
