/**
 * Explicit type for a semantic node
 * representing a value.  Wraps a single
 * value-evaluating node.
 *
 * @author Aubrey
 */

public class Value implements Node{
    
    /** Semantic node containing specific
      * value type. */
    private Node wrappedValue;
    String initChain = "";

    /**
     * Constructs a value, wrapping n.
     */
    public Value(Node n){
        wrappedValue = n;
    }

    // Children known at construction.
    public void addChild(Node child){
        return;
    }

    public Node[] getChildren(){
        Node[] children = new Node[1];
        children[0] = wrappedValue;
        return children;
    }

    public String[] translate(){
        // TODO
        String[] translation = new String[2];
        translation[0] = Translator.IN_PLACE;
        translation[1] = wrappedValue.translate()[1];
        return translation;
    }

    public String getSemanticRepresentation(){
        // TODO
        return "";
    }
}
