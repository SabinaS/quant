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
        // want typeSig to return a translation that looks like
        // { "type name", "field1", "field2", "field3" }
        String[] tSigTrans = typeSig.translate();
        String javaClass = "class "+tSigTrans[0]+"{\n";
        
        // if no member type chain...
        if(typeMems == null){
            for(int i = 1; i < tSigTrans.length; i++){
                javaClass += "    public NUMVAL "+tSigTrans[i]+";";
                javaClass += "    public String "+tSigTrans[i]+"_str;";
            }
        }
        // if there is a member chain...
        else{
            // { "field", "value/type" . . . . }
            String[] typeTrans = typeMems.translate();
            // go through all the fields
            for(int i = 1; i < tSigTrans.length; i++){
                // look for the field in the member type chain
                for(int j = 0; j < typeTrans.length; j+=2){
                    if(typeTrans[j].equals(tSigTrans[i])){
                        if(symrecord.getClass(tSigTrans[i+1]) == SymbolRecord.TYPE_CLASS){
                            javaClass += "    public "+tSigTrans[i+1]+" "+
                    }
                }
            } 
        }
     
        return translation;
    }

    public String getSemanticRepresentation(){
        return "";
    }

}
