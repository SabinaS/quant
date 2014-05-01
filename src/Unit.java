/**
 * Explicit type for semantic node representing
 * a unit.  (Is a leaf--has no children.)
 *
 * @author Aubrey
 */

public class Unit implements Node{
    /** String identifier referring to the unit. */    
    String unit;
    
    /**
     * Constructs a Unit for a
     * given identifier string.
     *
     * @param v string to be held
     */
    public Unit(String v){
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
        String[] translation = new String[2];
        translation[0] = Translator.IN_PLACE;
        translation[1] = unit;
        return translation;
    }

    public String getSemanticRepresentation(){
        return "";
    }
}
