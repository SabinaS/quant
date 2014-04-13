/**
 * Explicit class defining the semantic node 
 * representing a single boolan expression.
 *
 * @author Aubrey
 */

public class BooleanExpression implements Node{

    /** Encoded relational operator of the boolean expression. */
    private int relOp;

    private Node leftExp;
    private Node rightExp;

    public BooleanExpression(Node l, Node r, int op){
        leftExp = l;
        rightExp = r;
        relOp = op;
    }

    public void addChild(Node n){
        return;
    }

    public Node[] getChildren(){
        Node[] children = {};
        return children;
    }

    public String[] translate(){
        String[] translation = {""};
        return translation;
    }

    public String getSemanticRepresentation(){
        return "";
    }

}
