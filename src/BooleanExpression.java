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
        String[] translation = new String[2];
        // check the types of the expressions
        String literal = "";
        String left = leftExp.translate()[1];
        String right = rightExp.translate()[1];
        String lType = leftExp.getSemanticRepresentation();
        String rType = rightExp.getSemanticRepresentation();
        if((lType.toLowerCase().equals("string")&&
                !(rType.toLowerCase().equals("string")))||
           (rType.toLowerCase().equals("string")&&
                !(rType.toLowerCase().equals("string"))))
        {
            System.out.println("Incomparable types in boolean expression.");
            System.exit(0);
        }
        // String comparisons
        if(lType.toLowerCase().equals("string")){
            if(relOp == Node.SEM_LESS_THAN)
                literal = "("+left+".compareTo("+right+")<0)";
            if(relOp == Node.SEM_GREATER_THAN)
                literal = "("+left+".compareTo("+right+")>0)";
            if(relOp == Node.SEM_EQUAL_TO)
                literal = "("+left+".equals("+right+"))";
            if(relOp == Node.SEM_NEQUAL_TO)
                literal = "(!"+left+".equals("+right+"))";
        }
        // Value comparisons
        else{
            if(relOp == Node.SEM_LESS_THAN)
                literal = "("+left+".VALLT("+right+"))";
            if(relOp == Node.SEM_GREATER_THAN)
                literal = "("+left+".VALGT("+right+"))";
            if(relOp == Node.SEM_EQUAL_TO)
                literal = "("+left+".VALEQ("+right+"))";
            if(relOp == Node.SEM_NEQUAL_TO)
                literal = "("+left+".VALNEQ("+right+"))";
        }
        translation[0] = Translator.IN_PLACE;
        translation[1] = literal;
        return translation;
    }

    public String getSemanticRepresentation(){
        return "";
    }

}
