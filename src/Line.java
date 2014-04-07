/**
 * Explicit type for a line-level
 * node element in the AST.
 *
 * @author Aubrey
 */

public class Line implements Node{

    /** The functional content of the line. */
    private Node qualifier;

    /** The line terminator (if non-block-level qualifier.) */
    private Node terminator;

    /**
     * Constructor for a Line node.
     *
     * @param q node containing functional subtree of the line
     * @param t node containing the line's terminator (null if q is block-level)
     */
    public Line(Node q, Node t){
        qualifier = q;
        terminator = t;
    }

    // Semantically, a line will never have more than
    // the two children (qualifier, terminator) already
    // known.
    public void addChild(Node child){
        return;
    }

    // Return array of non-null nodes.
    // will be length 1 for line containing block-level
    // qualifier or 2 otherwise.
    public Node[] getChildren(){
        Node[] children;
        if(terminator == null){
            children = new Node[1];
            children[0] = qualifier;
        }  else{
            children = new Node[2];
            children[0] = qualifier;
            children[1] = terminator;
        }
        return children;
    }

    public String translate(){
        // TODO
        String[] arr = { "" };
        return arr;
    }

    public String getSemanticRepresentation(){
        // TODO
        return "";
    }
}
