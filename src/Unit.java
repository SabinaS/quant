/**
 * Explicit type for semantic node representing
 * a unit.  (Is a leaf--has no children.)
 *
 * @author Aubrey
 */

public class Unit implements Node{
    /** String identifier referring to the unit. */    
    private String unit;
    
    /**
     * Constructs a Unit for a
     * given identifier string.
     *
     * @param v string to be held
     */
    public StringConstant(String v){
        unit = v;
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
