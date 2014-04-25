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
     */
    public TypeDec(Node TS, SymbolRecord SM)
    {
    	    typeSig = TS;
    	    typeMems = null;
    	    symrecord = SM;
    }
    
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
    	if(typeMems == null)
    		children = {typeSig};
    	else
    		children = {typeSig, typeMems};
        return children;
    }

    public String[] translate(){
        String[] translation = new String[2];
        translation[0] = Translator.IN_PLACE;
        translation[1] = typeSig.translate()[1] + 
        	typeMems.translate()[1] + ")";
        return translation;
    }

    public String getSemanticRepresentation(){
        return "";
    }

}
