/**
 * Explicit type definition for the semantic node
 * representing an expression.
 *
 * @author Aubrey
 */

public class Expression implements Node{
    
    /** LHS of the expression. */
    private Node lhs;
    /** RHS of the expression. */
    private Node rhs;
    /** Operation represented. */
    private int operation;

    String initChain = "";
    String semRep = "";
    /** Constructs an Expression node.
      * @param l lhs of the expression
      * @param r rhs of the expression
      * @param o encoded operator of the expression
      */
    public Expression(Node l, Node r, int o){
        lhs = l;
        rhs = r;
        String lc = l.getSemanticRepresentation();
        String rc = r.getSemanticRepresentation();
        if(lc.equals("STRING")) lc = "String";
        if(rc.equals("STRING")) rc = "String";
        semRep = lc;
        operation = o;
    }

    public void addChild(Node c){ return; }

    public Node[] getChildren(){
        Node[] nodes = { lhs, rhs };
        return nodes;
    }

    public String[] translate(){
        String[] translation = new String[4];
        translation[0] = Translator.IN_PLACE;
        translation[1] = lhs.translate()[1] + getOp() +
            rhs.translate()[1] + ")";
        translation[2] = Translator.BEFORE;
        translation[3] = initChain;
        return translation;
    }

    public String getSemanticRepresentation(){
        // TODO
        return semRep;
    }

    private String getOp(){
        if(operation == Node.SEM_PLUS){ return ".VALPLUS("; }
        else if(operation == Node.SEM_MINUS){ return ".VALMINUS("; }
        else if(operation == Node.SEM_TIMES){ return ".VALTIMES("; }
        else{ return ".VALDIVIDE("; }
    }
}
     
