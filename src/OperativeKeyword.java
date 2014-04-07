/**
 * Explicit type definition for the semantic
 * node representing an operative keyword.
 * (Is a leaf.  No nodes contained.) 
 *
 * @author Aubrey
 */

public class OperativeKeyword implements Node{
    
    /** Wrapped keyword (encoded). */
    private int kwd;

    /**
     * Constructs an OperativeKeyword node,
     * wrapping a specified keyword (encoded).
     *
     * @param k keyword represented
     */
    public OperativeKeyword(int k){
        keyword = k;
    }

    // Children known at construction.
    public void addChild(Node child){
        return;
    }

    // Leaf.  No children.
    public Node[] getChildren(){
        Node[] children = {};
        return children;
    }

    public String[] translate(){
        // TODO
        String[] arr = {""};
        return arr;
    }

    public String getSemanticRepresentation(){
        // TODO
        return "";
    }
}
