/**
 * Explicit type definition for an AssignmentType
 * semantic node.
 *
 * @author Aubrey
 */

public class AssignmentStatement implements Node{

    /** 
     * Semantic node containing the representation of
     * the node to set.
     */

    private Node toSet;
    /** 
     * Semantic node containing the value to which to set
     * toSet.
     */

    private Node value;
    /** 
     * Mode of assignment--either NODE.ASSMNT_MODE
     * for simple assignment or NODE.INHT_MODE
     * for initialization of non-primitive to a type.
     */
    private int mode;

    /**
     * Constructs an AssignmentStatement in a given mode.
     *
     * @param t node to set
     * @param v value to which to set t
     * @m mode of assignment
     */
    public AssignmentStatement(Node t, Node v, int m){
        toSet = t;
        value = v;
        mode = m;
    }

    // Children known at time of construction.
    public void addChild(Node child){
        return;
    }

    // toSet, followed by value.
    public Node[] getChildren(){
        Node[] children = { toSet, value };
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
