/**
 * Explicit type representing a semantic
 * node for an integer/rational numeric
 * constant.
 * 
 * @author Aubrey
 */

public class Number implements Node{

    /** Value if number is rational. */
    private double rVal;
    /** Value if number is an integer. */
    private int iVal;
    /** Semantic flag denoting whether the number
     *  is rational or an integer. */
    private int const_type;

    /**
     * Constructs a Number node for an
     * integer constant.
     */
    public Number(int i){
        rVal = null;
        iVal = i;
        const_type = Node.SEM_INT_CONST;
    }

    /**
     * Constructs a Number node for a rational
     * constant.
     */
    public Number(double r){
        rVal = r;
        iVal = null;
        const_type = Node.SEM_RAT_CONST;
    }

    // Leaf.  Has no children.
    public void addChild(Node child){ return; }

    public Node[] getChildren(){
        Node[] children = {};
        return children;
    }

    public String[] translate(){
        //TODO
        String[] arr = {};
        return arr;
    }

    public String getSemanticRepresentation(){
        // TODO
        return "";
    }
}
