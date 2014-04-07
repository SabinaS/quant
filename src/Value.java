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
        String[] arr = {};
        return arr;
    }

    public String getSemanticRepresentation(){
        // TODO
        return "";
    }
}
