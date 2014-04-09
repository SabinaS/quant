/**
 * Explicit type for semantic node representing
 * a string constant.  (Is a leaf--has no children.)
 *
 * @author Aubrey
 */

public class StringConstant implements Node{
    /** Value of the string constant. */    
    private String value;
    
    /**
     * Constructs a StringConstant for a
     * given string.
     *
     * @param v string to be held
     */
    public StringConstant(String v){
        value = v;
    }

    // Leaf.  has no children.
    public void addChild(Node child){
        return;
    }

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
        return "";
    }
}
