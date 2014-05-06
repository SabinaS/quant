/**
 * Explicit type for a node representing
 * an operative statement.
 *
 * @author Aubrey
 */

public class OperativeStatement implements Node{

    /** Keyword in the operative statement */
    private Node keyword;

    /** Value on which to operate. */
    private Node value;

    private String conversion;
    /**
     * Constructs an OperativeStatement node
     * for a given keyword and value.
     *
     * @param k semantic node for the keyword
     * @param v semantic node for the value
     */
    public OperativeStatement(Node k, Node v){
        keyword = k;
        value = v;
    }

    public OperativeStatement(Node k, Node v, String c){
        keyword = k;
        value = v;
        conversion = c;
    }

    // operative statement children are specified 
    // at time of constructor call
    public void addChild(Node child){
        return;
    }

    // operator, then value
    public Node[] getChildren(){
        Node[] children = { keyword, value };
        return children;
    }
    
    public String[] translate(){
        //Aubrey
        String[] kwdTranslation = keyword.translate();
        String[] valueTranslation = value.translate();
        boolean valueTranslated = false;        

        // Might be a little slow, but works for a prototype.
        String result = "";
        // Check each directive + literal pair.
        for(int i = 0; i < kwdTranslation.length; i+=2){
            String directive = kwdTranslation[i];
            String literal = kwdTranslation[i+1];

            // Apply the proper rule:  append directly
            // if after an IN_PLACE directive
            if(directive.equals(Translator.IN_PLACE))
                result = result + literal;
            // Process sibling (value) otherwise
            else if(directive.equals(Translator.AFTER_SIBLING)){
                for(int j = 0; j < valueTranslation.length; j+=2){
                    // semanically, values should always correspond
                    // to an in-place translation
                    result = result + valueTranslation[j+1];
                }
                valueTranslated = true;
                
                result = result + literal;
            }
        }
        if(!valueTranslated){
            // Assume again that values always in place.
            for(int i = 0; i < valueTranslation.length; i+=2)
                result+=valueTranslation[i+1];
        }


        String[] translation = { Translator.IN_PLACE, result };
        return translation;
    }

    public String getSemanticRepresentation(){
        // TODO
        return "";
    }
}
