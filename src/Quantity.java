/**
 * Explicit type for semantic node representing
 * a quantity (number + unit pair).
 *
 * @author Aubrey
 */

public class Quantity implements Node{
    
    private Node number;
    private Node unit;

    /**
     * Constructs a quantity with a given
     * number and unit.
     *
     * @param n number
     * @param u (may be null)
     */
    public Quantity(Node n, Node u){
        number = n;
        unit = u;
    }

    // children known at construction
    public void addChild(Node n){
        return;
    }

    public Node[] getChildren(){
        if(unit != null){
            Node[] children = { number, unit };
            return children;
        } else{
            Node[] children = { number };
            return children;
        }
    }

    public String[] translate(){
        // TODO
        String[] arr = { "" };
        return arr;
    }

    public String getSemanticRepresentation(){
        // TODO
        return "";
    }
}
